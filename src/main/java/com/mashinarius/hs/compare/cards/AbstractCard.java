package com.mashinarius.hs.compare.cards;

import com.mashinarius.hs.compare.Board;
import com.mashinarius.hs.compare.Gamer;

public abstract class AbstractCard
{
	private Integer health;
	private Integer strenght;
	private Integer cost;
	private boolean tount;
	private boolean killed = false;

	public AbstractCard(Integer health, Integer strenght, Integer cost)
	{
		this.health = health;
		this.strenght = strenght;
		this.cost = cost;
		this.tount = false;
	}

	public AbstractCard(Integer health, Integer strenght, Integer cost, boolean tount)
	{
		this.health = health;
		this.strenght = strenght;
		this.cost = cost;
		this.tount = tount;
	}

	public boolean isKilled()
	{
		return killed;
	}

	public void setKilled(boolean killed)
	{
		this.killed = killed;
	}

	public Integer getCost()
	{
		return cost;
	}

	public void setCost(Integer cost)
	{
		this.cost = cost;
	}

	public boolean isTount()
	{
		return tount;
	}

	public void setTount(boolean tount)
	{
		this.tount = tount;
	}

	public Integer getHealth()
	{
		return health;
	}

	public void setHealth(Integer health)
	{
		this.health = health;
	}

	public Integer getStrenght()
	{
		return strenght;
	}

	public void setStrenght(Integer strenght)
	{
		this.strenght = strenght;
	}


	public abstract void deathRattle(Gamer owner, Gamer opposite, Board board);

	public abstract void battleCry(Gamer owner, Gamer opposite, Board board);

}
