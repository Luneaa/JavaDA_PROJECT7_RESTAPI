package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurvePointService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CurveControllerTests {

    @Mock
    private ICurvePointService curvePointService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CurveController curveController;

    @Test
    void home() {
        List<CurvePoint> curvePoints = new ArrayList<>();
        when(curvePointService.getCurvePoints()).thenReturn(curvePoints);

        assertEquals("curvePoint/list", curveController.home(model));
        verify(model, times(1)).addAttribute("curvePoints", curvePoints);
    }

    @Test
    void addCurvePoint() {
        assertEquals("curvePoint/add", this.curveController.addCurvePoint(mock(CurvePoint.class)));
    }

    @Test
    void validate() {
        assertEquals("redirect:/curvePoint/list", this.curveController.validate(mock(CurvePoint.class), bindingResult, model));
        verify(curvePointService, times(1)).addCurvePoint(any(CurvePoint.class));
    }

    @Test
    void showUpdateForm() {
        when(curvePointService.getCurvePoint(0)).thenReturn(Optional.of(mock(CurvePoint.class)));

        assertEquals("curvePoint/update", this.curveController.showUpdateForm(0, model));
        verify(curvePointService, times(1)).getCurvePoint(0);
    }

    @Test
    void showUpdateFormEmpty() {
        assertEquals("redirect:/errors/404", this.curveController.showUpdateForm(0, model));
        verify(curvePointService, times(1)).getCurvePoint(0);
    }

    @Test
    void updateCurvePoint() {
        when(curvePointService.getCurvePoint(0)).thenReturn(Optional.of(mock(CurvePoint.class)));

        assertEquals("redirect:/curvePoint/list", this.curveController.updateCurvePoint(0, mock(CurvePoint.class), bindingResult, model));
        verify(curvePointService, times(1)).updateCurvePoint(any(CurvePoint.class));
        verify(curvePointService, times(1)).getCurvePoint(0);
    }

    @Test
    void updateCurvePointEmpty() {
        assertEquals("redirect:/errors/404", this.curveController.updateCurvePoint(0, mock(CurvePoint.class), bindingResult, model));
        verify(curvePointService, times(0)).updateCurvePoint(any(CurvePoint.class));
        verify(curvePointService, times(1)).getCurvePoint(0);
    }

    @Test
    void deleteCurvePoint() {
        when(curvePointService.getCurvePoint(0)).thenReturn(Optional.of(mock(CurvePoint.class)));

        assertEquals("redirect:/curvePoint/list", this.curveController.deleteCurvePoint(0, model));
        verify(curvePointService, times(1)).getCurvePoint(0);
        verify(curvePointService, times(1)).deleteCurvePoint(0);
    }

    @Test
    void deleteCurvePointEmpty() {
        assertEquals("redirect:/errors/404", this.curveController.deleteCurvePoint(0, model));
        verify(curvePointService, times(1)).getCurvePoint(0);
        verify(curvePointService, times(0)).deleteCurvePoint(0);
    }
}
