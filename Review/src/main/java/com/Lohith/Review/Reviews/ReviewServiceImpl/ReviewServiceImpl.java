package com.Lohith.Review.Reviews.ReviewServiceImpl;



import com.Lohith.Review.Reviews.Review;
import com.Lohith.Review.Reviews.ReviewRepository;
import com.Lohith.Review.Reviews.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        if(companyId != null && review!=null) {
            review.setCompanyId(companyId); // 🔥 This is critical (I have forgotten this)
            reviewRepository.save(review);
            return true;
        }
        else return false;
    }

    @Override
    public Review getReviewByReviewId(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
        Review reviewToUpdate=getReviewByReviewId(reviewId);
        if (reviewToUpdate != null) {
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setRating(review.getRating());

            reviewRepository.save(reviewToUpdate);
            return true;
        }else  return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review =reviewRepository.findById(reviewId).orElse(null);
        if(review!=null) {
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }



}
