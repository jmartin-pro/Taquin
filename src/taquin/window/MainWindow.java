package taquin.window;

import java.io.*;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import taquin.core.TaquinGrid;
import taquin.component.*;
import taquin.observer.TaquinGridObserver;
import taquin.window.dialog.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements TaquinGridObserver {

	private static final String NUMBER_GRID = "NUMBER";
	private static final String IMAGE_GRID = "IMAGE";

	private static final int DEFAULT_GRID_WIDTH = 5;
	private static final int DEFAULT_GRID_HEIGHT = 5;

	private ImageTaquinGrid imageTaquinGrid;
	private NumberTaquinGrid numberTaquin;

	private TaquinGrid taquinGrid;


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

		JMenu menuFichier = new JMenu("Jeu");
		JMenu menuAffichage = new JMenu("Mode");

		JMenuItem itemNewGame = new JMenuItem("Nouvelle partie");
		menuFichier.add(itemNewGame);
		//Evenement de l'item "nouvele partie"
		itemNewGame.addActionListener((ActionEvent e) -> {
			NewGameDialog dialog = new NewGameDialog(this);
			Integer taquinGridWidth = dialog.getSelectedWidth();
			Integer taquinGridHeight = dialog.getSelectedHeight();

			if(taquinGridWidth != null && taquinGridHeight != null) {
				if(taquinGridWidth > 2 && taquinGridWidth < 23 && taquinGridHeight > 2 && taquinGridHeight < 23) {
					newGame(taquinGridWidth, taquinGridHeight);
				} else{
					JOptionPane.showMessageDialog(this, "La largeur et la hauteur de la grille doivent être comprises entre 3 et 22", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JCheckBoxMenuItem itemImage = new JCheckBoxMenuItem("Mode image");
		itemImage.setState(true);
		menuAffichage.add(itemImage);
		//Evenement de l'item "mode image"
		itemImage.addItemListener((ItemEvent e) -> {
			if(itemImage.getState())
				showTaquinGrid(IMAGE_GRID);
			else
				showTaquinGrid(NUMBER_GRID);
		});

		JMenuItem itemChiffre = new JMenuItem("Changer l'image");
		menuAffichage.add(itemChiffre);
		//Evenement de l'item "mode chiffres"
		itemChiffre.addActionListener((ActionEvent e) -> {
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Fichiers image", "png", "bmp", "jpg", "jpeg");

			JFileChooser imageChoosed = new JFileChooser();
			imageChoosed.setDialogTitle("Choisir une image");
			imageChoosed.setAcceptAllFileFilterUsed(false);
			imageChoosed.setFileFilter(imagesFilter);

			int returnVal = imageChoosed.showOpenDialog(MainWindow.this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					Image image = ImageIO.read(new File(imageChoosed.getSelectedFile().getPath()));
					imageTaquinGrid.setImage(image);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(MainWindow.this, "Impossible d'ouvrir le fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		menuBar.add(menuFichier);
		menuBar.add(menuAffichage);

		setJMenuBar(menuBar);
	}

	private void createMainUI() {
		setLayout(new CardLayout());
		newGame(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);

		//On ajoute l'ensemble des calques, un JPanel et son nom
		this.add(imageTaquinGrid, IMAGE_GRID);
		this.add(numberTaquin, NUMBER_GRID);

		showTaquinGrid(IMAGE_GRID);
	}

	protected void newGame(int w, int h) {
		this.taquinGrid = new TaquinGrid(w, h);
		this.taquinGrid.addTaquinObserver(this);

		if (numberTaquin == null) {
			numberTaquin = new NumberTaquinGrid(this.taquinGrid);
		} else {
			numberTaquin.setTaquinGrid(this.taquinGrid);
		}

		if (imageTaquinGrid == null) {
			try {
				imageTaquinGrid = new ImageTaquinGrid(this.taquinGrid, ImageIO.read(new File("res/default.jpg")));
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(MainWindow.this, "Impossible de charger l'image par défaut", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			imageTaquinGrid.setTaquinGrid(this.taquinGrid);
		}

		imageTaquinGrid.repaint();
		numberTaquin.repaint();
	}

	private void showTaquinGrid(String gridType) {
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
		cl.show(getContentPane(), gridType);

		if(gridType == NUMBER_GRID) {
			numberTaquin.requestFocus();
		} else if(gridType == IMAGE_GRID) {
			imageTaquinGrid.requestFocus();
		}
	}

	@Override
	public void moved() {
		if (this.taquinGrid.finished() == true){
			imageTaquinGrid.repaint();
			numberTaquin.repaint();

			int answer = JOptionPane.showConfirmDialog(this,"Vous avez gagné ! Voulez vous rejouer ?", "VICTOIRE !", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				this.taquinGrid.randomizeGrid();
			}
		}
	}

}
