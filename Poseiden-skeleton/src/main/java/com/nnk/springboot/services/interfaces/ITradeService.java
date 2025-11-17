package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage trades
 */
@Service
public interface ITradeService {

    /**
     * Gets all the trades
     * @return list of all the trades
     */
    List<Trade> getTrades();

    /**
     * Gets a specific trade
     * @param id id of the specific trade
     * @return optional trade
     */
    Optional<Trade> getTrade(Integer id);

    /**
     * Updates a specific trade
     * @param trade trade to update
     * @return updated trade
     */
    Trade updateTrade(Trade trade);

    /**
     * Adds a new trade
     * @param trade trade to add
     * @return addedTrade
     */
    Trade addTrade(Trade trade);

    /**
     * Deletes a specific trade
     * @param id id of the trade to delete
     */
    void deleteTrade(Integer id);
}
