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

		//Test du mouvement Direction.Bas
		Assert.assertTrue(gridCreate.move(Direction.BAS));
		//Test du mouvement bien effectué
		Assert.assertTrue(gridCreate.getSquare(9,8) == TaquinGrid.EMPTY_SQUARE);

		Assert.assertTrue(gridCreate.move(Direction.DROITE));
		Assert.assertTrue(gridCreate.getSquare(8,8) == TaquinGrid.EMPTY_SQUARE);

		Assert.assertTrue(gridCreate.move(Direction.HAUT));
		Assert.assertTrue(gridCreate.getSquare(8,9) == TaquinGrid.EMPTY_SQUARE);

		Assert.assertTrue(gridCreate.move(Direction.GAUCHE));
		Assert.assertTrue(gridCreate.getSquare(9,9) == TaquinGrid.EMPTY_SQUARE);
	}

	@Test
	public void fnished() {
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
