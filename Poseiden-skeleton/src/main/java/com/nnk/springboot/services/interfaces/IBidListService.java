package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBidListService {
    List<BidList> getBidLists();

    BidList addBidList(BidList bidList);

    Optional<BidList> getBidList(Integer id);

    void deleteBidList(Integer id);

    BidList updateBidList(BidList bidList);
}
