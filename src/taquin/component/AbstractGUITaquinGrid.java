package taquin.component;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import taquin.observer.TaquinGridObserver;
import taquin.core.TaquinGrid;
import taquin.core.Direction;

@SuppressWarnings("serial")
public class AbstractGUITaquinGrid extends JPanel implements TaquinGridObserver, MouseListener{

	private TaquinGrid taquinGrid;

	private int xMouse;
	private int yMouse;

	public AbstractGUITaquinGrid(TaquinGrid taquinGrid) {
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();

				repaint();
			}
			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		addMouseListener(this);
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent k) {
			}

			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == KeyEvent.VK_DOWN){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.BAS);
					repaint();
				} else if(k.getKeyCode() ==  KeyEvent.VK_RIGHT){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.DROITE);
					repaint();
				}
				else if(k.getKeyCode() == KeyEvent.VK_UP){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.HAUT);
					repaint();
				}
				else if(k.getKeyCode() == KeyEvent.VK_LEFT){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.GAUCHE);
					repaint();
				}
			}

			public void keyReleased(KeyEvent k) {
			}
		});

		this.taquinGrid = taquinGrid;
		this.taquinGrid.addTaquinObserver(this);
	}

	protected int getCellSize() {
		return Math.min(getHeight() / getTaquinGrid().getHeight(), getWidth() / getTaquinGrid().getWidth());
	}

	protected void drawGrid(Graphics g) {
		g.setColor(Color.BLACK);

		int cellSize = getCellSize();

		for(int y = 0 ; y < getTaquinGrid().getHeight() + 1; y++) {
			for(int x = 0 ; x < getTaquinGrid().getWidth() + 1 ; x++) {
				g.drawLine(x * cellSize, 0, x * cellSize, getTaquinGrid().getHeight() * cellSize);
			}
			g.drawLine(0, y * cellSize, getTaquinGrid().getWidth() * cellSize, y * cellSize);
		}
	}

	protected void drawSelectedSquare(Graphics g) {
		int cellSize = getCellSize();

		Graphics2D g2d = (Graphics2D) g;
		Composite originalComposite = g2d.getComposite();

		float alpha = 0.50f;
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(composite);

		g2d.setPaint(Color.WHITE);

		int mouseCaseX = getXMouse() / cellSize;
		int mouseCaseY = getYMouse() / cellSize;
		if(isCaseValid(mouseCaseX, mouseCaseY))
			g2d.fillRect(mouseCaseX * cellSize, mouseCaseY * cellSize, cellSize, cellSize);

		g2d.setComposite(originalComposite);
	}

	protected boolean isCaseValid(int x, int y) {
		int xPosVide = taquinGrid.getPosXVide();
		int yPosVide = taquinGrid.getPosYVide();

		return (x == xPosVide + 1 || x == xPosVide - 1) && y == yPosVide && x < taquinGrid.getWidth()
			|| (y == yPosVide + 1 || y == yPosVide - 1) && x == xPosVide && y < taquinGrid.getHeight();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		int cellSize = getCellSize();
		int mouseCaseX = getXMouse() / cellSize;
		int mouseCaseY = getYMouse() / cellSize;
		int xPosVide = this.taquinGrid.getPosXVide();
		int yPosVide = this.taquinGrid.getPosYVide();

		if(isCaseValid(mouseCaseX, mouseCaseY)) {
			if(mouseCaseX == xPosVide - 1)
				this.taquinGrid.move(Direction.DROITE);
			else if(mouseCaseX == xPosVide + 1)
				this.taquinGrid.move(Direction.GAUCHE);
			else if(mouseCaseY == yPosVide - 1)
				this.taquinGrid.move(Direction.BAS);
			else if(mouseCaseY == yPosVide + 1)
				this.taquinGrid.move(Direction.HAUT);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void moved() {
		this.repaint();
	}

	public void setTaquinGrid(TaquinGrid taquinGrid) {
		this.taquinGrid.removeTaquinObserver(this);
		this.taquinGrid = taquinGrid;
		this.taquinGrid.addTaquinObserver(this);
	}

	public TaquinGrid getTaquinGrid() {
		return this.taquinGrid;
	}

	public int getXMouse() {
		return this.xMouse;
	}

	public int getYMouse() {
		return this.yMouse;
	}
}
