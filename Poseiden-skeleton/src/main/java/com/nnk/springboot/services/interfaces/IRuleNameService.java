package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage rule names
 */
@Service
public interface IRuleNameService {

    /**
     * Gets all the rule names
     * @return list of all the rule names
     */
    List<RuleName> getRuleNames();

    /**
     * Gets a specific rule name
     * @param id id of the rule name to get
     * @return optional rule name
     */
    Optional<RuleName> getRuleName(Integer id);

    /**
     * Updates a specific rule name
     * @param ruleName rule name to update
     * @return updated rule name
     */
    RuleName updateRuleName(RuleName ruleName);

    /**
     * Adds a new rule name
     * @param ruleName rule name to add
     * @return added rule name
     */
    RuleName addRuleName(RuleName ruleName);

    /**
     * Deletes a specific rule name
     * @param id id of the rule name to delete
     */
    void deleteRuleName(Integer id);
}
