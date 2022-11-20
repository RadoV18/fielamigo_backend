package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaReviewDao;
import com.fielamigo.app.FielAmigo.dto.ReviewResDto;

@Service
public class ReviewBl {
    
    private FaReviewDao faReviewDao;

    public ReviewBl(FaReviewDao faReviewDao) {
        this.faReviewDao = faReviewDao;
    }

    public List<ReviewResDto> getReviewsByCaregiverId(Integer caregiverId) {
        return faReviewDao.getReviewsByCaregiverId(caregiverId);
    }
}
