package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaReviewDao;
import com.fielamigo.app.FielAmigo.dto.PaginatedDto;
import com.fielamigo.app.FielAmigo.dto.ReviewResDto;

@Service
public class ReviewBl {
    
    private FaReviewDao faReviewDao;

    public ReviewBl(FaReviewDao faReviewDao) {
        this.faReviewDao = faReviewDao;
    }

    public PaginatedDto<List<ReviewResDto>> getPaginatedReviewsByCaregiverId(
        Integer caregiverId, Integer limit, Integer offset
    ) {
        Integer total = faReviewDao.getReviewCountByCaregiverId(caregiverId);
        List<ReviewResDto> reviews = faReviewDao.getReviewsByCaregiverId(caregiverId, limit, offset);
        return new PaginatedDto<>(reviews, reviews.size(), offset, limit, total);
    }
}
