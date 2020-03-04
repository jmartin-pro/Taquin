package taquin.core;

import java.util.Random;
import java.util.List;

public class TaquinGrid {
	private int[][] grid;
	private int width, height;
	private int posXVide, posYVide;

	//private List<TaquinGridObserver> taquinGridObserver;


	public TaquinGrid(int width, int height) {
		this.width = width;
		this.height = height;

		createGrid();
	}

	private void createGrid() {
		this.grid = new int[this.width][this.height];

		for(int y = 0; y <= this.height-1; y++){
			for(int x = 0; x <= this.width-1; x++){
				this.grid[x][y] = x+y*this.width+1;
			}
		}

		this.grid[this.width-1][this.height-1] = -1;

		this.posXVide = this.width-1;
		this.posYVide = this.height-1;

		//randomizeGrid();
	}

	public void randomizeGrid(){
		Random r = new Random();

		for (int i = 0; i <= 5000; i++){
			int nbrRandom = r.nextInt(4);
			if (nbrRandom == 0){
				if (!move(Direction.HAUT)){
					i -= 1;
				}
			}
			if (nbrRandom == 1){
				if(!move(Direction.DROITE)){
					i -= 1;
				}
			}
			if (nbrRandom == 2){
				if(!move(Direction.BAS)){
					i -= 1;
				}
			}
			if (nbrRandom == 3){
				if(!move(Direction.GAUCHE)){
					i -= 1;
				}
			}
		}




		System.out.println();

		for (int j = 0; j < this.height; j++){
			System.out.print(" | "); //affichage des numéros des lignes
			for (int i = 0; i < this.width; i++){
				System.out.print(this.grid[i][j]+" | ");
			}
			System.out.println();
			System.out.print(" ");
			for (int i = 0; i < this.height; i++){
				System.out.print(" - -");
			}
		System.out.println();
		}
	}

	public boolean move(Direction direction){
		if (direction == Direction.HAUT && this.posYVide == this.height-1){
			return false;
		} else if (direction == Direction.DROITE && this.posXVide == 0){
			return false;
		} else if (direction == Direction.BAS && this.posYVide == 0){
			return false;
		} else if (direction == Direction.GAUCHE && this.posXVide == this.width-1){
			return false;
		}

		if (direction == Direction.HAUT){
			this.grid[posXVide][posYVide] = this.grid[posXVide][posYVide+1];
			this.grid[posXVide][posYVide+1] = -1;

			this.posYVide++;
		} else if (direction == Direction.DROITE){
			this.grid[posXVide][posYVide] = this.grid[posXVide-1][posYVide];
			this.grid[posXVide-1][posYVide] = -1;

			this.posXVide--;
		} else if (direction == Direction.BAS){
			this.grid[posXVide][posYVide] = this.grid[posXVide][posYVide-1];
			this.grid[posXVide][posYVide-1] = -1;

			this.posYVide--;
		} else if (direction == Direction.GAUCHE){
			this.grid[posXVide][posYVide] = this.grid[posXVide+1][posYVide];
			this.grid[posXVide+1][posYVide] = -1;

			this.posXVide++;
		}

		return true;
	}

	public boolean finished(){
		for(int y = 0; y <= this.height-1; y++){
			for(int x = 0; x <= this.width-1; x++){
				if(y == this.height-1 && x == this.width - 1 && this.grid[x][y] == -1)
					break;

				if(this.grid[x][y] != x+y*this.width+1){
					return false;
				}
			}
		}
		return true;
	}

	public Integer getSquare(int x, int y){
		if (x > this.width-1 || y > this.height-1 || x < 0 || y < 0){
			return null;
		}
		return grid[x][y];
	}
}
