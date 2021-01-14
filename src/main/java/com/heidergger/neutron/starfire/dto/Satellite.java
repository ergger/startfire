package com.heidergger.neutron.starfire.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto Satellite
 * @author Heidergger Forero
 *
 */
@Getter
@Setter
@ToString
public class Satellite implements Serializable {

	/** Serial Code */
	private static final long serialVersionUID = 8991253608038667846L;

	/** Satellite Name */
	private String name;

	/** Origin Distance to satellite */
	private Float distance;

	/** Words intercept */
	private List<String> message; 
}
