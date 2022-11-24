package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fielamigo.app.FielAmigo.dao.DogUserDao;
import com.fielamigo.app.FielAmigo.dao.FaDogDao;
import com.fielamigo.app.FielAmigo.dao.FaDogImageDao;
import com.fielamigo.app.FielAmigo.dao.FaImageDao;
import com.fielamigo.app.FielAmigo.dto.DogDto;
import com.fielamigo.app.FielAmigo.dto.DogResDto;
import com.fielamigo.app.FielAmigo.dto.DogUserDto;
import com.fielamigo.app.FielAmigo.entity.FaDog;
import com.fielamigo.app.FielAmigo.service.S3FileStorageServiceImpl;

@Service
public class DogBl {

    private DogUserDao dogUserDao;
    private FaDogDao faDogDao;
    private FaDogImageDao faDogImageDao;
    private FaImageDao faImageDao;
    private S3FileStorageServiceImpl s3FileStorageService;

    public DogBl(DogUserDao dogUserDao, FaDogDao faDogDao, FaDogImageDao faDogImageDao,
        FaImageDao faImageDao, S3FileStorageServiceImpl s3FileStorageService
    ) {
        this.dogUserDao = dogUserDao;
        this.faDogDao = faDogDao;
        this.faDogImageDao = faDogImageDao;
        this.faImageDao = faImageDao;
        this.s3FileStorageService = s3FileStorageService;
    }

    /**
     * Get all the user's dogs by a userId.
     * @param userId the id of the user
     * @return
     */
    public List<DogUserDto> getDogs(int userId) {
        return dogUserDao.getUserDogs(userId);
        
    }

    /**
     * Get all the caregiver's dogs by a caregiverId.
     * @param caregiverId the id of the caregiver
     * @return a list of DogUserDto objects
     */
    public List<DogUserDto> getDogsByCaregiverId(int caregiverId) {
        return dogUserDao.getDogsByCaregiverId(caregiverId);
    }

    /**
     * Create a new dog for a user.
     * @param dog the dog to create
     */
    public void addDog(DogDto dogDto, MultipartFile image) {
        FaDog newDog = new FaDog();
        newDog.setUserId(dogDto.getUserId());
        newDog.setName(dogDto.getName());
        newDog.setIsMale(dogDto.isMale());
        newDog.setBirthDate(dogDto.getBirthDate());
        newDog.setCatSize(dogDto.getSize());
        newDog.setCatBreed(dogDto.getBreed());
        newDog.setIsSterilized(dogDto.isSterilized());
        newDog.setNotes(dogDto.getNotes());

        int dogId = faDogDao.addDog(newDog);

        String imageUrl = s3FileStorageService.upload(image);
        int imageId = faImageDao.addImage(imageUrl);
        faDogImageDao.addDogImage(dogId, imageId);
    }

    /**
     * Method that a returns a DogUserDto by the dog id.
     */
    public DogResDto getDogById(int dogId) {
        return dogUserDao.getDogById(dogId);
    }
}
