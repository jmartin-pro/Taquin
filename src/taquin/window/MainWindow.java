package taquin.window;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;

import taquin.window.dialog.*;
import taquin.component.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	// private ImageTaquinGrid imageTaquinGrid;
	// private GUITaquinGrid guiTaquinGrid;

	public MainWindow(int w, int h, String name) {

		this.setTitle(name);
		this.setSize(w, h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createMenuBar();
		createMainUI();

		//this.pack();
		this.setVisible(true);
	}

	/**
	 * @return void
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFichier = new JMenu("Fichier");
		JMenu menuAffichage = new JMenu("Mode");

		JMenuItem itemNewGame = new JMenuItem("Nouvelle partie");
		menuFichier.add(itemNewGame);
		//Evenement de l'item "nouvele partie"
		itemNewGame.addActionListener((ActionEvent e) -> {
			NewGameDialog dialog = new NewGameDialog(this);
			int width = dialog.getSelectedWidth();
			int heigth = dialog.getSelectedHeight();
		});

		JMenuItem itemImage = new JMenuItem("Mode image");
		menuAffichage.add(itemImage);
		//Evenement de l'item "mode image"
		itemNewGame.addActionListener((ActionEvent e) -> {
			ImageTaquinGrid imageTaquin = new ImageTaquinGrid();
		});

		JMenuItem itemChiffre = new JMenuItem("Mode chiffres");
		menuAffichage.add(itemChiffre);
		//Evenement de l'item "mode chiffres"
		itemNewGame.addActionListener((ActionEvent e) -> {
			NumberTaquinGrid numberTaquin = new NumberTaquinGrid();
		});

		menuBar.add(menuFichier);
		menuBar.add(menuAffichage);

		setJMenuBar(menuBar);
	}

	private void createMainUI() {
	}



}
