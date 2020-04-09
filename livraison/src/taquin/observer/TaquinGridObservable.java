package taquin.observer;

public interface TaquinGridObservable {

	/**
	 * Ajoute un observer à la liste des observers
	 * @param observer L'observer à ajouter
	 */
	public void addTaquinObserver(TaquinGridObserver observer);

	/**
	 * Supprime un observer à la liste des observers
	 * @param observer L'observer à supprimer
	 */
	public void removeTaquinObserver(TaquinGridObserver observer);

	/**
	 * Notifie les observers qu'un mouvement a été effectué
	 */
	public void notifyMoved();

}
