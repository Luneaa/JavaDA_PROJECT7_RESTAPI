package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceTests {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    void addRating() {
        var ratingToAdd = new Rating("moddyTest", "sandTest", "fitchTest", 0);
        ratingService.addRating(ratingToAdd);
        verify(ratingRepository, times(1)).save(any(Rating.class));
        assertEquals(-1, ratingToAdd.getId());
    }

    @Test
    void getRatings() {
        var rating = new ArrayList<Rating>();
        rating.add(new Rating("moddyTest", "sandTest", "fitchTest", 0));
        rating.add(new Rating("poddyTest", "kandTest", "fitchTest", 1));

        when(this.ratingRepository.findAll()).thenReturn(rating);
        var result = this.ratingService.getRatings();
        assertEquals(2, result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void getRating() {
        var rating = new Rating("moodyTest", "sandTest", "fitchTest", 0);

        when(this.ratingRepository.findById(any(Integer.class))).thenReturn(Optional.of(rating));
        var result = this.ratingService.getRating(1);
        verify(ratingRepository, times(1)).findById(1);
        assertTrue(result.isPresent());
        assertEquals("moodyTest", result.get().getMoodysRating());
        assertEquals("sandTest", result.get().getSandPRating());
        assertEquals("fitchTest", result.get().getFitchRating());
        assertEquals(0, result.get().getOrderNumber());
        
    }

    @Test
    void deleteRating() {
        this.ratingService.deleteRating(1);

        verify(ratingRepository, times(1)).deleteById(1);
    }

    @Test
    void updateRating() {
        var Rating = new Rating("moddyTest", "sandTest", "fitchTest", 0);
        this.ratingService.updateRating(Rating);

        verify(ratingRepository, times(1)).save(Rating);
    }
}
