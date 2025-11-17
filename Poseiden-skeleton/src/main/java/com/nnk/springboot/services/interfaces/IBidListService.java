package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage bid lists
 */
@Service
public interface IBidListService {
    /**
     * Gets all the bidLists
     * @return list of all the bidLists
     */
    List<BidList> getBidLists();

    /**
     * Adds a new bidList
     * @param bidList bidList to add
     * @return added bidList
     */
    BidList addBidList(BidList bidList);

    /**
     * Gets a specific bidList
     * @param id id of the bidList to get
     * @return optional bidList
     */
    Optional<BidList> getBidList(Integer id);

    /**
     * Deletes a specific bidList
     * @param id id of the bidList to delete
     */
    void deleteBidList(Integer id);

    /**
     * Updates a given bidList
     * @param bidList bidList to update
     * @return updated bidList
     */
    BidList updateBidList(BidList bidList);
}
