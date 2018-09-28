package br.com.teste.parser.exceptions;

import java.io.Serializable;

/**
 * Classe de exceção da classe {@link br.com.teste.parser.business.GameBusiness}.
 */
public class GameBusinessException extends Exception implements Serializable {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public GameBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public GameBusinessException(String message) {
		super(message);
	}
}
