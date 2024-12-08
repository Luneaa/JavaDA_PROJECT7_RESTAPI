package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
class RuleNameServiceTests {
    @Mock
    private RuleNameRepository rulenameRepository;

    @InjectMocks
    private RuleNameService rulenameService;

    @Test
    void addRuleName() {
        var ruleNameToAdd = new RuleName("nameTest", "descriptionTest", "jsonTest", "templateTest","sqlStrTest", "sqlPartTest");
        rulenameService.addRuleName(ruleNameToAdd);
        verify(rulenameRepository, times(1)).save(any(RuleName.class));
        assertEquals(-1, ruleNameToAdd.getId());
    }

    @Test
    void getRuleNames() {
        var ruleName = new ArrayList<RuleName>();
        ruleName.add(new RuleName("nameTest", "descriptionTest", "jsonTest", "templateTest","sqlStrTest", "sqlPartTest"));
        ruleName.add(new RuleName("mameTest", "rescriptionTest", "jsonTest", "remplateTest","sqlStrTest", "sqlPartTest"));

        when(this.rulenameRepository.findAll()).thenReturn(ruleName);
        var result = this.rulenameService.getRuleNames();
        assertEquals(2, result.size());
        verify(rulenameRepository, times(1)).findAll();
    }

    @Test
    void getRuleName() {
        var ruleName = new RuleName("nameTest", "descriptionTest", "jsonTest", "templateTest","sqlStrTest", "sqlPartTest");

        when(this.rulenameRepository.findById(any(Integer.class))).thenReturn(Optional.of(ruleName));
        var result = this.rulenameService.getRuleName(1);
        verify(rulenameRepository, times(1)).findById(1);
        assertTrue(result.isPresent());
        assertEquals("nameTest", result.get().getName());
        assertEquals("descriptionTest", result.get().getDescription());
        assertEquals("jsonTest", result.get().getJson());
        assertEquals("templateTest", result.get().getTemplate());
        assertEquals("sqlStrTest", result.get().getSqlStr());
        assertEquals("sqlPartTest", result.get().getSqlPart());

    }

    @Test
    void deleteRuleName() {
        this.rulenameService.deleteRuleName(1);

        verify(rulenameRepository, times(1)).deleteById(1);
    }

    @Test
    void updateRuleName() {
        var RuleName = new RuleName("nameTest", "descriptionTest", "jsonTest", "templateTest","sqlStrTest", "sqlPartTest");
        this.rulenameService.updateRuleName(RuleName);

        verify(rulenameRepository, times(1)).save(RuleName);
    }
}
