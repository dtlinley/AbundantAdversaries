package com.dtlinley.abundantadversaries.input;

import com.badlogic.gdx.InputMultiplexer;

/**
 * Handle player input based on whatever control scheme is set up (touch based, keyboard based, etc). Each platform will (as long
 * as it has different controls) probably implement its own IH in order to handle its specific method of input.
 * 
 * @author david
 * 
 */
public interface InputHandler {

	/**
	 * If there is no input at the moment, return true
	 */
	public boolean noInputGiven();

	/**
	 * Based on the player's input, return the direction of focus that the Hero should have.
	 * 
	 * @return a float between 0f and 360f; 0f is "straight right", 90f is "straight up", etc.
	 */
	public float getInputDirection();

	/**
	 * Can be called while the game is in an OverlayState to find whether there has been input since the last update indicating
	 * that the game should be unpaused, or that a new one should be started.
	 */
	public boolean shouldStartGame();

	public InputMultiplexer getInputProcessor();

}
