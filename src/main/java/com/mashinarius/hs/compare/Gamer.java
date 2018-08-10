package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractCard;
import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.cards.SimpleCard;
import com.mashinarius.hs.compare.hero.AbstractHero;
import com.mashinarius.hs.compare.realcards.CoinCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Gamer
{

	private final Logger log = LoggerFactory.getLogger(Gamer.class);
	private Board board;

	private Integer fatig = 0;

	public Integer getFatig()
	{
		return fatig;
	}

	public void setFatig(Integer fatig)
	{
		this.fatig = fatig;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	private Integer HAND_SIZE = 10;
	private LinkedList<AbstractCard> hand = new LinkedList<>();
	//private List<AbstractCard> cardsInDeck;// = new LinkedList<>();
	private AbstractDeck deck;

	public LinkedList<AbstractCard> getHand()
	{
		return hand;
	}

	public void setHand(LinkedList<AbstractCard> hand)
	{
		this.hand = hand;
	}

	private AbstractHero hero;
	private Integer currentMana = 0;

	public Integer getCurrentMana()
	{
		return currentMana;
	}

	public Gamer(AbstractHero hero, AbstractDeck deck)
	{
		this.hero = hero;
		this.deck = deck;
	}

	public void addCardToTheHand(AbstractCard card)
	{
		SimpleCard card1 = new SimpleCard(card.getHealth(), card.getStrenght(), card.getCost());
		if (hand.size() < HAND_SIZE)
		{
			hand.add(card1);
		} else {
			log.info(card1.getCost() + " was fired. Hand is full." );
		}
	}

	public void addMana(int i)
	{
		currentMana += i;
	}

	public void addCardFromTheDeck()
	{
		if (deck.getCards().size() > 0)
		{
			AbstractCard card = deck.getCards().remove(0);
			addCardToTheHand(card);
		}
	}

	private String handToString(List<AbstractCard> cards)
	{
		String result = new String();
		for (AbstractCard card : cards)
		{
			result = result + " " + card.getCost();
		}

		return result;
	}

	public void play()
	{
		/*log.info("--------------------------");
		log.info("Hero name " + hero.getName());
		log.info("Hand  " + handToString(hand));
		log.info("Mana " + currentMana);
		log.info("Health " + getHero().getHealth());
		log.info("Deck " + deck.getCards().size());
		log.info("Board " + board.isCurrentGamerBoardSizeFull());
*/

		boolean isWeHaveACoin = hand.stream().filter(s -> s instanceof CoinCard).count() > 0;
		if (isWeHaveACoin && CommonUtil.getRandomBoolean())
		{
			boolean isNoCardCurrentMana = hand.stream().filter(c -> c.getCost() <= currentMana).count() < 1;
			boolean isAnyCardPlusOne = hand.stream().filter(c -> c.getCost().equals(currentMana + 1)).count() > 0;
			if (isAnyCardPlusOne && isNoCardCurrentMana)
			{
				currentMana = currentMana + 1;
				hand.removeFirstOccurrence(CoinCard.class);
			}
		}

		Integer manaLeft = currentMana;
		if (manaLeft > 2)
		{
			if (CommonUtil.getRandomBoolean())
			{
				//log.info("Ability played");
				getHero().getAbility().playAbility(board);
				manaLeft = manaLeft - 2;
			}
		}

		board.fight();

		board.getAttackerBoard().removeIf(c->c.getHealth()<1);

		Random rand = new Random();

		List<AbstractCard> availableCardsInHand = new ArrayList<>();
		for (AbstractCard card : hand)
		{
			if (card.getCost() <= manaLeft)
			{
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
			//log.info("Playing card " + card.getClass().getSimpleName());
			hand.remove(card);
			availableCardsInHand.clear();
			for (AbstractCard card2 : hand)
			{
				if (card2.getCost() <= manaLeft)
				{
					availableCardsInHand.add(card2);
				}
			}
		}

	}

	public AbstractDeck getDeck()
	{
		return deck;
	}

	public AbstractHero getHero()
	{
		return hero;
	}

	public void setHero(AbstractHero hero)
	{
		this.hero = hero;
	}

	public void setCurrentMana(int i)
	{
		currentMana = i;
	}
}
