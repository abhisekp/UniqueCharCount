package com.abhisekp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * GUI
 * <p>
 * Creation Date: 12-06-2014 08:12 AM
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class MyGUI extends JFrame implements TextObserver {
	private JLabel inputLabel = new JLabel("Input");
	private JLabel outputLabel = new JLabel("Output");
	private JTextField inputBox = new JTextField("<<Enter String>>", 20);
	private JTextArea outputBox = new JTextArea(15, 20);
	private JButton parseBTN = new JButton("Parse Text");
	private JPanel mainPanel = new JPanel();
	private UniqueCharCount model;
	private String inputText = "";

	public MyGUI(Object ob) {


		if (ob instanceof UniqueCharCount) {
			model = (UniqueCharCount) ob;
		}

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);

		addComponents();
		addListeners();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Unique Char Count");
//		setSize(500, 500);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addListeners() {
		parseBTN.addActionListener(e -> parseText());

		inputBox.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					parseText();
					inputBox.requestFocus();
					inputBox.selectAll();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		inputBox.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				inputBox.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				parseText();
			}
		});
	}

	@Override
	public void update() {
		// update outputBox text from Model
		// update GUI
		String outputText = model.getOutputText();
		outputBox.setText(outputText);
	}

	private void addComponents() {
		mainPanel.add(inputLabel);
		JPanel textBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		textBoxPanel.add(inputBox);
		textBoxPanel.add(parseBTN);
		inputBox.requestFocus();
		inputBox.selectAll();
		mainPanel.add(textBoxPanel);
		mainPanel.add(outputLabel);
		JScrollPane outputBoxScrollPane = new JScrollPane(outputBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outputBox.setLineWrap(true);
		outputBox.setWrapStyleWord(true);
		outputBox.setEditable(false);
		mainPanel.add(outputBoxScrollPane);
	}

	public String getInputText() {
		return inputText;
	}

	private void parseText() {
		inputText = inputBox.getText();
		model.update();
	}
}
