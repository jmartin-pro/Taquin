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
		int fontSize = getCellSize()/3;
		FontMetrics fontMetrics = g.getFontMetrics();

		for(int y = 0 ; y < getTaquinGrid().getHeight(); y++) {
			for(int x = 0 ; x < getTaquinGrid().getWidth() ; x++) {
				g.setFont(new Font("TimesRoman",Font.BOLD,fontSize));
				g.drawString(""+getTaquinGrid().getSquare(x,y), cellSize*x+(cellSize-(fontMetrics.stringWidth(""+getTaquinGrid().getSquare(x,y))))/2, cellSize*(y+1)-(cellSize/3));
			}
		}

		drawSelectedSquare(g);
		drawGrid(g);
	}


}
