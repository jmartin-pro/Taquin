package taquin.window.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class NewGameDialog extends AbstractDialog {

	private JTextField widthTextField;
	private JTextField heightTextField;

	private Integer widthSelected;
	private Integer heightSelected;

	public NewGameDialog(JFrame parent) {
		super(parent, "Nouvelle partie");
	}

	/**
	 * Création du contenu de la boite de dialogue lors de la création d'une nouvelle partie
	 * @return la boite de dialogue
	 */
	@Override
	protected JComponent getMainUI() {
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		//Création du label contenant l'écriture de "Largeur :"
		JLabel widthLabel = new JLabel("Largeur : ");
		c.gridx = 0;
		c.gridy = 0;
		mainContent.add(widthLabel, c);

		//Création du champ de saisie de la largeur
		widthTextField = new JTextField();
		widthTextField.setPreferredSize(new Dimension(200, 25));
		c.gridx = 1;
		c.gridy = 0;
		mainContent.add(widthTextField, c);

		//Création du label contenant l'écriture de "Hauteur :"
		JLabel heightLabel = new JLabel("Hauteur : ");
		c.gridx = 0;
		c.gridy = 1;
		mainContent.add(heightLabel, c);

		//Création du champ de saisie de la hauteur
		heightTextField = new JTextField();
		heightTextField.setPreferredSize(new Dimension(200, 25));
		c.gridx = 1;
		c.gridy = 1;
		mainContent.add(heightTextField, c);

		return mainContent;
	}

	/**
	 * Action émise après clic sue le bouton "Valider"
	 * @return ActionListener l'action émise
	 */
	@Override
	protected ActionListener getValidButtonEventHandler() {
		return (ActionEvent e) -> {
			//Vérification de la largeur mise par l'utilsateur correspond à un nombre
			try {
				this.widthSelected = Integer.parseInt(widthTextField.getText());
			} catch(NumberFormatException ex) {
				this.widthSelected = null;
			}

			//Vérification de la hauteur mise par l'utilsateur correspond à un nombre
			try {
				this.heightSelected = Integer.parseInt(heightTextField.getText());
			} catch(NumberFormatException ex) {
				this.heightSelected = null;
			}

			//On supprime la boite de dialogue après clic sue "Valider"
			NewGameDialog.this.dispose();
		};
	}

	/**
	 * Récupère la largeur sélectionnée par l'utilsiateur
	 */
	public Integer getSelectedWidth() {
		return this.widthSelected;
	}

	/**
	 * Récupère la heuteur sélectionnée par l'utilsiateur
	 */
	public Integer getSelectedHeight() {
		return this.heightSelected;
	}

}
