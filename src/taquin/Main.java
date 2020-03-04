package taquin;

import javax.swing.*;
import taquin.window.dialog.NewGameDialog;
import taquin.window.*;

public class Main {

	public static void main(String[] args) {
		MainWindow window = new MainWindow(800,800,"Taquin game");
		new NewGameDialog(new JFrame());
	}

}
