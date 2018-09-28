package br.com.teste.parser.exceptions;

import java.io.Serializable;

/**
 * Classe de exceção da classe {@link br.com.teste.parser.util.Util}.
 */
public class UtilException extends Exception implements Serializable {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public UtilException(String message) {
		super(message);
	}
}
