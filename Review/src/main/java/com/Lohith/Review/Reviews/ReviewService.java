package com.Lohith.Review.Reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);
    boolean createReview(Long companyId,Review review);
    Review getReviewByReviewId(Long reviewId);
    boolean updateReview(Long reviewId,Review review);
    boolean deleteReview(Long reviewId);
}
