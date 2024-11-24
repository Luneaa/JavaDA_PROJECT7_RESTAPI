package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRatingService {

    List<Rating> getRatings();
}
