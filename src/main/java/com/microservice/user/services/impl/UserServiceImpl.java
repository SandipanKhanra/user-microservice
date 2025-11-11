package com.microservice.user.services.impl;

import com.microservice.user.entities.Hotel;
import com.microservice.user.entities.Rating;
import com.microservice.user.entities.User;
import com.microservice.user.exceptions.ResourceNotFoundException;
import com.microservice.user.external.services.HotelService;
import com.microservice.user.repositories.UserRepository;
import com.microservice.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with given id not found on server - " + userId));
//        Using restTemplate fetch ratings for this user
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        List<Rating> ratingList = Arrays.stream(ratings).map(rating -> {
//                    Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                    Hotel hotel = hotelService.getHotel(rating.getHotelId());
                    rating.setHotel(hotel);
                    return rating;
                }
        ).toList();
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(String userId) {
        return null;
    }
}
