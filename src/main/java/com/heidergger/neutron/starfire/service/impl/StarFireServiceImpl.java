package com.heidergger.neutron.starfire.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heidergger.neutron.starfire.dto.Intelligence;
import com.heidergger.neutron.starfire.dto.Position;
import com.heidergger.neutron.starfire.dto.Satellite;
import com.heidergger.neutron.starfire.dto.SatelliteList;
import com.heidergger.neutron.starfire.service.IStarFireService;

/**
 * Implementation Service
 * @author Heidergger Forero
 *
 */
@Service
public class StarFireServiceImpl implements IStarFireService {

	@Value("${heidergger.neutron.startfire.kenobi.x}")
	private Float xKenobi;

	@Value("${heidergger.neutron.startfire.kenobi.y}")
	private Float yKenobi;

	@Value("${heidergger.neutron.startfire.skywalker.x}")
	private Float xSkywalker;

	@Value("${heidergger.neutron.startfire.skywalker.y}")
	private Float ySkywalker;

	@Value("${heidergger.neutron.startfire.solo.x}")
	private Float xSolo;

	@Value("${heidergger.neutron.startfire.solo.y}")
	private Float ySolo;
	
	@Value("${heidergger.neutron.startfire.name1}")
	private String name1;
	
	@Value("${heidergger.neutron.startfire.name2}")
	private String name2;

	@Value("${heidergger.neutron.startfire.name3}")
	private String name3;
	
	@Override
	public Intelligence getIntelligence(final SatelliteList satellites) {
		Intelligence intelligence = getLocation(satellites);
		intelligence.setMessage(getMessage(satellites));
		return intelligence;
	}

	@Override
	public Intelligence getLocation(final SatelliteList satellites) {
		Position kenobi= new Position(xKenobi,yKenobi);
		Position skywalker= new Position(xSkywalker,ySkywalker);
		Position solo= new Position(xSolo,ySolo);
		System.out.println(kenobi+"-"+skywalker+"-"+solo);
		System.out.println(satellites);
		System.out.println(name1+":"+name2+":"+name3);
		
		Satellite kenoSat = filterByName(satellites, name1);
		Satellite skywSat = filterByName(satellites, name2);
		Satellite soloSat = filterByName(satellites, name3);
		System.out.println(kenoSat);
		List<Position> possibles = trilaterationPossible(kenobi, skywalker, kenoSat, skywSat);
		return new Intelligence(trilaterationDefinitive(possibles, soloSat, solo), null);
	}

	/**
	 * Filter satellite by name from list
	 * @param satellites
	 * @param name
	 * @return
	 */
	private Satellite filterByName(SatelliteList satellites, String name) {
		return satellites.getSatellites()
				.stream().filter(s -> s.getName().equals(name)).findFirst().get();
	}
	/** 
	 * Return two possibles points
	 * @param origin
	 * @param other
	 * @return
	 */
	private List<Position> trilaterationPossible(Position origin, Position other, Satellite originSat, Satellite otherSat) {
		
		//Change Origin cartesian
		Position another = new Position(other.getX()-origin.getX(),other.getY()-origin.getY());
		System.out.println(another);
		//Change orientation Cartesian
		Float hipotenus = (float) Math.sqrt(Math.pow(another.getX(),2) + Math.pow(another.getY(), 2));
		System.out.println(hipotenus);
		//Find Emitter Signal
		Float xEmitter = (float) ( Math.pow(hipotenus, 2) + Math.pow(originSat.getDistance(), 2) - Math.pow(otherSat.getDistance(),  2));
		xEmitter = xEmitter / (2 * hipotenus);
		System.out.println(xEmitter);
		Float yEmitter = 0F;
		if(originSat.getDistance() > xEmitter) {
			yEmitter = (float) Math.sqrt(Math.pow(originSat.getDistance(),2) - Math.pow(xEmitter, 2));
		}else {
			yEmitter = (float) Math.sqrt(Math.pow(xEmitter,2) - Math.pow(originSat.getDistance(), 2));
		}
		System.out.println(yEmitter);
		//Points find allow Cartesian modified get angle for return Cartesian Origin
		Float angle = (float) Math.toDegrees(Math.asin(another.getY()/hipotenus));
		System.out.println(angle);
		//return to Cartessian angle
		Position possible1 = new Position(
				(float)(xEmitter * Math.cos(angle) + originSat.getDistance()*Math.cos(angle)),
				(float)(yEmitter * Math.sin(angle) + originSat.getDistance()*Math.sin(angle)));
		Position possible2 = new Position(
				(float)(xEmitter * Math.cos(angle) + originSat.getDistance()*Math.cos(angle)),
				(float)((yEmitter*(-1)) * Math.sin(angle) + originSat.getDistance()*Math.sin(angle)));
		System.out.println(possible1);
		System.out.println(possible2);
		//return to 0 Cartessian Origin
		possible1.setX(possible1.getX()+origin.getX());
		possible1.setY(possible1.getY()+origin.getY());
		possible2.setX(possible2.getX()+origin.getX());
		possible2.setY(possible2.getY()+origin.getY());
		System.out.println(possible1);
		System.out.println(possible2);
		return new ArrayList<>(Arrays.asList(
					possible1,
					possible2
				));
	}

	/**
	 * Comparative distances possible with definiter
	 * @param possibles
	 * @param definiter
	 * @return
	 */
	private Position trilaterationDefinitive(List<Position> possibles, Satellite definiter, Position canon) {
		for(Position p:possibles) {
			System.out.println(p);
			System.out.println(definiter.getDistance());
			Float xtest = (float) p.getX() - canon.getX();
			Float ytest = (float) p.getY() - canon.getY();
			Float hipotenus = (float) Math.sqrt(Math.pow(xtest,2)+Math.pow(ytest, 2));
			System.out.println(hipotenus);
			System.out.println(Math.floor(hipotenus));
			System.out.println(Math.floor(definiter.getDistance()));
			if(Math.floor(hipotenus) == Math.floor(definiter.getDistance())) {
				System.out.println("hallado!");
				return p;
			}
		}
		System.out.println("Paila");
		return new Position();
	}

	/**
	 * get message from message array each satellite with same size
	 */
	@Override
	public String getMessage(SatelliteList satellites) {
		StringBuilder message = new StringBuilder();
		StringBuilder flat = new StringBuilder();
		for(int i=0;i<satellites.getSatellites().get(0).getMessage().size();i++) {
			for(Satellite satellite :satellites.getSatellites()) {
				if(!satellite.getMessage().get(i).equals("")
					&& flat.length() == 0) {
					flat.append(satellite.getMessage().get(i));
					message.append(satellite.getMessage().get(i));
				}
			}
			flat.setLength(0);
			message.append(" ");
		}
		System.out.println(message);
		return message.toString();
	}

}
