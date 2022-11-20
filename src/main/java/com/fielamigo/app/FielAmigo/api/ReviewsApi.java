package com.fielamigo.app.FielAmigo.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.ReviewBl;
import com.fielamigo.app.FielAmigo.dto.PaginatedDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.dto.ReviewResDto;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsApi {

    private ReviewBl reviewBl;

    public ReviewsApi(ReviewBl reviewBl) {
        this.reviewBl = reviewBl;
    }

    /**
     * Endpoint to get all reviews of a given caregiver.
     * @param headers the request headers.
     * @param caregiverId the caregiver id.
     * @return A list of reviews.
     */
    @GetMapping("/{caregiverId}")
    public ResponseEntity<ResponseDto<PaginatedDto<List<ReviewResDto>>>> getReviewsByCaregiverId(
            @RequestHeader Map<String, String> headers,
            @PathVariable Integer caregiverId,
            @RequestParam(required = true) Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset
        ) {
        ResponseDto<PaginatedDto<List<ReviewResDto>>> responseDto =
            new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_REVIEWS");

            // get the reviews
            PaginatedDto<List<ReviewResDto>> reviews =
                reviewBl.getPaginatedReviewsByCaregiverId(caregiverId, limit, offset);

            responseDto.setData(reviews);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }
}
