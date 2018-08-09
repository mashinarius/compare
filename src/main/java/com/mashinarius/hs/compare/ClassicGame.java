package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.realcards.CoinCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassicGame extends AbstractGame
{

	private final Integer MAX_CARD = 30;

	public ClassicGame()
	{
		super();
	}

	private final Logger log = LoggerFactory.getLogger(ClassicGame.class);

	public ClassicGame(Gamer gamer1, Gamer gamer2)
	{
		board = new Board();
		board.setGamer1(gamer1);
		board.setGamer2(gamer2);
	}

	@Override
	protected Gamer game()
	{
		Gamer winner = null;
		//log.info("starting game..");
		for (int i = 0; i < 40; i++)
		{
			board.getTurnOwner().addMana(1);
			board.getTurnOwner().addCardFromTheDeck();
			board.getTurnOwner().play();
			Integer g1Health = board.getGamer1().getHero().getHealth();
			Integer g2Health = board.getGamer2().getHero().getHealth();

			if (g1Health < 1 && g2Health > 0)
			{
				//log.warn("Winner : Hero2");
				return board.getGamer2();
			} else if (g1Health > 0 && g2Health < 1)
			{
				//log.warn("Winner : SimpleHero");
				return board.getGamer1();
			} else if (g1Health < 1 && g2Health < 1)
			{
				// log.warn("Game draw!");
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
