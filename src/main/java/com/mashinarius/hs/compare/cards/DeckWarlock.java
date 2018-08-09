package com.mashinarius.hs.compare.cards;

import com.mashinarius.hs.compare.realcards.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class DeckWarlock extends AbstractDeck
{
	private final Logger log = LoggerFactory.getLogger(DeckWarlock.class);

	public DeckWarlock(String s)
	{
		super(s);
	}

	/*
	public DeckWarlock()
	{

		List<AbstractCard> allCards = new Book().getAllCards();
		Random ran = new Random();

		while (getCards().size() < 30)
		{
			int x = ran.nextInt(allCards.size());
			getCards().add(allCards.get(x));
		}

		getCards().forEach(c -> log.info(c.getClass().getSimpleName()));
	}*/


}
