package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITradeService;
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
 * Controller for trade
 */
@Controller
@RequiredArgsConstructor
public class TradeController {

    private final ITradeService tradeService;

    /**
     * Displays the list of trades
     * @param model Spring model
     * @return trade list url
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        var trades = this.tradeService.getTrades();

        model.addAttribute("trades", trades);

        return "trade/list";
    }

    /**
     * Displays the add form for trades
     * @param bid _
     * @return trades add form url
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     * Adds and validates trade and redirect to list
     * @param trade trade to validate and add
     * @param result _
     * @param model _
     * @return trade list url
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        this.tradeService.addTrade(trade);

        return "redirect:/trade/list";
    }

    /**
     * Displays the update form for trade
     * @param id id of the trade to update
     * @param model spring model
     * @return url of the trade update form
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var existingTrade = this.tradeService.getTrade(id);
        if (existingTrade.isEmpty()) {
            // No matching entity found
            return "redirect:/errors/404";
        }

        model.addAttribute("trade", existingTrade.get());

        return "trade/update";
    }

    /**
     * Validates and updates a trade
     * @param id id of the trade to update
     * @param trade updated trade entity
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        var existingTrade = this.tradeService.getTrade(id);
        if (existingTrade.isEmpty()) {
            // No matching entity found
            return "redirect:/errors/404";
        }

        this.tradeService.updateTrade(trade);

        return "redirect:/trade/list";
    }

    /**
     * Deletes a trade based on its id
     * @param id id of the trade to delete
     * @param model _
     * @return result url
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        var existingTrade = this.tradeService.getTrade(id);
        if (existingTrade.isEmpty()) {
            // No matching entity found
            return "redirect:/errors/404";
        }

        this.tradeService.deleteTrade(id);

        return "redirect:/trade/list";
    }
}
