package com.mashinarius.hs.compare.hero;

import com.mashinarius.hs.compare.Board;

public class AbilityHunter extends AbstractAbility
{
	@Override
	public void playAbility(Board board)
	{
		board.getDefender().getHero().removeHealth(2);
	}
}
