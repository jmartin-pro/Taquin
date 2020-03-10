package taquin.component;

import java.awt.*;
import javax.swing.*;

import taquin.core.TaquinGrid;


@SuppressWarnings("serial")
public class NumberTaquinGrid extends AbstractGUITaquinGrid {

	public NumberTaquinGrid(TaquinGrid taquinGrid) {
		super(taquinGrid);
	}

	public void paintComponent(Graphics gNum) {
		int cellSize = getCellSize();

		for(int y = 0 ; y < getTaquinGrid().getHeight(); y++) {
			for(int x = 0 ; x < getTaquinGrid().getWidth() ; x++) {
				gNum.drawString(""+getTaquinGrid().getSquare(x,y), cellSize*x, cellSize*(y+1));
			}
		}

		drawGrid(gNum);
	}


}
