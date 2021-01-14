package com.heidergger.neutron.starfire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.heidergger.neutron.starfire.dto.Intelligence;
import com.heidergger.neutron.starfire.dto.SatelliteList;

/**
 * Controller Interface
 * @author Heidergger Forero
 *
 */
public interface IStarFireController {

	/**
	 * Endpoint that determinate the distance and message for three satellite
	 * @param satellites
	 * @return
	 */
	@PostMapping("/topsecret/")
	ResponseEntity<Intelligence> getIntelligence(@RequestBody SatelliteList satellites);

	/**
	 * Endpoint that determinate message about one satellite, the location is not possible with one satellite
	 * this end point is request for the test, Never RESTAPI get method must has body
	 * @param name
	 * @param satellite
	 * @return
	 */
	@GetMapping("/topsecret_split/{name}")
	ResponseEntity<Intelligence> getSatellite(@PathVariable("name") String name, @RequestBody SatelliteList satellite);

}
