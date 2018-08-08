package com.mashinarius.hs.compare.realcards;

import com.mashinarius.hs.compare.cards.AbstractCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Book
{
	private List<AbstractCard> allCards = new ArrayList<>();

	public Book() {
		allCards.add(new Card111());
		allCards.add(new Card111());

		allCards.add(new Card222());
		allCards.add(new Card222());

		allCards.add(new Card333());
		allCards.add(new Card333());

		allCards.add(new Card444());
		allCards.add(new Card444());

		allCards.add(new Card555());
		allCards.add(new Card555());

		allCards.add(new Card666());
		allCards.add(new Card666());

		allCards.add(new Card777());
		allCards.add(new Card777());

		allCards.add(new Card779());
		allCards.add(new Card797());

	}

	public List<AbstractCard> getAllCards()
	{
		return allCards;
	}
}
