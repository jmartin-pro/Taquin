package taquin.window;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.CardLayout;

import java.awt.event.ActionEvent;

import taquin.core.TaquinGrid;
import taquin.window.dialog.*;
import taquin.component.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static final String NUMBER_GRID = "NUMBER";
	private static final String IMAGE_GRID = "IMAGE";
	// private ImageTaquinGrid imageTaquinGrid;
	// private GUITaquinGrid guiTaquinGrid;

	JPanel imagePanel = new JPanel();
	NumberTaquinGrid numberTaquin;

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
			Integer width = dialog.getSelectedWidth();
			Integer heigth = dialog.getSelectedHeight();
		});

		JMenuItem itemImage = new JMenuItem("Mode image");
		menuAffichage.add(itemImage);
		//Evenement de l'item "mode image"
		itemImage.addActionListener((ActionEvent e) -> {
			showTaquinGrid(IMAGE_GRID);
		});

		JMenuItem itemChiffre = new JMenuItem("Mode chiffres");
		menuAffichage.add(itemChiffre);
		//Evenement de l'item "mode chiffres"
		itemChiffre.addActionListener((ActionEvent e) -> {
			showTaquinGrid(NUMBER_GRID);
		});

		menuBar.add(menuFichier);
		menuBar.add(menuAffichage);

		setJMenuBar(menuBar);
	}

	private void createMainUI() {
			setLayout(new CardLayout());
			TaquinGrid taquinGrid = new TaquinGrid(5,5);
			NumberTaquinGrid numberTaquin = new NumberTaquinGrid(taquinGrid);
			//On ajoute l'ensemble des calques, un JPanel et son nom
			this.add(imagePanel, IMAGE_GRID);
			this.add(numberTaquin, NUMBER_GRID);
			imagePanel.setBackground(Color.RED);
	}

	private void showTaquinGrid(String gridType) {
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
		cl.show(getContentPane(), gridType);
	}



}
