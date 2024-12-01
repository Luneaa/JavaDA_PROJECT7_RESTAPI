package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRatingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RatingController {

    private final IRatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        var ratings = this.ratingService.getRatings();
        model.addAttribute("ratings", ratings);

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        this.ratingService.addRating(rating);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var rating = this.ratingService.getRating(id);
        if (rating.isEmpty()) {
            return "redirect:/errors/404";
        }

        model.addAttribute("rating", rating.get());

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        var existingRating = this.ratingService.getRating(id);
        if (existingRating.isEmpty()) {
            // User tried to update non-existing rating
            return "redirect:/errors/404";
        }

        // No fields are mandatory so no more check needed

        // Update rating entity
        this.ratingService.updateRating(rating);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        var existingRating = this.ratingService.getRating(id);
        if (existingRating.isEmpty()) {
            // User tried to delete non-existing rating
            return "redirect:/errors/404";
        }

        this.ratingService.deleteRating(id);

        return "redirect:/rating/list";
    }
}
