package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.interfaces.IBidListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidListService implements IBidListService {

    private final BidListRepository bidListRepository;

    public List<BidList> getBidLists() {
        return this.bidListRepository.findAll();
    }

    public BidList addBidList(BidList bidList) {
        bidList.setBidListId(-1); // set id to -1 to ensure creation of new entity
        return this.bidListRepository.save(bidList);
    }

    public Optional<BidList> getBidList(Integer id) {
        return this.bidListRepository.findById(id);
    }

    public void deleteBidList(Integer id) {
        this.bidListRepository.deleteById(id);
    }

    public BidList updateBidList(BidList bidList) {
        return this.bidListRepository.save(bidList);
    }
}
