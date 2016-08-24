package com.kupsh.main.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseInput extends MouseAdapter implements MouseListener {

	@SuppressWarnings("unused")
	private Game game;

	public MouseInput(Game game) {
		this.game = game;
	}

	@SuppressWarnings("unused")
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
