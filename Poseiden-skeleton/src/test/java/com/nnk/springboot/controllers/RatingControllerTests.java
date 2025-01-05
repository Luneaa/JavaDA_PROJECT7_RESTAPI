package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTests {
    @Mock
    private RatingService ratingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RatingController ratingController;

    @Test
    void home() {
        List<Rating> ratings = new ArrayList<>();
        when(this.ratingService.getRatings()).thenReturn(ratings);

        assertEquals("rating/list", this.ratingController.home(model));
        verify(model, times(1)).addAttribute("ratings", ratings);
    }

    @Test
    void addRatingForm() {
        assertEquals("rating/add", this.ratingController.addRatingForm(mock(Rating.class)));
    }

    @Test
    void validate() {
        assertEquals("redirect:/rating/list", this.ratingController.validate(mock(Rating.class), bindingResult, model));
        verify(this.ratingService, times(1)).addRating(any(Rating.class));
    }

    @Test
    void showUpdateForm() {
        when(this.ratingService.getRating(0)).thenReturn(Optional.of(mock(Rating.class)));

        assertEquals("rating/update", this.ratingController.showUpdateForm(0, model));
        verify(this.ratingService, times(1)).getRating(0);
    }

    @Test
    void showUpdateFormEmpty() {
        assertEquals("redirect:/errors/404", this.ratingController.showUpdateForm(0, model));
        verify(this.ratingService, times(1)).getRating(0);
    }

    @Test
    void updateRating() {
        when(this.ratingService.getRating(0)).thenReturn(Optional.of(mock(Rating.class)));

        assertEquals("redirect:/rating/list", this.ratingController.updateRating(0, mock(Rating.class), bindingResult, model));
        verify(this.ratingService, times(1)).getRating(0);
        verify(this.ratingService, times(1)).updateRating(any(Rating.class));
    }

    @Test
    void updateRatingEmpty() {
        assertEquals("redirect:/errors/404", this.ratingController.updateRating(0, mock(Rating.class), bindingResult, model));
        verify(this.ratingService, times(1)).getRating(0);
        verify(this.ratingService, times(0)).updateRating(any(Rating.class));
    }

    @Test
    void deleteRating() {
        when(this.ratingService.getRating(0)).thenReturn(Optional.of(mock(Rating.class)));

        assertEquals("redirect:/rating/list", this.ratingController.deleteRating(0, model));
        verify(this.ratingService, times(1)).getRating(0);
        verify(this.ratingService, times(1)).deleteRating(0);
    }

    @Test
    void deleteRatingEmpty() {
        assertEquals("redirect:/errors/404", this.ratingController.deleteRating(0, model));
        verify(this.ratingService, times(1)).getRating(0);
        verify(this.ratingService, times(0)).deleteRating(any());
    }
}
