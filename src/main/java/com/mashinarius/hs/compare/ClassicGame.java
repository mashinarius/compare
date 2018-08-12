package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractCard;
import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.hero.*;
import com.mashinarius.hs.compare.realcards.CoinCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassicGame extends AbstractGame
{

	//private final Integer MAX_CARD = 30;

	private final Logger log = LoggerFactory.getLogger(ClassicGame.class);



	private AbstractHero getRealHeroFromType(Hero hero) {

		switch (hero) {
		case WARRIOR: {
			return new SimpleHero(Hero.WARRIOR.getText(), new AbilityWarrior());
		}
		case HUNTER: {
			return new SimpleHero(Hero.HUNTER.getText(), new AbilityHunter());
		}
		case WARLOCK: {
			return new SimpleHero(Hero.WARLOCK.getText(), new AbilityWarlock());
		}
		default: {
			return null;
		}
		}
	}

	public ClassicGame(Hero hero1, Hero hero2, Strategy strategy1, Strategy strategy2, AbstractDeck deck1, AbstractDeck deck2)
	{
		board = new Board();



		Gamer gamer1 = new Gamer(getRealHeroFromType(hero1), deck1);
		Gamer gamer2 = new Gamer(getRealHeroFromType(hero2), deck2);

		board.setGamer1(gamer1);
		board.setGamer2(gamer2);
	}

	private String cardsToString(List<AbstractCard> cards) {

		String text = "";

		for (AbstractCard card : cards)
		{
			text = text + " | " + card.getCost() + "("+card.getStrenght() + ","+card.getHealth()+")";
		}

		return text;
	}


	@Override
	protected Gamer game()
	{
		Gamer winner = null;
		log.info("-----------------");
		log.info("starting game..");
		for (int i = 0; i < 400; i++)
		{
			log.info("Turn " + board.getTurnOwner().getHero().getName());
			log.info("Health " + board.getTurnOwner().getHero().getHealth());
			board.getTurnOwner().addMana(1);
			if (board.getTurnOwner().getCurrentMana()>10) {
				board.getTurnOwner().setCurrentMana(10);
			}
			board.getTurnOwner().addCardFromTheDeck();
			log.info("Mana " + board.getTurnOwner().getCurrentMana());
			log.info("Hand " + cardsToString(board.getTurnOwner().getHand()));
			log.info("Board " + cardsToString(board.getAttackerBoard()));

			if (board.getTurnOwner().getDeck().getCards().size() < 1) {
				board.getTurnOwner().setFatig(board.getTurnOwner().getFatig() + 1 );
			}

			if (board.getTurnOwner().getFatig() > 0) {

				board.getTurnOwner().getHero().removeHealth(board.getTurnOwner().getFatig());
				log.info("Fatig on " + board.getTurnOwner().getFatig());
			}


			board.getTurnOwner().play();
			Integer g1Health = board.getGamer1().getHero().getHealth();
			Integer g2Health = board.getGamer2().getHero().getHealth();

			if (g1Health < 1 && g2Health > 0)
			{
				log.warn("Winner gamer 2 : " + board.getGamer2().getHero().getName());
				return board.getGamer2();
			} else if (g1Health > 0 && g2Health < 1)
			{
				log.warn("Winner gamer 1 : " + board.getGamer1().getHero().getName());
				return board.getGamer1();
			} else if (g1Health < 1 && g2Health < 1)
			{
				 log.warn("Round draw!");
				break;
			}
			board.nextTurn();
		}
		return winner;
	}

	@Override
	protected void сoin()
	{
		//log.info("coin..");
		boolean isFirst = CommonUtil.getRandomBoolean();
		board.setTurnOwner(isFirst);
		board.getCoinOwner().addCardToTheHand(new CoinCard());
		board.getCoinOwner().addMana(1);

	}

	@Override
	protected void fillDecks()
	{

	}

	@Override
	protected void fillHands()
	{
		board.getTurnOwner().addCardFromTheDeck();
		board.getTurnOwner().addCardFromTheDeck();
		board.getTurnOwner().addCardFromTheDeck();

		board.getCoinOwner().addCardFromTheDeck();
		board.getCoinOwner().addCardFromTheDeck();
		board.getCoinOwner().addCardFromTheDeck();
		board.getCoinOwner().addCardFromTheDeck();
	}

}
