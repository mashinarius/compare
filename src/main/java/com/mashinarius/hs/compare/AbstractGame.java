package com.mashinarius.hs.compare;

public abstract class AbstractGame
{

	protected Board board;
	public AbstractGame() {


	}


	protected abstract void game();

	protected abstract void сoin();

	protected abstract void createGamers();

	protected abstract void createBoard();

	protected abstract  void fillDecks();
	protected abstract  void fillHands();

	public void startGame() {

		// create Board;
		createBoard();
		// create Gamers;
		createGamers();
		// rize Coin
		сoin();

		// fill decks
		fillDecks();
		// fill hands
		fillHands();


		// start

		game();
	}

}
