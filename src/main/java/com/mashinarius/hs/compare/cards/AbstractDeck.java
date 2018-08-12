package com.mashinarius.hs.compare.cards;

import com.mashinarius.hs.compare.realcards.Book;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDeck
{
	private List<AbstractCard> cards = new LinkedList<>();

	public List<AbstractCard> getCards()
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


		switch (s)
		{
		case "light":
		{
			this.cards = new Book().getSmallCards();
			break;
		}
		case "medium":
		{
			this.cards = new Book().getMediumCards();
			break;
		}
		case "hard":
		{
			this.cards = new Book().getHardCards();
			break;
		}
		default:
		{
			this.cards = new Book().getAllCards();
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
