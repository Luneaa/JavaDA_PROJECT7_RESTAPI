package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
class TradeServiceTests {
    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    void addTrade() {
        var tradeToAdd = new Trade("accountTest", "typeTest");
        tradeService.addTrade(tradeToAdd);
        verify(tradeRepository, times(1)).save(any(Trade.class));
        assertEquals(-1, tradeToAdd.getTradeId());
    }

    @Test
    void getTrades() {
        var trade = new ArrayList<Trade>();
        trade.add(new Trade("accountTest", "typeTest"));
        trade.add(new Trade("accountTest", "typeTest"));

        when(this.tradeRepository.findAll()).thenReturn(trade);
        var result = this.tradeService.getTrades();
        assertEquals(2, result.size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void getTrade() {
        var trade = new Trade("accountTest", "typeTest");

        when(this.tradeRepository.findById(any(Integer.class))).thenReturn(Optional.of(trade));
        var result = this.tradeService.getTrade(1);
        verify(tradeRepository, times(1)).findById(1);
        assertTrue(result.isPresent());
        assertEquals("accountTest", result.get().getAccount());
        assertEquals("typeTest", result.get().getType());

    }

    @Test
    void deleteTrade() {
        this.tradeService.deleteTrade(1);

        verify(tradeRepository, times(1)).deleteById(1);
    }

    @Test
    void updateTrade() {
        var Trade = new Trade("accountTest", "typeTest");
        this.tradeService.updateTrade(Trade);

        verify(tradeRepository, times(1)).save(Trade);
    }
}
