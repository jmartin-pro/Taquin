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

	private int mousePressedX;
	private int mousePressedY;

	public AbstractGUITaquinGrid(TaquinGrid taquinGrid) {
		addMouseMotionListener(new MouseMotionListener() {
			//On récupère la positon x et y de la souris
			@Override
			public void mouseMoved(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();

				// On redessine au cas où on se trouve sur une nouvelle case
				repaint();
			}
			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});
		//Évènements de la souris
		addMouseListener(this);

		//Permet d'obtenir le focus pour les événements claviers
		setFocusable(true);

		//Évènements du clavier
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent k) {
			}

			//On déplace une case selon la touche du clavier sélectionnée
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == KeyEvent.VK_DOWN){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.BAS);
				}
				else if(k.getKeyCode() ==  KeyEvent.VK_RIGHT){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.DROITE);
				}
				else if(k.getKeyCode() == KeyEvent.VK_UP){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.HAUT);
				}
				else if(k.getKeyCode() == KeyEvent.VK_LEFT){
					AbstractGUITaquinGrid.this.taquinGrid.move(Direction.GAUCHE);
				}

				repaint();
			}

			public void keyReleased(KeyEvent k) {
			}
		});

		this.taquinGrid = taquinGrid;
		this.taquinGrid.addTaquinObserver(this);
	}

	/**
	 * Récupère la taille d'une case
	 * @return la taille de la case
	 */
	protected int getCellSize() {
		return Math.min(getHeight() / getTaquinGrid().getHeight(), getWidth() / getTaquinGrid().getWidth());
	}

	/**
	 * Dessine la grille
	 * @param g l'objet avec lequel on dessine
	 */
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

	/**
	 * Dessine la case sélectionnée
	 * @param g l'objet avec lequel on dessine
	 */
	protected void drawSelectedSquare(Graphics g) {
		int cellSize = getCellSize();

		// Passage en alpha pour la couleur
		Graphics2D g2d = (Graphics2D) g;
		Composite originalComposite = g2d.getComposite();

		float alpha = 0.50f;
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(composite);

		g2d.setPaint(Color.WHITE);

		// On récupère la case sur laquelle se trouve la souris et on dessine un "overlay" blanc
		int mouseCaseX = getXMouse() / cellSize;
		int mouseCaseY = getYMouse() / cellSize;
		if(isCaseValid(mouseCaseX, mouseCaseY))
			g2d.fillRect(mouseCaseX * cellSize, mouseCaseY * cellSize, cellSize, cellSize);

		// Retour à une couleur sans alpha
		g2d.setComposite(originalComposite);
	}

	/**
	 * Vérifie si la case peur être déplacée
	 *	@param x la coordonnée x de la case
	 *	@param y la coordonnée y de la case
	 *	@return true si la case peut être déplacée, false sinon
	 */
	protected boolean isCaseValid(int x, int y) {
		int xPosVide = taquinGrid.getPosXVide();
		int yPosVide = taquinGrid.getPosYVide();

		return (x == xPosVide + 1 || x == xPosVide - 1) && y == yPosVide && x < taquinGrid.getWidth()
			|| (y == yPosVide + 1 || y == yPosVide - 1) && x == xPosVide && y < taquinGrid.getHeight();
	}

	/**
	 * Récupère les coordonnées de la case séléctionnée
	 * @param e l'événement clic de la souris
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		this.mousePressedX = e.getX()/getCellSize();
		this.mousePressedY = e.getY()/getCellSize();
	}

	/**
	 * Déplace la case séléctionnée après relachement de la souris
	 * @param e l'événement clic de la souris relaché
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		int cellSize = getCellSize();

		int mouseReleasedX = e.getX()/cellSize;
		int mouseReleasedY = e.getY()/cellSize;

		//On vérifie si la case où la souris est relachée, est la même que la case cliquée
		if (mouseReleasedX == this.mousePressedX && mouseReleasedY == this.mousePressedY){

			int mouseCaseX = getXMouse() / cellSize;
			int mouseCaseY = getYMouse() / cellSize;
			int xPosVide = this.taquinGrid.getPosXVide();
			int yPosVide = this.taquinGrid.getPosYVide();

			//Déplacement de la case
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
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * Redessine l'affichage après un mouvement
	 */
	@Override
	public void moved() {
		this.repaint();
	}

	/**
	 *	Change le modèle du taquin
	 *	@param taquinGrid le taquin
	 */
	public void setTaquinGrid(TaquinGrid taquinGrid) {
		this.taquinGrid.removeTaquinObserver(this);
		this.taquinGrid = taquinGrid;
		this.taquinGrid.addTaquinObserver(this);
	}

	/**
	 * Récupère le modèle du taquin
	 */
	public TaquinGrid getTaquinGrid() {
		return this.taquinGrid;
	}

	/**
	 * Récupère la position en X de la souris
	 */
	public int getXMouse() {
		return this.xMouse;
	}

	/**
	 * Récupère la position en Y de la souris
	 */
	public int getYMouse() {
		return this.yMouse;
	}
}
