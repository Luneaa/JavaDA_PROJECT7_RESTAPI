package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
class BidListServiceTests {
    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    @Test
    void addBidList() {
        var bidToAdd = new BidList("Test account", "Test type", 20d);
        bidListService.addBidList(bidToAdd);
        verify(bidListRepository, times(1)).save(any(BidList.class));
        assertEquals(-1, bidToAdd.getBidListId());
    }

    @Test
    void getBidLists() {
        var bidLists = new ArrayList<BidList>();
        bidLists.add(new BidList("Account test", "Type test", 10d));
        bidLists.add(new BidList("Account test 2", "Type test2", 20d));

        when(this.bidListRepository.findAll()).thenReturn(bidLists);
        var result = this.bidListService.getBidLists();
        assertEquals(2, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void getBidList() {
        var bidList = new BidList("Account test", "Type test", 10d);

        when(this.bidListRepository.findById(any(Integer.class))).thenReturn(Optional.of(bidList));
        var result = this.bidListService.getBidList(1);
        verify(bidListRepository, times(1)).findById(1);
        assertTrue(result.isPresent());
        assertEquals("Account test", result.get().getAccount());
        assertEquals("Type test", result.get().getType());
        assertEquals(10d, result.get().getBidQuantity());
    }

    @Test
    void deleteBidList() {
        this.bidListService.deleteBidList(1);

        verify(bidListRepository, times(1)).deleteById(1);
    }

    @Test
    void updateBidList() {
        var bidList = new BidList("Account test", "Type test", 10d);
        this.bidListService.updateBidList(bidList);

        verify(bidListRepository, times(1)).save(bidList);
    }
}
