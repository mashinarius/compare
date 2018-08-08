package com.mashinarius.hs.compare.cards;

public class AbstractCard
{
	private Integer health;
	private Integer strenght;
	private Integer cost;
	private boolean tount;

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
}
