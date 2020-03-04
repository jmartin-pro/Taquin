package taquin.observer;

public interface TaquinGridObservable {

	/**
	 * @param observer
	 * @return void
	 */
	public void addTaquinObserver(TaquinGridObserver observer);

	/**
	 * @param observer
	 * @return void
	 */
	public void removeTaquinObserver(TaquinGridObserver observer);

	/**
	 * @return void
	 */
	public void notifyMoved();

}
