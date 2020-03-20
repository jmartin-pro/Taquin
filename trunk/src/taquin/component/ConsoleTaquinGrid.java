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
	 * Démarre une nouvelle partie
	 */
	public void newGame() {
		//Le temps du mélange on n'écoute plus les événements.
		this.taquinGrid.removeTaquinObserver(this);
		//Mélange du taquin
		this.taquinGrid.randomizeGrid();
		//Réécoute les événements
		this.taquinGrid.addTaquinObserver(this);
		//Affichage de la grille
		this.showGrid();

		//On joue tant que la partie n'est pas terminée
		while(!this.taquinGrid.finished()) {
			//Choix du mouvement
			Direction dir = makeAMove();
			System.out.println();
			System.out.println("=================================");
			System.out.println();

			//Précise dans le cas où le mouvement n'est pas valide
			if(!this.taquinGrid.move(dir)) {
				System.out.println("Action invalide");
			}

		}

		System.out.println("Félicitation vous avez gagné!");
	}

	/**
	 * Choix du mouvement
	 * @return la direction valide choisie
	 */
	private Direction makeAMove() {
		//Affichage des directions
		for(int i = 0 ; i < Direction.values().length ; i++) {
			System.out.println((i+1) + " - " + Direction.values()[i]);
		}

		//Entrée clavier de l'utilisateur du mouvement
		Scanner input = new Scanner(System.in);
		int dirIndex = input.nextInt();

		//Vérification du mouvement choisi si il est possible
		if(dirIndex < 1 || dirIndex > Direction.values().length) {
			System.out.println("Merci de renseigner une action valide");
			System.out.println();

			return makeAMove();
		}

		return Direction.values()[dirIndex-1];
	}

	/**
	 * Affichage du taquin  après mouvement
	 */
	@Override
	public void moved() {
		showGrid();
	}

	/**
	 * Affichage du taquin
	 */
	public void showGrid(){
		for (int y = 0 ; y < taquinGrid.getHeight() ; y++) {
			System.out.print(" | ");
			for (int x = 0 ; x < taquinGrid.getWidth() ; x++) {
				System.out.print(taquinGrid.getSquare(x, y) +" | ");
			}

			System.out.println();
		}

		System.out.println();
	}
}
