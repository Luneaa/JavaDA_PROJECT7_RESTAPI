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

@Controller
@RequiredArgsConstructor
public class TradeController {

    private final ITradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        var trades = this.tradeService.getTrades();

        model.addAttribute("trades", trades);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        this.tradeService.addTrade(trade);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var existingTrade = this.tradeService.getTrade(id);
        if (existingTrade.isEmpty()) {
            return "redirect:/errors/404";
        }

        model.addAttribute("trade", existingTrade.get());

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        var existingTrade = this.tradeService.getTrade(id);
        if (existingTrade.isEmpty()) {
            return "redirect:/errors/404";
        }

        this.tradeService.updateTrade(trade);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        var existingTrade = this.tradeService.getTrade(id);
        if (existingTrade.isEmpty()) {
            return "redirect:/errors/404";
        }

        this.tradeService.deleteTrade(id);

        return "redirect:/trade/list";
    }
}
