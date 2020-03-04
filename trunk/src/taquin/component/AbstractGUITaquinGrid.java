package taquin.component;

import javax.swing.JPanel;

import taquin.observer.TaquinGridObserver;
import taquin.core.TaquinGrid;

@SuppressWarnings("serial")
public class AbstractGUITaquinGrid extends JPanel implements TaquinGridObserver {
	private TaquinGrid taquinGrid;
	private int xMouse;
	private int yMouse;

	public AbstractGUITaquinGrid() {

	}

	@Override
	public void moved() {

	}

}
