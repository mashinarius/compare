package com.mashinarius.hs.compare.hero;

public abstract class AbstractHero
{
	public AbstractHero(String name, AbstractAbility ability)
	{
		this.name = name;
		this.ability = ability;
	}

	private final Integer MAX_HEALTH = 30;

	private String name;
	private AbstractAbility ability;
	private Integer health = MAX_HEALTH;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public AbstractAbility getAbility()
	{
		return ability;
	}

	public void setAbility(AbstractAbility ability)
	{
		this.ability = ability;
	}

	public Integer getHealth()
	{
		return health;
	}


	public boolean removeHealth(Integer amount) {
		health= health - amount;
		return health > 0;
	}
	public void addHealth(Integer amount) {
		health = health + amount;
		if (health > MAX_HEALTH) {
			health = MAX_HEALTH;
		}
	}


}
