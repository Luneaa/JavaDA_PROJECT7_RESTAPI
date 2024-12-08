package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurvePointServiceTests {
    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    @Test
    void addCurvePoint() {
        var curveToAdd = new CurvePoint(1, 10d, 20d);
        curvePointService.addCurvePoint(curveToAdd);
        verify(curvePointRepository, times(1)).save(any(CurvePoint.class));
        assertEquals(-1, curveToAdd.getCurveId());
    }

    @Test
    void getCurvePoints() {
        var curvePoint = new ArrayList<CurvePoint>();
        curvePoint.add(new CurvePoint(1, 10d, 20d));
        curvePoint.add(new CurvePoint(2, 15d, 25d));

        when(this.curvePointRepository.findAll()).thenReturn(curvePoint);
        var result = this.curvePointService.getCurvePoints();
        assertEquals(2, result.size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void getCurvePoint() {
        var curvePoint = new CurvePoint(1, 10d, 20d);

        when(this.curvePointRepository.findById(any(Integer.class))).thenReturn(Optional.of(curvePoint));
        var result = this.curvePointService.getCurvePoint(1);
        verify(curvePointRepository, times(1)).findById(1);
        assertTrue(result.isPresent());
        assertEquals(10d, result.get().getTerm());
        assertEquals(20d, result.get().getValue());
    }

    @Test
    void deleteCurvePoint() {
        this.curvePointService.deleteCurvePoint(1);

        verify(curvePointRepository, times(1)).deleteById(1);
    }

    @Test
    void updateCurvePoint() {
        var curvePoint = new CurvePoint(1, 10d, 20d);
        this.curvePointService.updateCurvePoint(curvePoint);

        verify(curvePointRepository, times(1)).save(curvePoint);
    }
}
