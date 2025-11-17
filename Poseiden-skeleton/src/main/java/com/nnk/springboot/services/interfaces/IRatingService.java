package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage ratings
 */
@Service
public interface IRatingService {

    /**
     * Gets all the ratings
     * @return list of all the ratings
     */
    List<Rating> getRatings();

    /**
     * Gets a specific rating
     * @param id id of the rating to get
     * @return optional rating
     */
    Optional<Rating> getRating(Integer id);

    /**
     * Updates a given rating
     * @param rating rating to update
     * @return updated rating
     */
    Rating updateRating(Rating rating);

    /**
     * Deletes a specific rating
     * @param id id of the rating to delete
     */
    void deleteRating(Integer id);

    /**
     * Adds a new rating
     * @param rating rating to add
     * @return added rating
     */
    Rating addRating(Rating rating);
}
