package com.microservice.user;

import com.microservice.user.entities.Rating;
import com.microservice.user.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private RatingService ratingService;

    @Test
    void contextLoads() {
    }

    @Test
    void createRating() {
        Rating rating = Rating.builder().userId("").hotelId("").rating(9).feedback("This is a dummy rating for testing").build();
        ResponseEntity<Rating> response = ratingService.createRating(rating);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "The status code should be CREATED");
    }

    @Test
    void deleteRating(String id){
        ResponseEntity<Rating> ratingResponseEntity = ratingService.deleteRating(id);
        assertEquals(HttpStatus.NO_CONTENT,ratingResponseEntity.getStatusCode(),"The status code should be No content");
    }

    @Test
    void createAndDeleteRatingIntegrationTest() {
        Rating rating = Rating.builder()
                .userId("user123")
                .hotelId("hotel456")
                .rating(9)
                .feedback("This is a dummy rating for testing")
                .build();

        ResponseEntity<Rating> createResponse = ratingService.createRating(rating);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode(), "The creation status code should be CREATED");

        Rating createdRating = createResponse.getBody();

        assertNotNull(createdRating, "The created rating body should not be null");
        assertNotNull(createdRating.getRatingId(), "The created rating should have an ID");

        String ratingId = createdRating.getRatingId();
        ResponseEntity<Rating> deleteResponse = ratingService.deleteRating(ratingId);

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode(), "The deletion status code should be NO_CONTENT");
    }

}
