package com.kidzoo.catalogue.error;

public class CatalogueError extends Exception{
	private static final long serialVersionUID = 1L;
	private Exception underlyingError;

	public CatalogueError(Exception underlyingError,String message) {
		super(message);
		this.underlyingError = underlyingError;
	}
	
	public CatalogueError(String message) {
		super(message);
	}
	

	public Exception getUnderlyingError() {
		return underlyingError;
	}
}
