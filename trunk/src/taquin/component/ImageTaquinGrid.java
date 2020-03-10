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
		int cellSize = getCellSize();

		for(int y = 0 ; y < getTaquinGrid().getHeight() ; y++) {
			for(int x = 0 ; x < getTaquinGrid().getWidth() ; x++) {
				int caseValue = getTaquinGrid().getSquare(x, y);
				if(caseValue == TaquinGrid.EMPTY_SQUARE)
					continue;

				int xCaseValue = caseValue % getTaquinGrid().getWidth();
				int yCaseValue = caseValue / getTaquinGrid().getWidth();

				g.drawImage(image,
				       x * cellSize, y * cellSize, x * cellSize + cellSize, y * cellSize + cellSize,
				       xCaseValue * cellSize, yCaseValue * cellSize, xCaseValue * cellSize + cellSize, yCaseValue * cellSize + cellSize,
				       null);
			}
		}

		drawGrid(g);
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.repaint();
	}
}
