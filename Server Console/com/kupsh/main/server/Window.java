package com.kupsh.main.server;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextArea chatBox = new JTextArea();
	private JTextField inputField = new JTextField();

	private Server server;

	public Window(Server server) {
		this.server = server;
	}

	public void window() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Type here:"), BorderLayout.WEST);
		p.add(inputField, BorderLayout.CENTER);

		// add input listener
		inputField.addActionListener(new TextFieldListener(chatBox, inputField,
				server));

		// add to frame
		setLayout(new BorderLayout());
		add(new JScrollPane(chatBox), BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);

		// set visible
		chatBox.setEditable(false);
		setTitle("Server");
		setSize(550, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JTextArea getChatBox() {
		return chatBox;
	}
}
