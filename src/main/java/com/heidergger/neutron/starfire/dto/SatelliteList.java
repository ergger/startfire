package com.heidergger.neutron.starfire.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto Satellite list 
 * @author Heidergger Forero
 *
 */
@Getter
@Setter
@ToString
public class SatelliteList implements Serializable {

	/** Serial Code */
	private static final long serialVersionUID = -6573436167096650430L;

	/** satellite list */
	private List<Satellite> satellites;
}
