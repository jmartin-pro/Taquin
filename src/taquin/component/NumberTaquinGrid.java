package taquin.component;

import java.awt.*;
import javax.swing.*;

import taquin.core.TaquinGrid;


@SuppressWarnings("serial")
public class NumberTaquinGrid extends AbstractGUITaquinGrid {

	public NumberTaquinGrid(TaquinGrid taquinGrid) {
		super(taquinGrid);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cellSize = getCellSize();

		for(int y = 0 ; y < getTaquinGrid().getHeight(); y++) {
			for(int x = 0 ; x < getTaquinGrid().getWidth() ; x++) {
				g.drawString(""+getTaquinGrid().getSquare(x,y), cellSize*x, cellSize*(y+1));
			}
		}

		drawSelectedSquare(g);
		drawGrid(g);
	}


}
