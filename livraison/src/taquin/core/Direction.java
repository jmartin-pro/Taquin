package taquin.core;

/**
 * Représente une direction pour un mouvement
 */
public enum Direction {
	BAS("Bas"), HAUT("Haut"), GAUCHE("Gauche"), DROITE("Droite");

	private String name;

	Direction(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
