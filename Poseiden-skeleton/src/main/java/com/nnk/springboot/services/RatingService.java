package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.interfaces.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;

    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }
}
