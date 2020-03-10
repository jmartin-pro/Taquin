package taquin.component;

import java.awt.*;
import java.awt.event.*;

import taquin.core.TaquinGrid;

@SuppressWarnings("serial")
public class ImageTaquinGrid extends AbstractGUITaquinGrid {

	private Image image;

	public ImageTaquinGrid(TaquinGrid taquinGrid) {
		super(taquinGrid);
	}

	@Override
	public void paintComponent(Graphics g) {
		drawGrid(g);
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
