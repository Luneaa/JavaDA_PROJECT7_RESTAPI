package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IRatingService {

    List<Rating> getRatings();

    Optional<Rating> getRating(Integer id);

    Rating updateRating(Rating rating);

    void deleteRating(Integer id);

    Rating addRating(Rating rating);
}
