package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to manage curve points
 */
@Service
public interface ICurvePointService {

    /**
     * Gets all the curvePoints
     * @return list of all the curvePoints
     */
    List<CurvePoint> getCurvePoints();

    /**
     * Get a specific curvePoint
     * @param id id of the curvePoint to get
     * @return optional curvePoint
     */
    Optional<CurvePoint> getCurvePoint(Integer id);

    /**
     * Updates a given curvePoint
     * @param curvePoint curvePoint to update
     * @return updated curvePoint
     */
    CurvePoint updateCurvePoint(CurvePoint curvePoint);

    /**
     * Adds a new curvePoint
     * @param curvePoint curvePoint to add
     * @return added curvePoint
     */
    CurvePoint addCurvePoint(CurvePoint curvePoint);

    /**
     * Deletes a specific curvePoint
     * @param id id of the curvePoint to delete
     */
    void deleteCurvePoint(Integer id);
}
