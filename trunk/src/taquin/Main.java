package taquin;

import javax.swing.*;

import taquin.component.ConsoleTaquinGrid;
import taquin.window.dialog.NewGameDialog;
import taquin.window.*;

public class Main {

	public static void main(String[] args) {
		/*ConsoleTaquinGrid consoleTaquinGrid = new ConsoleTaquinGrid(2, 2);
		consoleTaquinGrid.newGame();*/

		MainWindow window = new MainWindow(800,820,"Taquin game");
	}

}
