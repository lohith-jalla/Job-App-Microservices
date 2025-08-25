package com.Lohith.Review.Reviews;

import com.Lohith.Review.Reviews.JWT.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    private JwtService jwtService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByCompanyId(
            @RequestParam Long companyId
    ) {
        List<Review> reviews = reviewService.getReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable("reviewId") Long reviewId
    ) {
        Review r = reviewService.getReviewByReviewId(reviewId);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createReview(
            @RequestParam Long companyId,
            @RequestBody Review review,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);

        boolean created = reviewService.createReview(companyId, review, username);
        if (created) {
            return new ResponseEntity<>("Review Created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Company not found to add a review", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody Review review,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);

        boolean updated = reviewService.updateReview(reviewId, review, username);
        if (updated)
            return new ResponseEntity<>("Review Updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not authorized or not found to update review", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable("reviewId") Long reviewId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);

        boolean deleted = reviewService.deleteReview(reviewId, username);
        if (deleted)
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not authorized or not found to delete review", HttpStatus.NOT_FOUND);
    }
}
