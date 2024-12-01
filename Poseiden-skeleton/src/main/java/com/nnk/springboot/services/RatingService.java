package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.interfaces.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;

    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }

    public Optional<Rating> getRating(Integer id) {
        return this.ratingRepository.findById(id);
    }

    public Rating updateRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    public void deleteRating(Integer id) {
        this.ratingRepository.deleteById(id);
    }

    public Rating addRating(Rating rating) {
        rating.setId(-1); // This is to ensure a new rating
        return this.ratingRepository.save(rating);
    }
}
