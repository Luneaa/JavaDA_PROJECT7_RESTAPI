package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleNameService;
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
public class RuleNameController {

    private final IRuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        var ruleNames = this.ruleNameService.getRuleNames();

        model.addAttribute("ruleNames", ruleNames);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        this.ruleNameService.addRuleName(ruleName);

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var existingRuleName = this.ruleNameService.getRuleName(id);
        if (existingRuleName.isEmpty()) {
            return "redirect:/errors/404";
        }

        model.addAttribute("ruleName", existingRuleName.get());

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        var existingRuleName = this.ruleNameService.getRuleName(id);
        if (existingRuleName.isEmpty()) {
            return "redirect:/errors/404";
        }

        this.ruleNameService.updateRuleName(ruleName);

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        var existingRuleName = this.ruleNameService.getRuleName(id);
        if (existingRuleName.isEmpty()) {
            return "redirect:/errors/404";
        }

        this.ruleNameService.deleteRuleName(id);

        return "redirect:/ruleName/list";
    }
}
