package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.DogUserDao;
import com.fielamigo.app.FielAmigo.dao.FaDogDao;
import com.fielamigo.app.FielAmigo.dto.DogDto;
import com.fielamigo.app.FielAmigo.entity.DogUser;
import com.fielamigo.app.FielAmigo.entity.FaDog;

@Service
public class DogBl {

    private DogUserDao dogUserDao;
    private FaDogDao faDogDao;

    public DogBl(DogUserDao dogUserDao, FaDogDao faDogDao) {
        this.dogUserDao = dogUserDao;
        this.faDogDao = faDogDao;
    }

    /**
     * Get all the user's dogs by a userId.
     * @param userId the id of the user
     * @return
     */
    public List<DogUser> getDogs(int userId) {
        return dogUserDao.getDogs(userId);
        
    }

    /**
     * Create a new dog for a user.
     * @param dog the dog to create
     */
    public void addDog(DogDto dogDto) {
        FaDog newDog = new FaDog();
        newDog.setUserId(dogDto.getUserId());
        newDog.setName(dogDto.getName());
        newDog.setIsMale(dogDto.isMale());
        newDog.setBirthDate(dogDto.getBirthDate());
        newDog.setCatSize(dogDto.getSize());
        newDog.setCatBreed(dogDto.getBreed());
        newDog.setIsSterilized(dogDto.isSterilized());
        newDog.setNotes(dogDto.getNotes());

        // TODO: add image to AWS S3

        faDogDao.addDog(newDog);
    }
}
