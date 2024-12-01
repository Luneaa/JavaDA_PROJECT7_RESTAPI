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

@Controller
@RequiredArgsConstructor
public class CurveController {

    private final ICurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        var curvePoints = this.curvePointService.getCurvePoints();

        model.addAttribute("curvePoints", curvePoints);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        this.curvePointService.addCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var existingCurvePoint = this.curvePointService.getCurvePoint(id);
        if (existingCurvePoint.isEmpty()){
            return "redirect:/errors/404";
        }

        model.addAttribute("curvePoint", existingCurvePoint.get());

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        var existingCurvePoint = this.curvePointService.getCurvePoint(id);
        if (existingCurvePoint.isEmpty()){
            return "redirect:/errors/404";
        }

        this.curvePointService.updateCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        var existingCurvePoint = this.curvePointService.getCurvePoint(id);
        if (existingCurvePoint.isEmpty()){
            return "redirect:/errors/404";
        }

        this.curvePointService.deleteCurvePoint(id);

        return "redirect:/curvePoint/list";
    }
}
