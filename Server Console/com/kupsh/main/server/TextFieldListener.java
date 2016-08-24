package com.kupsh.main.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldListener implements ActionListener {
	private JTextArea chatBox;
	private JTextField inputField;
	private Server server;

	public TextFieldListener(JTextArea chatBox, JTextField inputField,
			Server server) {
		this.chatBox = chatBox;
		this.inputField = inputField;
		this.server = server;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String userInput = inputField.getText();
			inputField.setText("");
			chatBox.append("Command: " + userInput + "\n");
			server.textInput(userInput);
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("hi");
		}
	}
}
