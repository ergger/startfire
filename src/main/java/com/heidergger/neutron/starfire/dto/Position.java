package com.heidergger.neutron.starfire.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto Position coordenades in [x, y], units based in location of the satellites:
 * Kenobi [-500,-200]
 * Skywalker [100,-100]
 * Solo [100,500]
 * @author Heidergger Forero
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Position implements Serializable {

	/** Serial Code */
	private static final long serialVersionUID = -2444495504877102911L;

	/** Horizontal coordinate */
	private Float x;

	/** Vertical coordinate */
	private Float y;
}
