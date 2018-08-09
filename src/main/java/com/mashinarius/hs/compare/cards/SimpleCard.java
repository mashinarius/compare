package com.mashinarius.hs.compare.cards;

import com.mashinarius.hs.compare.Board;
import com.mashinarius.hs.compare.Gamer;

public class SimpleCard extends AbstractCard
{
	public SimpleCard(Integer health, Integer strenght, Integer cost)
	{
		super(health, strenght, cost);
	}

	public SimpleCard(Integer health, Integer strenght, Integer cost, boolean tount)
	{
		super(health, strenght, cost, tount);
	}

	@Override
	public void deathRattle(Gamer owner, Gamer opposite, Board board)
	{

	}

	@Override
	public void battleCry(Gamer owner, Gamer opposite, Board board)
	{

	}
}
