package com.abhisekp;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Model
 * <p/>
 * Creation Date: 12-06-2014 08:12 AM
 *
 * @version 1.0.1
 * @since 1.0.0
 */
public class UniqueCharCount implements Observable {
	private final MyGUI gui;
	private String inputText = "";
	private String outputText = "";

	public UniqueCharCount() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		gui = new MyGUI(this);

		addObserver(gui);
	}

	public static void main(String[] args) {
		new UniqueCharCount();
	}

	@Override
	public void addObserver(TextObserver observer) {
		observerList.add(observer);
	}

	@Override
	public void removeObserver(TextObserver observer) {
		observerList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (TextObserver observer : observerList) {
			observer.update();
		}
	}


	@Override
	public void update() {
		inputText = gui.getInputText();
		parseText();
		notifyObservers();
	}

	/**
	 * Main Logic GOES HERE
	 */
	private void parseText() {
		ArrayList<Character> uniqueChar = new ArrayList<Character>();
		ArrayList<Integer> uniqueCharCount = new ArrayList<Integer>();
		outputText = "";
		// update outputText
		String tmpString = "";
		// Count & Create unique chars
		for (int i = 0; i < inputText.length(); i++) {
			char ch = inputText.charAt(i);

			if (tmpString.indexOf(ch) < 0) { // ch is Unique char
				tmpString += ch;
				uniqueChar.add(ch);
				uniqueCharCount.add(1);
			} else { // ch NOT Unique
				int charIndex = uniqueChar.indexOf(ch);
				uniqueCharCount.set(charIndex, uniqueCharCount.get(charIndex) + 1);
			}
		}

		outputText += tmpString + "\n";
		for (int i = 0; i < tmpString.length(); i++) {
			if (' ' != uniqueChar.get(i)) {
				outputText += (uniqueChar.get(i) + " - " + uniqueCharCount.get(i) + "\n");
			} else {
				outputText += ("<space> - " + uniqueCharCount.get(i) + "\n");
			}
		}
	}

	public String getOutputText() {
		return outputText;
	}
}
