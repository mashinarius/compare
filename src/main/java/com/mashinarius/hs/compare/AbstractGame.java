package com.mashinarius.hs.compare;

public abstract class AbstractGame
{
	protected Board board;

	protected abstract Gamer game();

	protected abstract void сoin();

	protected abstract void fillDecks();

	protected abstract void fillHands();


	public Gamer startGame () {
		// throw Coin
		сoin();
		// fill decks
		fillDecks();
		// fill hands
		fillHands();
		// start
		return game();
	}
}
