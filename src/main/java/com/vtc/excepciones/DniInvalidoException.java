package com.vtc.excepciones;

public class DniInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DniInvalidoException(String mensaje) {
		super(mensaje);
	}

}
