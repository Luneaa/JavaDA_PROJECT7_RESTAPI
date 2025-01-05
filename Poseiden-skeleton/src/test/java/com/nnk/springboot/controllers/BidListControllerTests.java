package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.interfaces.IBidListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
public class BidListControllerTests {

    @Mock
    private IBidListService bidListService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private BidListController bidListController;

    @Test
    void home() {
        assertEquals("bidList/list", bidListController.home(model));
    }

    @Test
    void add() {
        assertEquals("bidList/add", bidListController.addBidForm(new BidList()));
    }

    @Test
    void validate() {
        assertEquals("redirect:/bidList/list", bidListController.validate(new BidList(), bindingResult, model));
    }

    @Test
    void showUpdateForm() {
        BidList bidList = new BidList();
        when(bidListService.getBidList(0)).thenReturn(Optional.of(bidList));

        assertEquals("redirect:/bidList/update", bidListController.showUpdateForm(0, model));
    }

    @Test
    void showUpdateFormEmpty() {
        assertEquals("redirect:/errors/404", bidListController.showUpdateForm(0, model));
    }

    @Test
    void updateBid() {
        BidList bidList = new BidList();
        when(bidListService.getBidList(0)).thenReturn(Optional.of(bidList));

        assertEquals("redirect:/bidList/list", bidListController.updateBid(0, bidList, bindingResult, model));
    }

    @Test
    void updateBidEmpty() {
        BidList bidList = new BidList();
        assertEquals("redirect:/errors/404", bidListController.updateBid(0, bidList, bindingResult, model));
    }

    @Test
    void deleteBid() {
        BidList bidList = new BidList();
        when(bidListService.getBidList(0)).thenReturn(Optional.of(bidList));

        assertEquals("redirect:/bidList/list", bidListController.deleteBid(0, model));
    }

    @Test
    void deleteBidEmpty() {
        assertEquals("redirect:/errors/404", bidListController.deleteBid(0, model));
    }
}
