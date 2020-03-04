package taquin.observer;

public class TaquinGridObservable {

	public TaquinGridObservable() {

	}

	/**
	 * @param observer
	 * @return void
	 */
	public abstract void addTaquinObserver(TaquinGridObserver observer);

	/**
	 * @param observer
	 * @return void
	 */
	public abstract void removeTaquinObserver(TaquinGridObserver observer);

	/**
	 * @return void
	 */
	public abstract void notifyMoved();

}
