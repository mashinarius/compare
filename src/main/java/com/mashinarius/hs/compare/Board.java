package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class Board
{
	private final Logger log = LoggerFactory.getLogger(Board.class);
	private boolean isGamer1Turn = true;
	private boolean isGamer2Turn = false;

public boolean isCurrentGamerBoardSizeFull() {
	if (isGamer1Turn) {
		return gamer1Board.size()==7;
	} else {
		return gamer2Board.size()==7;
	}

}

	private LinkedList<AbstractCard> gamer1Board = new LinkedList<>();
	private LinkedList<AbstractCard> gamer2Board = new LinkedList<>();

	public void addCardToTheBoard(AbstractCard card)
	{
		if (isGamer1Turn)
		{
			gamer1Board.add(card);
		} else
		{
			gamer2Board.add(card);
		}

	}

	public void nextTurn()
	{
		if (isGamer1Turn)
		{
			isGamer1Turn = false;
			isGamer2Turn = true;
		} else
		{
			isGamer1Turn = true;
			isGamer2Turn = false;
		}
	}

	public void setTurnOwner(boolean isFirstGamer)
	{
		if (isFirstGamer)
		{
			isGamer1Turn = true;
			isGamer2Turn = false;
		} else
		{
			isGamer1Turn = false;
			isGamer2Turn = true;
		}
	}

	public Gamer getTurnOwner()
	{
		if (isGamer1Turn)
		{
			return gamer1;
		} else
		{
			return gamer2;
		}
	}

	public Gamer getCoinOwner()
	{
		if (isGamer1Turn)
		{
			return gamer2;
		} else
		{
			return gamer1;
		}
	}

	private Gamer gamer1;
	private Gamer gamer2;

	public Gamer getGamer1()
	{
		return gamer1;
	}

	public void setGamer1(Gamer gamer1)
	{
		this.gamer1 = gamer1;
	}

	public Gamer getGamer2()
	{
		return gamer2;
	}

	public void setGamer2(Gamer gamer2)
	{
		this.gamer2 = gamer2;
	}

	public void figth()
	{
		if (isGamer1Turn)
		{
			figthExt(gamer1Board, gamer2Board, gamer2);
		} else
		{
			figthExt(gamer2Board, gamer1Board, gamer1);
		}
	}

	private void figthExt(LinkedList<AbstractCard> gamer2Board, LinkedList<AbstractCard> gamer1Board, Gamer gamer1)
	{
		for (AbstractCard attacker : gamer2Board)
		{
			if (CommonUtil.getRandomBoolean() && gamer1Board.size() > 0)
			{
				AbstractCard defender = gamer1Board.getFirst();
				if (defender.getHealth() > attacker.getStrenght())
				{
					log.info(attacker.getClass().getSimpleName() + " attacks defender " + defender.getClass().getSimpleName() );
					defender.setHealth(defender.getHealth() - attacker.getStrenght());
				} else
				{
					log.info(attacker.getClass().getSimpleName() + " kill defender " + defender.getClass().getSimpleName());
					gamer1Board.remove(defender);
				}
			} else
			{
				// face
				log.info(attacker.getClass().getSimpleName() + " attacks face ");
				gamer1.getHero().removeHealth(attacker.getStrenght());
			}
		}
	}
}
