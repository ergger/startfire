package com.heidergger.neutron.starfire.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.heidergger.neutron.starfire.controller.IStarFireController;
import com.heidergger.neutron.starfire.dto.Intelligence;
import com.heidergger.neutron.starfire.dto.Position;
import com.heidergger.neutron.starfire.dto.SatelliteList;
import com.heidergger.neutron.starfire.service.IStarFireService;

/**
 * Implementation Controller
 * @author Heidergger Forero
 *
 */
@RestController
public class StarFireControllerImpl implements IStarFireController {

	@Autowired
	IStarFireService iStartFireService;

	@Override
	public ResponseEntity<Intelligence> getIntelligence(SatelliteList satellites) {
		System.out.println(satellites);
		Intelligence response = iStartFireService.getIntelligence(satellites);
		if(response.getPosition().getX() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(response);
	}

	@Override
	public ResponseEntity<Intelligence> getSatellite(String name, SatelliteList satellites) {
		System.out.println(name);
		System.out.println(satellites);
		Intelligence response = new Intelligence(new Position(),iStartFireService.getMessage(satellites));
		return ResponseEntity.ok().body(response);
	}

	
}
