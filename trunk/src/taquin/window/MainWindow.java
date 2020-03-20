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

	/**
	 * Création de la fenêtre du jeu
	 * @param w la largeur de la fenêtre
	 * @param h la hauteur de la fenêtre
	 * @param name le nom de la fenêtre 
	 */
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
	 * Création de la barre de navigation
	 */
	private void createMenuBar() {
		//Création de la barre de navigation
		JMenuBar menuBar = new JMenuBar();

		//Création des catégories de la barre de navigation
		JMenu menuFichier = new JMenu("Jeu");
		JMenu menuAffichage = new JMenu("Mode");

		//Création de la sous-catégorie "nouvelle partie" de "jeu"
		JMenuItem itemNewGame = new JMenuItem("Nouvelle partie");
		menuFichier.add(itemNewGame);

		//Evenement de l'item "nouvelle partie"
		itemNewGame.addActionListener((ActionEvent e) -> {
			//Appel à la boite de dialogue permettant de sélectionner la taille du Taquin
			NewGameDialog dialog = new NewGameDialog(this);
			Integer taquinGridWidth = dialog.getSelectedWidth();
			Integer taquinGridHeight = dialog.getSelectedHeight();

			//On vérifie les tailles rentrées
			if(taquinGridWidth != null && taquinGridHeight != null) {
				if(taquinGridWidth > 2 && taquinGridWidth < 23 && taquinGridHeight > 2 && taquinGridHeight < 23) {
					//Dans le cas ou elles sont correctes, on créer le taquin avec ces tailles
					newGame(taquinGridWidth, taquinGridHeight);
				} else{
					//Sinon on affiche une erreur dans une boite de dialogue
					JOptionPane.showMessageDialog(this, "La largeur et la hauteur de la grille doivent être comprises entre 3 et 22", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//Création de la sous-catégorie "Mode image" de "Mode"
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
		//Création de la sous-catégorie "Changer l'image" de "Mode"
		JMenuItem itemChiffre = new JMenuItem("Changer l'image");
		menuAffichage.add(itemChiffre);
		//Evenement de l'item "mode chiffres"
		itemChiffre.addActionListener((ActionEvent e) -> {
			//Liste des extensions de fichier autorisées
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Fichiers image", "png", "bmp", "jpg", "jpeg");

			//Choix d'une image dans les dossiers de l'utilisateur
			JFileChooser imageChoosed = new JFileChooser();
			imageChoosed.setDialogTitle("Choisir une image");
			imageChoosed.setAcceptAllFileFilterUsed(false);
			imageChoosed.setFileFilter(imagesFilter);

			int returnVal = imageChoosed.showOpenDialog(MainWindow.this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				//On essaye de lire le fichier chois par l'utilisateur
				try {
					Image image = ImageIO.read(new File(imageChoosed.getSelectedFile().getPath()));
					imageTaquinGrid.setImage(image);
				} catch (IOException ex) {
					//On renvoie un message d'erreur si le fichier n'est pas valide
					JOptionPane.showMessageDialog(MainWindow.this, "Impossible d'ouvrir le fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//Ajout des catégories à la barre de navigation
		menuBar.add(menuFichier);
		menuBar.add(menuAffichage);

		setJMenuBar(menuBar);
	}

	/**
	 * Création du contenu de la fenêtre de jeu
	 */
	private void createMainUI() {
		setLayout(new CardLayout());
		newGame(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);

		//On ajoute l'ensemble des calques, un JPanel et son nom
		this.add(imageTaquinGrid, IMAGE_GRID);
		this.add(numberTaquin, NUMBER_GRID);

		showTaquinGrid(IMAGE_GRID);
	}

	/**
	 * Lancement d'une nouvelle partie
	 * @param w la largeur de la grille du taquin
	 * @param h la hauteur de la grille du taquin
	 */
	protected void newGame(int w, int h) {
		this.taquinGrid = new TaquinGrid(w, h);
		this.taquinGrid.addTaquinObserver(this);

		//Création du mode "Nombre" s'il existe pas
		if (numberTaquin == null) {
			numberTaquin = new NumberTaquinGrid(this.taquinGrid);
		} else {
			numberTaquin.setTaquinGrid(this.taquinGrid);
		}
		//Création du mode "Image" s'il existe pas
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

	/**
	 * Affichage du jeu s'il s'agit du mode "Image" ou "Nombre"
	 * @param gridType le type d'affichage
	 */
	private void showTaquinGrid(String gridType) {
		CardLayout cl = (CardLayout)(getContentPane().getLayout());
		cl.show(getContentPane(), gridType);

		if(gridType == NUMBER_GRID) {
			numberTaquin.requestFocus();
		} else if(gridType == IMAGE_GRID) {
			imageTaquinGrid.requestFocus();
		}
	}

	/**
	 * Permet d'afficher une fenêtre de victoire au moment où le joueur à gagné
	 */
	@Override
	public void moved() {
		if (this.taquinGrid.finished() == true){
			//Affichage du taquin au moment où il est gagné
			imageTaquinGrid.repaint();
			numberTaquin.repaint();

			//Affichage de la boite de dialogue
			int answer = JOptionPane.showConfirmDialog(this,"Vous avez gagné ! Voulez vous rejouer ?", "VICTOIRE !", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				this.taquinGrid.randomizeGrid();
			}
		}
	}

}
