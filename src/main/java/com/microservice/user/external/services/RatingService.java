package com.microservice.user.external.services;

import com.microservice.user.entities.Rating;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("RATING-SERVICE")
@Service
public interface RatingService {
    //    Post
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating rating);

    //    PUT
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

//    Delete
    @DeleteMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> deleteRating(@PathVariable String ratingId);
}
