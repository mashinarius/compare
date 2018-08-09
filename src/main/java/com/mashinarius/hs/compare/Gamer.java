package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractCard;
import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.hero.AbstractHero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Gamer
{
	private Board board;
	private final Logger log = LoggerFactory.getLogger(Gamer.class);
	private Integer HAND_SIZE = 10;
	private LinkedList<AbstractCard> hand = new LinkedList<>();
	private List<AbstractCard> deck;// = new LinkedList<>();

	private AbstractHero hero;
	private Integer currentMana = 0;

	public Gamer(Board board, AbstractHero hero, List<AbstractCard> deck)
	{
		this.board = board;
		this.hero = hero;
		this.deck = deck;
	}


	public void addCardToTheHand(AbstractCard card)
	{
		if (hand.size() < HAND_SIZE)
		{
			hand.add(card);
		}
	}

	public void addMana(int i)
	{
		currentMana += i;
	}

	public void addCardFromTheDeck()
	{
		if (deck.size() > 0)
		{
			AbstractCard card = deck.removeFirst();
			addCardToTheHand(card);
		}
	}

	private String handToString(List<AbstractCard> cards) {
		String result = new String();
		for (AbstractCard card : cards)
		{
			result = result + " " + card.getCost();
		}

		return result;
	}

	public void play()
	{
		log.info("--------------------------");
		log.info("Hero name " + hero.getName());
		log.info("Hand  " + handToString(hand));
		log.info("Mana " + currentMana);
		log.info("Health " + getHero().getHealth());
		log.info("Deck " + deck.size());
		log.info("Board " + board.isCurrentGamerBoardSizeFull());


		Integer manaLeft = currentMana;
		if (manaLeft > 2)
		{
			if (CommonUtil.getRandomBoolean())
			{
				log.info("Ability played");
				getHero().getAbility().playAbility(board);
				manaLeft = manaLeft - 2;
			}
		}

		board.fight();

		Random rand = new Random();

		List<AbstractCard> availableCardsInHand = new ArrayList<>();
		for (AbstractCard card : hand)
		{
			if (card.getCost() <= manaLeft) {
				availableCardsInHand.add(card);
			}
		}
		//availableCardsInHand.addAll(hand.stream().filter(c -> c.getCost() <= finalManaLeft).collect(Collectors.toList()));

		while (availableCardsInHand.size() > 0 && !board.isCurrentGamerBoardSizeFull())
		{

			int x = rand.nextInt(availableCardsInHand.size());
			AbstractCard card = availableCardsInHand.remove(x);
			manaLeft = manaLeft - card.getCost();
			board.addCardToTheBoard(card);
			log.info("Playing card " + card.getClass().getSimpleName());
			hand.remove(card);
			availableCardsInHand.clear();
			for (AbstractCard card2 : hand)
			{
				if (card2.getCost() <= manaLeft) {
					availableCardsInHand.add(card2);
				}
			}
		}

	}

	public AbstractHero getHero()
	{
		return hero;
	}

	public void setHero(AbstractHero hero)
	{
		this.hero = hero;
	}
}
