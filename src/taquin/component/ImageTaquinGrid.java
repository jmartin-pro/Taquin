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

	/**
	 * Dessin du taquin avec une image
	 * @param g l'objet avec lequel on dessine
	 */
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

				int xCaseValue = (caseValue-1) % getTaquinGrid().getWidth();
				int yCaseValue = (caseValue-1) / getTaquinGrid().getWidth();

				g.drawImage(image,
					x * cellSize, y * cellSize, (x + 1) * cellSize, (y + 1) * cellSize,
					(int)((xCaseValue * cellSize) * factor), (int)((yCaseValue * cellSize) * factor), (int)(((xCaseValue + 1) * cellSize) * factor), (int)(((yCaseValue + 1) * cellSize) * factor),
					null);
			}
		}

		drawSelectedSquare(g);
		drawGrid(g);
	}

	/**
	 * Récupère l'image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Modifie l'image
	 * @param image la nouvelle image
	 */
	public void setImage(Image image) {
		this.image = image;
		this.repaint();
	}
}
