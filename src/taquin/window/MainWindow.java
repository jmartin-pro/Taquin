package taquin.window;

import javax.swing.JFrame;

public class MainWindow {

	// private ImageTaquinGrid imageTaquinGrid;
	// private GUITaquinGrid guiTaquinGrid;

	public MainWindow(int width, int height, String name) {

		JFrame fenetre = new JFrame();
		fenetre.setTitle(name);
		fenetre.setSize(width, height);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}

	/**
	 * @return void
	 */
	private void createMenuBar() {
		// TODO Auto-generated method stub
	}

}
