package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.interfaces.IRuleNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleNameService implements IRuleNameService {

    private final RuleNameRepository ruleNameRepository;

    public List<RuleName> getRuleNames() {
        return this.ruleNameRepository.findAll();
    }

    public Optional<RuleName> getRuleName(Integer id) {
        return this.ruleNameRepository.findById(id);
    }

    public RuleName updateRuleName(RuleName ruleName) {
        return this.ruleNameRepository.save(ruleName);
    }

    public RuleName addRuleName(RuleName ruleName) {
        ruleName.setId(-1);
        return this.ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id) {
        this.ruleNameRepository.deleteById(id);
    }
}
