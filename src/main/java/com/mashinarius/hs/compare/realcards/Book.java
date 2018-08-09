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

		allCards.add(new Card888());
		allCards.add(new Card999());
		allCards.add(new Card101010());

	}

	public List<AbstractCard> getAllCards()
	{
		return allCards;
	}


	public List<AbstractCard> getSmallCards() {
		List<AbstractCard> cards = new ArrayList<>();
		cards.add(new Card111());
		cards.add(new Card111());

		cards.add(new Card222());
		cards.add(new Card222());

		cards.add(new Card333());
		cards.add(new Card333());

		cards.add(new Card444());
		cards.add(new Card444());

		cards.add(new Card555());
		cards.add(new Card555());

		return cards;
	}


	public List<AbstractCard> getMediumCards() {
		List<AbstractCard> cards = new ArrayList<>();

		cards.add(new Card333());

		cards.add(new Card444());
		cards.add(new Card444());

		cards.add(new Card555());
		cards.add(new Card555());

		cards.add(new Card666());
		cards.add(new Card666());

		cards.add(new Card777());
		cards.add(new Card777());

		cards.add(new Card888());

		return cards;
	}

	public List<AbstractCard> getHardCards() {
		List<AbstractCard> cards = new ArrayList<>();

		cards.add(new Card666());
		cards.add(new Card666());

		cards.add(new Card777());
		cards.add(new Card777());

		cards.add(new Card888());
		cards.add(new Card888());

		cards.add(new Card999());
		cards.add(new Card999());

		cards.add(new Card101010());
		cards.add(new Card101010());
		return cards;
	}
}
