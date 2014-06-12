package com.abhisekp;

import java.util.ArrayList;

/**
 * <p>
 * Creation Date: 12-06-2014 09:54 AM
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Observable {
	public ArrayList<TextObserver> observerList = new ArrayList<TextObserver>();

	public static void addObserver(TextObserver observer) {
		observerList.add(observer);
	}

	public static void removeObserver(TextObserver observer) {
		observerList.remove(observer);
	}

	public static void notifyObservers() {
		for (TextObserver observer : observerList) {
			observer.update();
		}
	}

	public void update();

}
