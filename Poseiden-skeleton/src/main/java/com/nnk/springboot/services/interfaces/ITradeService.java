package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITradeService {
    List<Trade> getTrades();

    Optional<Trade> getTrade(Integer id);

    Trade updateTrade(Trade trade);

    Trade addTrade(Trade trade);

    void deleteTrade(Integer id);
}
