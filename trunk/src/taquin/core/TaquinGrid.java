package taquin.core;

import taquin.observer.TaquinGridObservable;
import taquin.observer.TaquinGridObserver;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class TaquinGrid {

	public static int EMPTY_SQUARE = -1;

	private int[][] grid;
	private int width, height;
	private int posXVide, posYVide;

	private List<TaquinGridObserver> taquinGridObserver;

	public TaquinGrid(int width, int height) {
		this.width = width;
		this.height = height;

		taquinGridObserver = new ArrayList<>();

		createGrid();
	}

	private void createGrid() {
		this.grid = new int[this.width][this.height];

		for(int y = 0 ; y < this.height ; y++) {
			for(int x = 0 ; x < this.width ; x++) {
				this.grid[x][y] = x+y*this.width+1;
			}
		}

		this.grid[this.width-1][this.height-1] = EMPTY_SQUARE;

		this.posXVide = this.width-1;
		this.posYVide = this.height-1;

		randomizeGrid();
	}

	public void randomizeGrid() {
		randomizeGrid(1000);
	}

	public void randomizeGrid(int n) {
		Random r = new Random();

		for (int i = 0; i < n; i++) {
			int nbrRandom = r.nextInt(4);
			Direction dir = null;

			if (nbrRandom == 0) {
				dir = Direction.HAUT;
			} else if (nbrRandom == 1) {
				dir = Direction.DROITE;
			} else if (nbrRandom == 2) {
				dir = Direction.BAS;
			} else if (nbrRandom == 3) {
				dir = Direction.GAUCHE;
			}

			if (!move(dir)) {
				i -= 1;
			}
		}

		if (finished()){
			randomizeGrid(n);
		}
	}

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

	public boolean finished() {
		for(int y = 0 ; y < this.height ; y++) {
			for(int x = 0 ; x < this.width ; x++) {
				if(y == this.height-1 && x == this.width - 1 && this.grid[x][y] == EMPTY_SQUARE)
					break;

				if(this.grid[x][y] != x+y*this.width+1) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @param observer
	 * @return void
	 */
	public void addTaquinObserver(TaquinGridObserver observer) {
		taquinGridObserver.add(observer);
	}

	/**
	 * @param observer
	 * @return void
	 */
	public void removeTaquinObserver(TaquinGridObserver observer) {
		taquinGridObserver.remove(observer);
	}

	/**
	 * @return void
	 */
	public void notifyMoved() {
		for(TaquinGridObserver observer : taquinGridObserver) {
			observer.moved();
		}
	}

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
}
