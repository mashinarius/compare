package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.hero.AbstractHero;

import java.util.List;

public abstract class AbstractGame
{



	protected Board board;
	private AbstractHero hero1;
	private AbstractHero hero2;


	protected abstract void game();

	protected abstract void сoin();

	//protected abstract void createGamers(AbstractHero hero1, AbstractHero hero2, List<AbstractDeck> deck1, List<AbstractDeck> deck2);

	protected abstract void createBoard();

	protected abstract void fillDecks();

	protected abstract void fillHands();

/*
	public void startGame()
	{
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
*/

	public void startGame (Gamer  gamer1, Gamer  gamer2) {
		// create Board;
		createBoard();
		// create Gamers;
		createGamers(gamer1, gamer2);
		// throw Coin
		сoin();
		// fill decks
		fillDecks();
		// fill hands
		fillHands();
		// start
		game();

	}

	protected abstract void createGamers(Gamer gamer1, Gamer gamer2);

}
