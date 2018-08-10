package com.mashinarius.hs.compare;

public class CommonUtil
{
	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}

	public static boolean getRandomBooleanForPlayCard() {
		return Math.random() < 0.7;
	}

	public static boolean getRandomBooleanForFace() {
		return Math.random() < 0.4;
	}

/*	public static AbstractHero getRandomHero() {

		return new SimpleHero();
	}*/
}
