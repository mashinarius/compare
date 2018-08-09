package com.mashinarius.hs.compare.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
