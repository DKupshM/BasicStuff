package com.kupsh.main.game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;

	public void window(String Title, Game game) {
		game.setPreferredSize(new Dimension(640, 480));
		game.setMinimumSize(new Dimension(640, 480));
		frame = new JFrame(Title);
		frame.add(game);
		frame.pack();
		game.requestFocus();
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addComponentListener(new ComponentInput());
	}
}
