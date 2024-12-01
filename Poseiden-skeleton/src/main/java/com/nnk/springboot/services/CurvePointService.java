package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.interfaces.ICurvePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurvePointService implements ICurvePointService {

    private final CurvePointRepository curvePointRepository;

    public List<CurvePoint> getCurvePoints() {
        return this.curvePointRepository.findAll();
    }

    public Optional<CurvePoint> getCurvePoint(Integer id) {
        return this.curvePointRepository.findById(id);
    }

    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
        return this.curvePointRepository.save(curvePoint);
    }

    public CurvePoint addCurvePoint(CurvePoint curvePoint) {
        curvePoint.setCurveId(-1);
        return this.curvePointRepository.save(curvePoint);
    }

    public void deleteCurvePoint(Integer id) {
        this.curvePointRepository.deleteById(id);
    }
}
