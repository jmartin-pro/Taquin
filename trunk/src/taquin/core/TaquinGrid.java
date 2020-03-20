package taquin.core;

import taquin.observer.TaquinGridObservable;
import taquin.observer.TaquinGridObserver;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TaquinGrid {

	public static int EMPTY_SQUARE = -1;

	private int[][] grid;
	private int width, height;
	private int posXVide, posYVide;

	private boolean shouldNotify;

	private List<TaquinGridObserver> taquinGridObserver;

	public TaquinGrid(int width, int height) {
		this.width = width;
		this.height = height;
		this.shouldNotify = true;

		taquinGridObserver = new ArrayList<>();

		createGrid();
	}
	/**
	  * Création de la grille du taquin
	  */
	private void createGrid() {
		this.grid = new int[this.width][this.height];

		//Affectation d'un chiffre à chaque case du Taquin
		for(int y = 0 ; y < this.height ; y++) {
			for(int x = 0 ; x < this.width ; x++) {
				this.grid[x][y] = x+y*this.width+1;
			}
		}
		//Création de la case vide
		this.grid[this.width-1][this.height-1] = EMPTY_SQUARE;

		this.posXVide = this.width-1;
		this.posYVide = this.height-1;

		randomizeGrid();
	}

	/**
	  * Création de la grille du taquin
	  */
	public void randomizeGrid() {
		randomizeGrid(width * height * 100);
	}

	/**
	  * Mélange les cases du Taquin selon un nombre de déplacements aléatoires
	  * @param n le nombre de mouvements
	  */
	public void randomizeGrid(int n) {
		this.shouldNotify = false;
		Random r = new Random();

		//Déplacement des cases un certain nombre de fois
		for (int i = 0; i < n; i++) {
			int nbrRandom = r.nextInt(4);
			Direction dir = null;

			//Définition du déplacement selon le nombre tiré aléatoirement
			if (nbrRandom == 0) {
				dir = Direction.HAUT;
			} else if (nbrRandom == 1) {
				dir = Direction.DROITE;
			} else if (nbrRandom == 2) {
				dir = Direction.BAS;
			} else if (nbrRandom == 3) {
				dir = Direction.GAUCHE;
			}

			//Si le mouvement n'est pas possible, on recommence l'étape
			if (!move(dir)) {
				i -= 1;
			}
		}
		//On re-génère les déplacements dans le cas où la grille est la même qu'à l'état initial
		if (finished()){
			randomizeGrid(n );
		}

		this.shouldNotify = true;
	}

	/**
	 * Déplacement d'un case
	 * @param direction une direction donnée
	 * @return true si le mouvement à été effectué, fakse sinon
	 */
	public boolean move(Direction direction) {
		if (direction == Direction.HAUT && this.posYVide == this.height-1) {
			return false;
		} else if (direction == Direction.DROITE && this.posXVide == 0) {
			return false;
		} else if (direction == Direction.BAS && this.posYVide == 0) {
			return false;
		} else if (direction == Direction.GAUCHE && this.posXVide == this.width-1) {
			return false;
		}

		if (direction == Direction.HAUT) {
			this.grid[posXVide][posYVide] = this.grid[posXVide][posYVide+1];
			this.grid[posXVide][posYVide+1] = EMPTY_SQUARE;

			this.posYVide++;
		} else if (direction == Direction.DROITE) {
			this.grid[posXVide][posYVide] = this.grid[posXVide-1][posYVide];
			this.grid[posXVide-1][posYVide] = EMPTY_SQUARE;

			this.posXVide--;
		} else if (direction == Direction.BAS) {
			this.grid[posXVide][posYVide] = this.grid[posXVide][posYVide-1];
			this.grid[posXVide][posYVide-1] = EMPTY_SQUARE;

			this.posYVide--;
		} else if (direction == Direction.GAUCHE) {
			this.grid[posXVide][posYVide] = this.grid[posXVide+1][posYVide];
			this.grid[posXVide+1][posYVide] = EMPTY_SQUARE;

			this.posXVide++;
		}

		notifyMoved();

		return true;
	}
	/**
	 * Vérifie si le jeu est fini
	 * @return true si le jeu est fini, false sinon
	 */
	public boolean finished() {
		for(int y = 0 ; y < this.height ; y++) {
			for(int x = 0 ; x < this.width ; x++) {
				if(y == this.height-1 && x == this.width - 1 && this.grid[x][y] == EMPTY_SQUARE)
					continue;

				if(this.grid[x][y] != x+y*this.width+1) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Ajout de l'observer de la liste d'observer
	 * @param observer un observer
	 */
	public void addTaquinObserver(TaquinGridObserver observer) {
		taquinGridObserver.add(observer);
	}

	/**
	 * Supression de l'observer de la liste d'observer
	 * @param observer
	 * @return void
	 */
	public void removeTaquinObserver(TaquinGridObserver observer) {
		taquinGridObserver.remove(observer);
	}

	/**
	 * Notifier lorsqu'il y a un mouvement
	 */
	public void notifyMoved() {
		if(!this.shouldNotify)
			return;

		for(TaquinGridObserver observer : taquinGridObserver) {
			observer.moved();
		}
	}

	/**
	 * Récupère le numéro de la case
	 * @param x la position x de la case
	 * @param y la position y de la case
	 * @return le numéro de la case
	 */
	public Integer getSquare(int x, int y) {
		if (x > this.width-1 || y > this.height-1 || x < 0 || y < 0) {
			return null;
		}

		return grid[x][y];
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public int getPosXVide() {
		return this.posXVide;
	}

	public int getPosYVide() {
		return this.posYVide;
	}
}
