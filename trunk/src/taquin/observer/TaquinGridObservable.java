package taquin.observer;

public interface TaquinGridObservable {

	/**
	 * Ajoute un observer à la liste des observer
	 * @param observer un observer
	 */
	public void addTaquinObserver(TaquinGridObserver observer);

	/**
	 * Supprime un observer à la liste des observer
	 * @param observer un observer
	 */
	public void removeTaquinObserver(TaquinGridObserver observer);

	/**
	 * Notifie un mouvement
	 */
	public void notifyMoved();

}
