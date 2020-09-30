package com.capgemini.exception;
/**
 * 
 * Entity notfound experience
 *
 */

public class EntityNotFoundException extends RuntimeException {
	/**
	 * 
	 * @param msg
	 */
	public EntityNotFoundException(String msg) {
		super(msg);
	}

}
