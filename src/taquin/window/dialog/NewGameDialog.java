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

	@Override
	protected ActionListener getValidButtonEventHandler() {
		return (ActionEvent e) -> {
			//Vérification que la largeur mise par l'utilsateur correspond bien à un nombre
			try {
				this.widthSelected = Integer.parseInt(widthTextField.getText());
			} catch(NumberFormatException ex) {
				this.widthSelected = null;
			}

			//Vérification que la hauteur mise par l'utilsateur correspond bien à un nombre
			try {
				this.heightSelected = Integer.parseInt(heightTextField.getText());
			} catch(NumberFormatException ex) {
				this.heightSelected = null;
			}

			//On supprime la boite de dialogue après clic sur "Valider"
			NewGameDialog.this.dispose();
		};
	}

	/**
	 * Récupère la largeur sélectionnée par l'utilisateur
	 */
	public Integer getSelectedWidth() {
		return this.widthSelected;
	}

	/**
	 * Récupère la hauteur sélectionnée par l'utilisateur
	 */
	public Integer getSelectedHeight() {
		return this.heightSelected;
	}

}
