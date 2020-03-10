package taquin.component;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import taquin.observer.TaquinGridObserver;
import taquin.core.TaquinGrid;

public class AbstractGUITaquinGrid extends JPanel implements TaquinGridObserver {

	private TaquinGrid taquinGrid;
	private int xMouse;
	private int yMouse;

	public AbstractGUITaquinGrid(TaquinGrid taquinGrid) {
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		/*addKeyListener(new KeyListener() {

		public void keyTyped(KeyEvent k) {
			System.out.println("va dans keyTyped");
		}


		public void keyPressed(KeyEvent k) {
			System.out.println("va dans keyPressed");
			if(k.getKeyCode() == KeyEvent.VK_DOWN){
				System.out.println("bas");
	        	//Déplacement bas
				repaint();
	        } else if(k.getKeyCode() ==  KeyEvent.VK_RIGHT){
				System.out.println("droite");
	            //Deplacement droite
	            repaint();
	        }
			else if(k.getKeyCode() == KeyEvent.VK_UP){
				System.out.println("haut");
	            //Deplacement haut
	            repaint();
	        }
			else if(k.getKeyCode() == KeyEvent.VK_LEFT){
				System.out.println("gauche");
	            //Deplacement gauche
	        	repaint();
	        }
		}


		public void keyReleased(KeyEvent k) {
			System.out.println("va dans keyRealeased");
		}
	});*/
		this.taquinGrid = taquinGrid;
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

	@Override
	public void moved() {
		this.repaint();
	}

	public TaquinGrid getTaquinGrid() {
		return this.taquinGrid;
	}

	public void setTaquinGrid(TaquinGrid taquinGrid) {
		this.taquinGrid = taquinGrid;
	}

	public int getXMouse() {
		return this.xMouse;
	}

	public void setXMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public int getYMouse() {
		return this.yMouse;
	}

	public void setYMouse(int yMouse) {
		this.yMouse = yMouse;
	}
}
