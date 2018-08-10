package com.mashinarius.hs.compare.cards;

import com.mashinarius.hs.compare.realcards.Book;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDeck
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

	private String dackName;

	public String getDackName()
	{
		return dackName;
	}

	public void setDackName(String dackName)
	{
		this.dackName = dackName;
	}

	public AbstractDeck(String s)
	{
		this.dackName = s + " " + this.getClass().getSimpleName();

		List<AbstractCard> cards;
		switch (s)
		{
		case "light":
		{
			cards = new Book().getSmallCards();
			break;
		}
		case "medium":
		{
			cards = new Book().getMediumCards();
			break;
		}
		case "hard":
		{
			cards = new Book().getHardCards();
			break;
		}
		default:
		{
			cards = new Book().getAllCards();
			break;
		}
		}

/*		while (getCards().size() < 30)
		{

			for (AbstractCard card : cards)
			{
				getCards().add(card);
			}
		}*/

		Collections.shuffle(cards);

	}

}
