package com.heidergger.neutron.starfire.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto Intelligence is response
 * @author Heidergger Forero
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Intelligence implements Serializable {

	/** Serial Code */
	private static final long serialVersionUID = 1783694152209816988L;

	/** Dto Position coordenades in [x, y] */
	private Position position;

	/** Message received */
	private String message;

}
