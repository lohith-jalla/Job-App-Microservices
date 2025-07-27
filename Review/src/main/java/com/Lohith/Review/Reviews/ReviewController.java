package com.Lohith.Review.Reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByCompanyId(
            @RequestParam Long companyId
    ){
        List<Review > reviews = reviewService.getReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable("reviewId") Long reviewId
    ){
        Review r=reviewService.getReviewByReviewId(reviewId);
        if(r!=null){
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createReview(
            @RequestParam Long companyId,
            @RequestBody Review review
    ){
        boolean created = reviewService.createReview(companyId,review);
        if(created){
            return new ResponseEntity<>("Review Created successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Company not found to add a review",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody Review review
    ){
        boolean updated = reviewService.updateReview(reviewId,review);
        if(updated)
            return new ResponseEntity<>("Review Updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not found to update a review",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable("reviewId") Long reviewId
    ){
        boolean deleted = reviewService.deleteReview(reviewId);
        if(deleted)
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not found to delete a review",HttpStatus.NOT_FOUND);
    }


}
