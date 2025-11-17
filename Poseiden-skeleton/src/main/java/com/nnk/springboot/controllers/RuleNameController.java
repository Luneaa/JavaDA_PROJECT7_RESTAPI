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

/**
 * Controller for rule names
 */
@Controller
@RequiredArgsConstructor
public class RuleNameController {

    private final IRuleNameService ruleNameService;

    /**
     * Displays the list of rule names
     * @param model Spring model
     * @return rule name list url
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        var ruleNames = this.ruleNameService.getRuleNames();

        model.addAttribute("ruleNames", ruleNames);

        return "ruleName/list";
    }

    /**
     * Displays the add form for rule names
     * @param bid _
     * @return ruleName add form url
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Adds and validate ruleName and redirect to list
     * @param ruleName ruleName to validate and add
     * @param result _
     * @param model _
     * @return ruleName list url
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        this.ruleNameService.addRuleName(ruleName);

        return "redirect:/ruleName/list";
    }

    /**
     * Displays the update form for ruleName
     * @param id id of the ruleName to update
     * @param model spring model
     * @return url of the ruleName update form
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var existingRuleName = this.ruleNameService.getRuleName(id);
        if (existingRuleName.isEmpty()) {
            // No matching entity found
            return "redirect:/errors/404";
        }

        model.addAttribute("ruleName", existingRuleName.get());

        return "ruleName/update";
    }

    /**
     * Validates and update a ruleName
     * @param id id of the ruleName to update
     * @param ruleName updated ruleName entity
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        var existingRuleName = this.ruleNameService.getRuleName(id);
        if (existingRuleName.isEmpty()) {
            // No matching entity found
            return "redirect:/errors/404";
        }

        this.ruleNameService.updateRuleName(ruleName);

        return "redirect:/ruleName/list";
    }

    /**
     * Deletes a ruleName based on its id
     * @param id id of the ruleName to delete
     * @param model _
     * @return result url
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        var existingRuleName = this.ruleNameService.getRuleName(id);
        if (existingRuleName.isEmpty()) {
            // No matching entity found
            return "redirect:/errors/404";
        }

        this.ruleNameService.deleteRuleName(id);

        return "redirect:/ruleName/list";
    }
}
