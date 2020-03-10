package taquin;

import javax.swing.*;

import taquin.component.ConsoleTaquinGrid;
import taquin.window.dialog.NewGameDialog;
import taquin.window.*;

public class Main {

	public static void main(String[] args) {
		int w = 0;
		int h = 0;

		if(args.length == 2) {
			try {
				w = Integer.parseInt(args[0]);
				h = Integer.parseInt(args[1]);

			} catch(NumberFormatException ex) {
				System.out.println("Arguments incorrects");
				System.exit(1);
			}

			ConsoleTaquinGrid consoleTaquinGrid = new ConsoleTaquinGrid(w, h);
			consoleTaquinGrid.newGame();

		} else if(args.length == 0) {
			MainWindow window = new MainWindow(800,800,"Taquin game");

		} else {
			System.out.println("./build.sh run --args '<width> <heigth>'");
		}

	}

}
