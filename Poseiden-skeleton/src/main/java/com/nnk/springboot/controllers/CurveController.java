package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurvePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Controller for curve points
 */
@Controller
@RequiredArgsConstructor
public class CurveController {

    private final ICurvePointService curvePointService;

    /**
     * Displays the list of curve points
     * @param model Spring model
     * @return curvePoints list url
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        var curvePoints = this.curvePointService.getCurvePoints();

        model.addAttribute("curvePoints", curvePoints);

        return "curvePoint/list";
    }

    /**
     * Displays the add form for curvePoints
     * @param bid _
     * @return curvePoint add form url
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePoint(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Adds and validate curvePoint and redirect to list
     * @param curvePoint curvePoint to validate and add
     * @param result _
     * @param model _
     * @return CurvePoint list url
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        this.curvePointService.addCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    /**
     * Displays the update form for curvePoints
     * @param id id of the curvePoint to update
     * @param model spring model
     * @return url of the curvePoint update form
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var existingCurvePoint = this.curvePointService.getCurvePoint(id);
        if (existingCurvePoint.isEmpty()){
            // No matching entity found
            return "redirect:/errors/404";
        }

        model.addAttribute("curvePoint", existingCurvePoint.get());

        return "curvePoint/update";
    }

    /**
     * Validates and update a curvePoint
     * @param id id of the curvePoint to update
     * @param curvePoint updated curvePoint entity
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        var existingCurvePoint = this.curvePointService.getCurvePoint(id);
        if (existingCurvePoint.isEmpty()){
            // No matching entity found
            return "redirect:/errors/404";
        }

        this.curvePointService.updateCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    /**
     * Deletes a curvePoint based on its id
     * @param id id of the curvePoint to delete
     * @param model _
     * @return result url
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        var existingCurvePoint = this.curvePointService.getCurvePoint(id);
        if (existingCurvePoint.isEmpty()){
            // No matching entity found
            return "redirect:/errors/404";
        }

        this.curvePointService.deleteCurvePoint(id);

        return "redirect:/curvePoint/list";
    }
}
