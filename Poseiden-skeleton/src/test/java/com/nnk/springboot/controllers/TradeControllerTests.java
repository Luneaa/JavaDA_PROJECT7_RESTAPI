package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITradeService;
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
public class TradeControllerTests {

    @Mock
    private ITradeService tradeService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private TradeController tradeController;

    @Test
    void home() {
        assertEquals("trade/list", this.tradeController.home(model));
        verify(this.tradeService, times(1)).getTrades();
    }

    @Test
    void addUser() {
        assertEquals("trade/add", this.tradeController.addUser(mock(Trade.class)));
    }

    @Test
    void validate() {
        assertEquals("redirect:/trade/list", this.tradeController.validate(mock(Trade.class), bindingResult, model));
        verify(this.tradeService, times(1)).addTrade(any(Trade.class));
    }

    @Test
    void showUpdateForm() {
        when(this.tradeService.getTrade(0)).thenReturn(Optional.of(mock(Trade.class)));

        assertEquals("trade/update", this.tradeController.showUpdateForm(0, model));
        verify(this.tradeService, times(1)).getTrade(0);
    }

    @Test
    void showUpdateFormEmpty() {
        assertEquals("redirect:/errors/404", this.tradeController.showUpdateForm(0, model));
        verify(this.tradeService, times(1)).getTrade(0);
    }

    @Test
    void updateTrade() {
        when(this.tradeService.getTrade(0)).thenReturn(Optional.of(mock(Trade.class)));

        assertEquals("redirect:/trade/list", this.tradeController.updateTrade(0, mock(Trade.class), bindingResult, model));
        verify(this.tradeService, times(1)).getTrade(0);
        verify(this.tradeService, times(1)).updateTrade(any(Trade.class));
    }

    @Test
    void updateTradeEmpty() {
        assertEquals("redirect:/errors/404", this.tradeController.updateTrade(0, mock(Trade.class), bindingResult, model));
        verify(this.tradeService, times(1)).getTrade(0);
        verify(this.tradeService, times(0)).updateTrade(any(Trade.class));
    }

    @Test
    void deleteTrade() {
        when(this.tradeService.getTrade(0)).thenReturn(Optional.of(mock(Trade.class)));

        assertEquals("redirect:/trade/list", this.tradeController.deleteTrade(0, model));
        verify(this.tradeService, times(1)).getTrade(0);
        verify(this.tradeService, times(1)).deleteTrade(0);
    }

    @Test
    void deleteTradeEmpty() {
        assertEquals("redirect:/errors/404", this.tradeController.deleteTrade(0, model));
        verify(this.tradeService, times(1)).getTrade(0);
        verify(this.tradeService, times(0)).deleteTrade(0);
    }
}
