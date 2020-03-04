package taquin.component;

import taquin.core.TaquinGrid;
import taquin.core.Direction;
import taquin.observer.TaquinGridObserver;

import java.util.Scanner;

public class ConsoleTaquinGrid implements TaquinGridObserver {

	private TaquinGrid taquinGrid;

	public ConsoleTaquinGrid(int w, int h) {
		this(new TaquinGrid(w, h));
	}

	public ConsoleTaquinGrid(TaquinGrid taquinGrid) {
		this.taquinGrid = taquinGrid;

		this.taquinGrid.addTaquinObserver(this);
	}

	/**
	 * @return void
	 */
	public void newGame() {
		this.taquinGrid.randomizeGrid();

		while(!this.taquinGrid.finished()) {
			Direction dir = makeAMove();
			if(!this.taquinGrid.move(dir)) {
				System.out.println("Action invalide");
			}
		}

		System.out.println("Félicitation vous avez gagné!");
	}

	/**
	 * @return Direction
	 */
	private Direction makeAMove() {
		for(int i = 0 ; i < Direction.values().length ; i++) {
			System.out.println((i+1) + " - " + Direction.values()[i]);
		}

		Scanner input = new Scanner(System.in);
		int dirIndex = input.nextInt();

		if(dirIndex < 1 || dirIndex > Direction.values().length) {
			System.out.println("Merci de renseigner une action valide");
			System.out.println();

			return makeAMove();
		}

		return Direction.values()[dirIndex-1];
	}

	@Override
	public void moved() {
		for (int y = 0 ; y < taquinGrid.getHeight() ; y++) {
			System.out.print(" | ");
			for (int x = 0 ; x < taquinGrid.getWidth() ; x++) {
				System.out.print(taquinGrid.getSquare(x, y) +" | ");
			}

			System.out.println();
		}

		System.out.println();
		System.out.println();
		System.out.println();
	}
}
