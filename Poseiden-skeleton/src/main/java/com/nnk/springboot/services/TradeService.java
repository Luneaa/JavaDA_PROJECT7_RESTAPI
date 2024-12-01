package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.interfaces.ITradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeService implements ITradeService {

    private final TradeRepository tradeRepository;

    public List<Trade> getTrades(){
        return this.tradeRepository.findAll();
    }

    public Optional<Trade> getTrade(Integer id) {
        return this.tradeRepository.findById(id);
    }

    public Trade updateTrade(Trade trade) {
        return this.tradeRepository.save(trade);
    }

    public Trade addTrade(Trade trade) {
        trade.setTradeId(-1);
        return this.tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id) {
        this.tradeRepository.deleteById(id);
    }
}
