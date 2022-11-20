package com.fielamigo.app.FielAmigo.bl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fielamigo.app.FielAmigo.dao.FaImageDao;
import com.fielamigo.app.FielAmigo.dao.FaUserAddressDao;
import com.fielamigo.app.FielAmigo.dao.FaUserDao;
import com.fielamigo.app.FielAmigo.dao.FaUserDetailsDao;
import com.fielamigo.app.FielAmigo.dao.FaUserGroupDao;
import com.fielamigo.app.FielAmigo.dao.FaUserImageDao;
import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.UserAddressReqDto;
import com.fielamigo.app.FielAmigo.dto.UserDetailsReqDto;
import com.fielamigo.app.FielAmigo.entity.FaUser;
import com.fielamigo.app.FielAmigo.entity.FaUserAddress;
import com.fielamigo.app.FielAmigo.entity.FaUserDetails;
import com.fielamigo.app.FielAmigo.entity.FaUserImage;
import com.fielamigo.app.FielAmigo.service.S3FileStorageService;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserBl {

    private FaImageDao faImageDao;
    private FaUserDao faUserDao;
    private FaUserAddressDao faUserAddressDao;
    private FaUserDetailsDao faUserDetailsDao;
    private FaUserGroupDao faUserGroupDao;
    private FaUserImageDao faUserImageDao;
    private S3FileStorageService s3FileStorageService;
    
    public UserBl(FaImageDao faImageDao, FaUserDao faUserDao, FaUserAddressDao faUserAddressDao,
        FaUserDetailsDao faUserDetailsDao, FaUserGroupDao faUserGroupDao, FaUserImageDao faUserImageDao,
        S3FileStorageService s3FileStorageService
    ) {
        this.faImageDao = faImageDao;
        this.faUserDao = faUserDao;
        this.faUserAddressDao = faUserAddressDao;
        this.faUserDetailsDao = faUserDetailsDao;
        this.faUserImageDao = faUserImageDao;
        this.faUserGroupDao = faUserGroupDao;
        this.s3FileStorageService = s3FileStorageService;
    }

    /**
     * Stores a new user in the database.
     * @param createUserDto the user to be stored.
     */
    public int createUser(CreateUserDto createUserDto) {
        FaUser faUser = new FaUser();
        faUser.setEmail(createUserDto.getEmail());

        // Encrypt password with BCrypt
        String secret = BCrypt
            .withDefaults()
            .hashToString(12, createUserDto.getPassword().toCharArray());
        faUser.setSecret(secret);

        // default values for status and cat_status
        faUser.setCatStatus(3);
        faUser.setStatus(1);

        // store user in database
        int userId = faUserDao.createUser(faUser);
        // add user to the selected group
        if(createUserDto.isOwner()) {
            faUserGroupDao.addUserToGroup(userId, 1);
        } else {
            faUserGroupDao.addUserToGroup(userId, 2);
            // TODO: add user to FA_CAREGIVER table
        }
        return userId;
    }

    /**
     * Sets the status of a user to active with incomplete data after the user
     * sends the verification code.
     * @param userId
     */
    public void setToActiveWithIncompleteData(int userId) {
        this.faUserDao.setToActiveWithIncompleteData(userId);
    }

    /**
     * Checks if a user exists in the database.
     * @param email the email of the user to be checked.
     */
    public void userExists(String email) throws FielAmigoException {
        if(this.faUserDao.userExists(email) == 1) {
            throw new FielAmigoException("User already exists");
        }
    }
    /**
     * Method to add a new address to a user.
     * @param userId the user id.
     * @param address the address to be added.
     */
    public void addAddress(int userId, UserAddressReqDto address) {
        FaUserAddress faUserAddress = new FaUserAddress();
        faUserAddress.setUserId(userId);
        faUserAddress.setAddress1(address.getAddress1());
        faUserAddress.setAddress2(address.getAddress2());
        faUserAddress.setCatCityId(address.getCityId());
        faUserAddress.setCatCountryId(address.getCountryId());
        faUserAddress.setZone(address.getZone());
        
        this.faUserAddressDao.addAddress(faUserAddress);
    }

    public void addUserDetails(int userId, UserDetailsReqDto userDetails, MultipartFile image) {
        // add user details to database
        System.out.println(userDetails);
        FaUserDetails faUserDetails = new FaUserDetails();
        faUserDetails.setUserId(userId);
        faUserDetails.setFirstName(userDetails.getFirstName());
        faUserDetails.setLastName(userDetails.getLastName());
        faUserDetails.setPhoneNumber(userDetails.getPhoneNumber());
        faUserDetails.setBirthDate(userDetails.getBirthDate());

        this.faUserDetailsDao.addUserDetails(faUserDetails);

        // add image to s3
        String url = this.s3FileStorageService.upload(image);

        // add user image to database
        int imageId = this.faImageDao.addImage(url);

        // add imageid to user
        FaUserImage faUserImage = new FaUserImage();
        faUserImage.setUserId(userId);
        faUserImage.setImageId(imageId);
        this.faUserImageDao.addUserImage(faUserImage);
    }

}
