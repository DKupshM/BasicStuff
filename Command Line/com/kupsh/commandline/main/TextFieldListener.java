package com.kupsh.commandline.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldListener implements ActionListener {
	private JTextArea chatBox;
	private JTextField inputField;
	private Main main;

	public TextFieldListener(JTextArea chatBox, JTextField inputField,
			Main main) {
		this.chatBox = chatBox;
		this.inputField = inputField;
		this.main = main;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String userInput = inputField.getText();
			inputField.setText("");
			chatBox.append("Command: " + userInput + "\n");
			main.textInput(userInput);
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("hi");
		}
	}
}
