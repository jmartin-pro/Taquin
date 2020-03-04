package taquin.core;

public class TaquinGrid implements TaquinGridObservable {
	private int[] grid;
	private int width;
	private int height;
	private List<TaquinGridObserver> taquinGridObservers;

	public TaquinGrid() {

	}

	/**
	 * @param w
	 * @param h
	 * @return void
	 */
	public void TaquinGrid(int w, int h) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param w
	 * @param h
	 * @return void
	 */
	private void createGrid(int w, int h) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return void
	 */
	public void randomizeGrid() {
		// TODO Auto-generated method stub
	}

	/**
	 * Boolean true si deplacement effectué, false sinon
	 * @param direction
	 * @return boolean
	 */
	public boolean move(Direction direction) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return boolean
	 */
	public boolean finished() {
		// TODO Auto-generated method stub
	}

	/**
	 * @param x
	 * @param
	 * @return int
	 */
	public int getSquare(int x, int y a) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param observer
	 * @return void
	 */
	public abstract void addTaquinObserver(TaquinGridObserver observer);

}
