package taquin;

import javax.swing.*;

import taquin.component.ConsoleTaquinGrid;
import taquin.window.dialog.NewGameDialog;
import taquin.window.*;

public class Main {

	public static void main(String[] args) {
		int w = 0;
		int h = 0;
		//Si on a 2 arguments (correspondant à la hauteur et la largeur du taquin), on lance le mode console
		if(args.length == 2) {

			//Vértifie si les arguments envoyés sont bien des int
			try {
				w = Integer.parseInt(args[0]);
				h = Integer.parseInt(args[1]);

			} catch(NumberFormatException ex) {
				System.out.println("Arguments incorrects");
				System.exit(1);
			}

			//Création du taquin en mode console
			ConsoleTaquinGrid consoleTaquinGrid = new ConsoleTaquinGrid(w, h);
			consoleTaquinGrid.newGame();

		}
		//Si il y a pas d'arguments, on lance le jeu en interface graphique
		else if(args.length == 0) {
			MainWindow window = new MainWindow(800,800,"Taquin game");

		}
		//Si le nombre d'arguments ne correspond pas
		else {
			System.out.println("./build.sh run --args '<width> <heigth>'");
		}

	}

}
