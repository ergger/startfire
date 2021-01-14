package com.heidergger.neutron.starfire.service;

import com.heidergger.neutron.starfire.dto.Intelligence;
import com.heidergger.neutron.starfire.dto.SatelliteList;

/**
 * Interface Service
 * @author Heidergger Forero
 *
 */
public interface IStarFireService {

	Intelligence getIntelligence (SatelliteList satellites);

	Intelligence getLocation(SatelliteList satellites);

	String getMessage(SatelliteList satellites);
}
