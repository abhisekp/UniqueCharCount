package com.abhisekp;

import java.util.ArrayList;

/**
 * <p/>
 * Creation Date: 12-06-2014 09:54 AM
 *
 * @version 1.0.1
 * @since 1.0.0
 */
public interface Observable {
	public ArrayList<TextObserver> observerList = new ArrayList<TextObserver>();

	/**
	 * Add observer to list of observers
	 * @param observer Implement {@link com.abhisekp.TextObserver}
	 */
	public void addObserver(TextObserver observer);

	/**
	 * Remove observer to list of observers
	 * @param observer Implement {@link com.abhisekp.TextObserver}
	 */
	public void removeObserver(TextObserver observer);

	/**
	 * Notifies all observers
	 */
	public void notifyObservers();

	/**
	 * Call on Observer updated
	 */
	public void update();

}
