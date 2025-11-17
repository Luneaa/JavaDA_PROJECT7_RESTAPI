package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBidListService;
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
 *  Controller for bid lists
 */
@Controller
@RequiredArgsConstructor
public class BidListController {

    public static final String REDIRECT_ERRORS_404 = "redirect:/errors/404";
    public static final String REDIRECT_BID_LIST_LIST = "redirect:/bidList/list";

    private final IBidListService bidListService;


    /**
     * Displays the list of bid lists
     *
     * @param model Spring model
     * @return bidList list url
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        var bidLists = this.bidListService.getBidLists();

        model.addAttribute("bidLists", bidLists);

        return "bidList/list";
    }

    /**
     *  Displays the add form for bidLists
     *
     * @param bid _
     * @return bidList add form url
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Adds and validate bidList and redirect to list
     * @param bid bidList to validate and add
     * @param result _
     * @param model _
     * @return BidList list url
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        this.bidListService.addBidList(bid);

        return REDIRECT_BID_LIST_LIST;
    }

    /**
     * Displays the update form for bidList
     * @param id id of the bidList to update
     * @param model spring model
     * @return url of the bidList update form
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var bidList = this.bidListService.getBidList(id);

        if (bidList.isEmpty()) {
            // No matching entity found
            return REDIRECT_ERRORS_404;
        }

        model.addAttribute("bidList", bidList.get());

        return "/bidList/update";
    }

    /**
     * Validates and update a bidList
     * @param id id of the bidList to update
     * @param bidList updated bidList entity
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        var existingBidList = this.bidListService.getBidList(id);
        if (existingBidList.isEmpty()) {
            // No matching entity found
            return REDIRECT_ERRORS_404;
        }

        this.bidListService.updateBidList(bidList);

        return REDIRECT_BID_LIST_LIST;
    }

    /**
     * Deletes a bidList based on its id
     * @param id id of the bidList to delete
     * @param model _
     * @return result url
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        var bidList = this.bidListService.getBidList(id);
        if (bidList.isEmpty()) {
            // No matching entity found
            return REDIRECT_ERRORS_404;
        }

        this.bidListService.deleteBidList(id);

        return REDIRECT_BID_LIST_LIST;
    }
}
