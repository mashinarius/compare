package com.mashinarius.hs.compare.cards;

import java.util.LinkedList;

public class AbstractDeck
{
	private LinkedList<AbstractCard> cards = new LinkedList<>();

	public LinkedList<AbstractCard> getCards()
	{
		return cards;
	}

	public void setCards(LinkedList<AbstractCard> cards)
	{
		this.cards = cards;
	}
}
