package com.mashinarius.hs.compare.realcards;

import com.mashinarius.hs.compare.Board;
import com.mashinarius.hs.compare.Gamer;
import com.mashinarius.hs.compare.cards.SimpleCard;

public class CoinCard extends SimpleCard
{
	public CoinCard()
	{
		super(0, 0, 0);
	}

	@Override
	public void deathRattle(Gamer owner, Gamer opposite, Board board)
	{

	}

	@Override
	public void battleCry(Gamer owner, Gamer opposite, Board board)
	{
		board.getTurnOwner().addMana(1);
	}
}
