package com.Lohith.Review.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(long companyId);
    Optional<Review> findByIdAndCompanyId(Long reviewId, Long companyId);

}
