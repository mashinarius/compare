package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.cards.DeckWarlock;
import com.mashinarius.hs.compare.cards.DeckWarrior;
import com.mashinarius.hs.compare.hero.AbilityWarrior;
import com.mashinarius.hs.compare.hero.AbilityWarlock;
import com.mashinarius.hs.compare.hero.AbstractHero;
import com.mashinarius.hs.compare.hero.Hero1;
import com.mashinarius.hs.compare.realcards.CoinCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClassicGame extends AbstractGame
{

	private final Integer MAX_CARD = 30;
	public ClassicGame () {
		super();
	}
	private final Logger log = LoggerFactory.getLogger(ClassicGame.class);

	@Override
	protected void game()
	{
		log.info("starting game..");
		for (int i = 0; i < 40; i++)
		{
			board.getTurnOwner().addMana(1);
			board.getTurnOwner().addCardFromTheDeck();
			board.getTurnOwner().play();
			Integer g1Health = board.getGamer1().getHero().getHealth();
			Integer g2Health = board.getGamer2().getHero().getHealth();

			if ( g1Health<1 && g2Health > 0) {
				log.warn("Winner : Hero2");
				break;
			} else if ( g1Health>0 && g2Health <1 )
			{
				log.warn("Winner : Hero1");
				break;
			} else if (g1Health<1 && g2Health < 1) {
				log.warn("Game draw!");
				break;
			}
			board.nextTurn();
		}

	}

	@Override
	protected void сoin()
	{
		log.info("coin..");
		boolean isFirst = CommonUtil.getRandomBoolean();
		board.setTurnOwner(isFirst);
		board.getCoinOwner().addCardToTheHand(new CoinCard());
		board.getCoinOwner().addMana(1);

	}
/*
	@Override
	protected void createGamers(AbstractHero hero1, AbstractHero hero2, List<AbstractDeck> deck1, List<AbstractDeck> deck2)
	{
		Gamer gamer1 = new Gamer(board, hero1, deck1);
		board.setGamer1(gamer1);
		Gamer gamer2 = new Gamer(board, hero2, deck2);
		board.setGamer2(gamer2);
	}*/

/*	@Override
	protected void createGamers(AbstractDeck deck1, AbstractDeck deck2)
	{
		log.info("creating gamers..");
		AbstractHero hero1 = new Hero1(new AbilityWarrior(), "Warrior");
		AbstractHero hero2 = new Hero1(new AbilityWarlock(), "Warlock");

		Gamer gamer1 = new Gamer(board, hero1, new DeckWarrior(deck1));
		board.setGamer1(gamer1);
		Gamer gamer2 = new Gamer(board, hero2, new DeckWarlock());
		board.setGamer2(gamer2);
	}*/

	@Override
	protected void createBoard()
	{
		log.info("creating board..");
		board = new Board();

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

	@Override
	protected void createGamers(Gamer gamer1, Gamer gamer2)
	{
		board.setGamer1(gamer1);
		board.setGamer2(gamer2);
	}

}
