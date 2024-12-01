package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IRuleNameService {

    List<RuleName> getRuleNames();

    Optional<RuleName> getRuleName(Integer id);

    RuleName updateRuleName(RuleName ruleName);

    RuleName addRuleName(RuleName ruleName);

    void deleteRuleName(Integer id);
}
