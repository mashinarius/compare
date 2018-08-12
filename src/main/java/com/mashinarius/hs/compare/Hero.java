package com.mashinarius.hs.compare;

public enum Hero
{
	WARRIOR("Garosh"), WARLOCK ("Nancy"), HUNTER("Rexar");

	private String text;

	Hero(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
