package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractCard;
import com.mashinarius.hs.compare.realcards.CoinCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;

public class Board
{
	private final Logger log = LoggerFactory.getLogger(Board.class);
	private boolean isGamer1Turn = true;
	//private boolean isGamer2Turn = false;

	public boolean isCurrentGamerBoardSizeFull()
	{
		if (isGamer1Turn)
		{
			return gamer1Board.size() == 7;
		} else
		{
			return gamer2Board.size() == 7;
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
			//isGamer2Turn = true;
		} else
		{
			isGamer1Turn = true;
			//isGamer2Turn = false;
		}
	}

	public void setTurnOwner(boolean isFirstGamer)
	{
		if (isFirstGamer)
		{
			isGamer1Turn = true;
			//isGamer2Turn = false;
		} else
		{
			isGamer1Turn = false;
			//isGamer2Turn = true;
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

	public Gamer getDefender()
	{
		if (isGamer1Turn)
		{
			return gamer2;
		} else
		{
			return gamer1;
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
		this.gamer1.setBoard(this);
	}

	public Gamer getGamer2()
	{
		return gamer2;
	}

	public void setGamer2(Gamer gamer2)
	{
		this.gamer2 = gamer2;
		this.gamer2.setBoard(this);
	}

	public void fight()
	{
		if (isGamer1Turn)
		{
			figthExt(gamer1Board, gamer2Board, gamer2);
		} else
		{
			figthExt(gamer2Board, gamer1Board, gamer1);
		}
	}

	private void figthExt(LinkedList<AbstractCard> attackerBoard, LinkedList<AbstractCard> defenderBoard, Gamer defenderGamer)
	{
		for (AbstractCard attackerCard : attackerBoard)
		{
			if (!attackerCard.isKilled())
			{
				if (CommonUtil.getRandomBoolean() && defenderBoard.size() > 0)
				{
					Optional<AbstractCard> cardThatCouldBeKilledAndAttackerAllived = defenderBoard.stream()
							.filter(c -> c.getHealth() <= attackerCard.getStrenght() && c.getStrenght() < attackerCard.getHealth())
							.sorted(Comparator.comparing(AbstractCard::getCost).reversed()).findFirst();

					if (cardThatCouldBeKilledAndAttackerAllived.isPresent())
					{
						attackerCard.setHealth(attackerCard.getHealth() - cardThatCouldBeKilledAndAttackerAllived.get().getStrenght());
						defenderBoard.remove(cardThatCouldBeKilledAndAttackerAllived.get());
						continue;
					}

					Optional<AbstractCard> cardThatCouldBeKilledAndAttackerKilled = defenderBoard.stream().filter(c -> c.getHealth() <= attackerCard.getStrenght()).sorted(Comparator.comparing(AbstractCard::getCost).reversed())
							.findFirst();

					if (cardThatCouldBeKilledAndAttackerKilled.isPresent())
					{
						attackerCard.setHealth(attackerCard.getHealth() - cardThatCouldBeKilledAndAttackerKilled.get().getStrenght());
						defenderBoard.remove(cardThatCouldBeKilledAndAttackerKilled.get());
						continue;
					}

					AbstractCard defenderCard = defenderBoard.getFirst();
					if (defenderCard.getHealth() > attackerCard.getStrenght())
					{
						//log.info(attackerCard.getClass().getSimpleName() + " attacks defenderCard " + defenderCard.getClass().getSimpleName() );
						defenderCard.setHealth(defenderCard.getHealth() - attackerCard.getStrenght());
						attackerCard.setHealth(attackerCard.getHealth() - defenderCard.getStrenght());
					} else
					{
						//log.info(attackerCard.getClass().getSimpleName() + " kill defenderCard " + defenderCard.getClass().getSimpleName());
						attackerCard.setHealth(attackerCard.getHealth() - defenderCard.getStrenght());
						defenderBoard.remove(defenderCard);
					}
					if (attackerCard.getHealth() < 1) {
						attackerCard.setKilled(true);
					}
				} else
				{
					// face
					//log.info(attackerCard.getClass().getSimpleName() + " attacks face ");
					defenderGamer.getHero().removeHealth(attackerCard.getStrenght());
				}
			}
		}

		attackerBoard.removeIf(c->c.isKilled());

	}
}
