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
		this.setBackground(Color.GREEN);
	}





	@Override
	public void moved() {
		System.out.println("la sourie");
	}


}
