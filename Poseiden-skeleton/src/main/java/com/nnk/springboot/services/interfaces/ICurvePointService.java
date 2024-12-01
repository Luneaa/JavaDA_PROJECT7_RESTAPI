package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICurvePointService {

    List<CurvePoint> getCurvePoints();

    Optional<CurvePoint> getCurvePoint(Integer id);

    CurvePoint updateCurvePoint(CurvePoint curvePoint);

    CurvePoint addCurvePoint(CurvePoint curvePoint);

    void deleteCurvePoint(Integer id);
}
