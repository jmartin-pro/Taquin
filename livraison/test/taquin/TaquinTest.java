package taquin;

import taquin.core.TaquinGrid;
import taquin.core.Direction;

import org.junit.Assert;
import org.junit.Test;

public class TaquinTest {

	@Test
	public void randomizeGrid() {
		TaquinGrid gridRandomize = new TaquinGrid(10, 10);

      	Assert.assertFalse(gridRandomize.finished());
	}

	@Test
	public void move() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		int ancienneValeur;

		ancienneValeur = gridCreate.getSquare(9,8);
		Assert.assertTrue(gridCreate.move(Direction.BAS));
		Assert.assertTrue(gridCreate.getSquare(9,8) == TaquinGrid.EMPTY_SQUARE);
		Assert.assertTrue(gridCreate.getSquare(9,9) == ancienneValeur);

		ancienneValeur = gridCreate.getSquare(8,8);
		Assert.assertTrue(gridCreate.move(Direction.DROITE));
		Assert.assertTrue(gridCreate.getSquare(8,8) == TaquinGrid.EMPTY_SQUARE);
		Assert.assertTrue(gridCreate.getSquare(9,8) == ancienneValeur);

		ancienneValeur = gridCreate.getSquare(8,9);
		Assert.assertTrue(gridCreate.move(Direction.HAUT));
		Assert.assertTrue(gridCreate.getSquare(8,9) == TaquinGrid.EMPTY_SQUARE);
		Assert.assertTrue(gridCreate.getSquare(8,8) == ancienneValeur);

		ancienneValeur = gridCreate.getSquare(9,9);
		Assert.assertTrue(gridCreate.move(Direction.GAUCHE));
		Assert.assertTrue(gridCreate.getSquare(9,9) == TaquinGrid.EMPTY_SQUARE);
		Assert.assertTrue(gridCreate.getSquare(8,9) == ancienneValeur);
	}

	@Test
	public void finished() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		TaquinGrid gridRandomize = new TaquinGrid(10, 10);

		Assert.assertTrue(gridCreate.finished());
		Assert.assertFalse(gridRandomize.finished());
	}

	@Test
	public void getSquare() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		Assert.assertTrue(TaquinGrid.EMPTY_SQUARE == gridCreate.getSquare(9, 9));
	}

}
