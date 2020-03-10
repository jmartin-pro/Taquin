package taquin.component;

import java.awt.*;
import java.awt.event.*;

import taquin.core.TaquinGrid;

@SuppressWarnings("serial")
public class ImageTaquinGrid extends AbstractGUITaquinGrid {

	private Image image;

	public ImageTaquinGrid(TaquinGrid taquinGrid, Image image) {
		super(taquinGrid);
		setImage(image);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cellSize = getCellSize();
		float factor = Math.min((float)image.getWidth(null) / (float)(getTaquinGrid().getWidth() * cellSize), (float)image.getHeight(null) / (float)(getTaquinGrid().getHeight() * cellSize));

		for(int y = 0 ; y < getTaquinGrid().getHeight() ; y++) {
			for(int x = 0 ; x < getTaquinGrid().getWidth() ; x++) {
				int caseValue = getTaquinGrid().getSquare(x, y);
				if(caseValue == TaquinGrid.EMPTY_SQUARE)
					continue;

				int xCaseValue = caseValue % getTaquinGrid().getWidth();
				int yCaseValue = caseValue / getTaquinGrid().getWidth();

				g.drawImage(image,
					x * cellSize, y * cellSize, x * cellSize + cellSize, y * cellSize + cellSize,
					(int)(xCaseValue * cellSize * factor), (int)(yCaseValue * cellSize * factor), (int)((xCaseValue + 1) * cellSize * factor), (int)((yCaseValue + 1) * cellSize * factor),
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
