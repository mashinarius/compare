package com.mashinarius.hs.compare.hero;

import com.mashinarius.hs.compare.Board;

public class AbilityWarrior extends AbstractAbility
{

	@Override
	public void playAbility(Board board)
	{
		board.getTurnOwner().getHero().addHealth(1);
	}
}
