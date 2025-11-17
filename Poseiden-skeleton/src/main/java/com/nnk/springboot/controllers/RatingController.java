package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for ratings
 */
@Controller
@RequiredArgsConstructor
public class RatingController {

    private final IRatingService ratingService;

    private static final String REDIRECT_RATING_LIST = "redirect:/rating/list";
    private static final String REDIRECT_ERROR_404 = "redirect:/errors/404";

    /**
     * Displays the list of ratings
     * @param model Spring model
     * @return ratings list url
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        var ratings = this.ratingService.getRatings();
        model.addAttribute("ratings", ratings);

        return "rating/list";
    }

    /**
     * Displays the add form for ratings
     * @param rating _
     * @return ratings add form url
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Adds and validate a rating and redirect to list
     * @param rating rating to validate and add
     * @param result _
     * @param model _
     * @return rating list url
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        this.ratingService.addRating(rating);

        return REDIRECT_RATING_LIST;
    }

    /**
     * Displays the update form for ratings
     * @param id id of the rating to update
     * @param model spring model
     * @return url of the rating update form
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var rating = this.ratingService.getRating(id);
        if (rating.isEmpty()) {
            // No matching entity found
            return REDIRECT_ERROR_404;
        }

        model.addAttribute("rating", rating.get());

        return "rating/update";
    }

    /**
     * Validates and update a rating
     * @param id id of the rating to update
     * @param rating updated rating entity
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        var existingRating = this.ratingService.getRating(id);
        if (existingRating.isEmpty()) {
            // User tried to update non-existing rating
            return REDIRECT_ERROR_404;
        }

        // Update rating entity
        this.ratingService.updateRating(rating);

        return REDIRECT_RATING_LIST;
    }

    /**
     * Deletes a rating based on its id
     * @param id id of the rating to delete
     * @param model _
     * @return result url
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        var existingRating = this.ratingService.getRating(id);
        if (existingRating.isEmpty()) {
            // User tried to delete non-existing rating
            return REDIRECT_ERROR_404;
        }

        this.ratingService.deleteRating(id);

        return REDIRECT_RATING_LIST;
    }
}
