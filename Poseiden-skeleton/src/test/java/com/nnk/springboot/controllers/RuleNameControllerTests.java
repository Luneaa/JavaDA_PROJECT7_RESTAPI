package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTests {
    @Mock
    private IRuleNameService ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RuleNameController ruleNameController;

    @Test
    void home() {
        assertEquals("ruleName/list", this.ruleNameController.home(model));
        verify(this.ruleNameService, times(1)).getRuleNames();
    }

    @Test
    void addRuleForm() {
        assertEquals("ruleName/add", this.ruleNameController.addRuleForm(mock(RuleName.class)));
    }

    @Test
    void validate() {
        assertEquals("redirect:/ruleName/list", this.ruleNameController.validate(mock(RuleName.class), bindingResult, model));
        verify(this.ruleNameService, times(1)).addRuleName(any(RuleName.class));
    }

    @Test
    void showUpdateForm() {
        when(this.ruleNameService.getRuleName(0)).thenReturn(Optional.of(mock(RuleName.class)));

        assertEquals("ruleName/update", this.ruleNameController.showUpdateForm(0, model));
        verify(this.ruleNameService, times(1)).getRuleName(0);
    }

    @Test
    void showUpdateFormEmpty() {
        assertEquals("redirect:/errors/404", this.ruleNameController.showUpdateForm(0, model));
        verify(this.ruleNameService, times(1)).getRuleName(0);
    }

    @Test
    void updateRuleName() {
        when(this.ruleNameService.getRuleName(0)).thenReturn(Optional.of(mock(RuleName.class)));

        assertEquals("redirect:/ruleName/list", this.ruleNameController.updateRuleName(0, mock(RuleName.class), bindingResult, model));
        verify(this.ruleNameService, times(1)).getRuleName(0);
        verify(this.ruleNameService, times(1)).updateRuleName(any(RuleName.class));
    }

    @Test
    void updateRuleNameEmpty() {
        assertEquals("redirect:/errors/404", this.ruleNameController.updateRuleName(0, mock(RuleName.class), bindingResult, model));
        verify(this.ruleNameService, times(1)).getRuleName(0);
        verify(this.ruleNameService, times(0)).updateRuleName(any(RuleName.class));
    }

    @Test
    void deleteRuleName() {
        when(this.ruleNameService.getRuleName(0)).thenReturn(Optional.of(mock(RuleName.class)));

        assertEquals("redirect:/ruleName/list", this.ruleNameController.deleteRuleName(0, model));
        verify(this.ruleNameService, times(1)).getRuleName(0);
        verify(this.ruleNameService, times(1)).deleteRuleName(0);
    }

    @Test
    void deleteRuleNameEmpty() {
        assertEquals("redirect:/errors/404", this.ruleNameController.deleteRuleName(0, model));
        verify(this.ruleNameService, times(1)).getRuleName(0);
        verify(this.ruleNameService, times(0)).deleteRuleName(0);
    }
}
