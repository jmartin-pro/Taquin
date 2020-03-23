package taquin;

import taquin.core.TaquinGrid;
import taquin.core.Direction;

import org.junit.Assert;
import org.junit.Test;

public class TaquinTest {

	@Test
	public void melangeTrue() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		TaquinGrid gridRandomize = new TaquinGrid(10, 10);

      	Assert.assertTrue(gridCreate != gridRandomize);
	}

	@Test
	public void directionTrue() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		Assert.assertTrue(gridCreate.move(Direction.BAS));
		Assert.assertTrue(gridCreate.move(Direction.DROITE));
		Assert.assertTrue(gridCreate.move(Direction.HAUT));
		Assert.assertTrue(gridCreate.move(Direction.GAUCHE));
	}

	@Test
	public void finalyTrue() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		TaquinGrid gridRandomize = new TaquinGrid(10, 10);

		Assert.assertTrue(gridCreate.finished());
		Assert.assertFalse(gridRandomize.finished());
	}

	@Test
	public void squareTrue() {
		TaquinGrid gridCreate = new TaquinGrid(10, 10, false);
		Assert.assertTrue(TaquinGrid.EMPTY_SQUARE == gridCreate.getSquare(9, 9));
	}

}
