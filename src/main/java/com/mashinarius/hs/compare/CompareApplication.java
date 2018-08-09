package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.cards.DeckWarlock;
import com.mashinarius.hs.compare.cards.DeckWarrior;
import com.mashinarius.hs.compare.hero.AbilityWarlock;
import com.mashinarius.hs.compare.hero.AbilityWarrior;
import com.mashinarius.hs.compare.hero.AbstractHero;
import com.mashinarius.hs.compare.hero.SimpleHero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class CompareApplication
{
	private final Logger log = LoggerFactory.getLogger(CompareApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(CompareApplication.class, args);
	}

	@PostConstruct
	public void init()
	{
		Long GAME_AMOUNT = new Long(100000);
		// create heroes and decks

		List<AbstractDeck> winners = new ArrayList();
		long t1 = System.nanoTime();
		for (int i = 0; i < GAME_AMOUNT; i++)
		{
			DeckWarrior deckWarLight = new DeckWarrior("light");
			DeckWarrior deckWarMedium = new DeckWarrior("medium");
			DeckWarrior deckWarHard = new DeckWarrior("hard");

			DeckWarlock deckLockLight = new DeckWarlock("light");
			DeckWarlock deckLockMedium = new DeckWarlock("medium");
			DeckWarlock deckLockHard = new DeckWarlock("hard");


			AbstractHero hero1 = new SimpleHero("Garosh", new AbilityWarrior());
			AbstractHero hero2 = new SimpleHero("Nancy", new AbilityWarlock());

			Gamer winner = new ClassicGame(new Gamer(hero1, deckWarLight), new Gamer(hero2, deckLockLight)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
			winner = new ClassicGame(new Gamer(hero1, deckWarLight), new Gamer(hero2, deckLockMedium)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
			winner = new ClassicGame(new Gamer(hero1, deckWarLight), new Gamer(hero2, deckLockHard)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());

			winner = new ClassicGame(new Gamer(hero1, deckWarMedium), new Gamer(hero2, deckLockLight)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
			winner = new ClassicGame(new Gamer(hero1, deckWarMedium), new Gamer(hero2, deckLockMedium)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
			winner = new ClassicGame(new Gamer(hero1, deckWarMedium), new Gamer(hero2, deckLockMedium)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());

			winner = new ClassicGame(new Gamer(hero1, deckWarHard), new Gamer(hero2, deckLockLight)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
			winner = new ClassicGame(new Gamer(hero1, deckWarHard), new Gamer(hero2, deckLockMedium)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
			winner = new ClassicGame(new Gamer(hero1, deckWarHard), new Gamer(hero2, deckLockHard)).startGame();
			if (winner != null)
				winners.add(winner.getDeck());
		}
		long t2 = System.nanoTime();

		HashMap<String, Integer> mapOfHeroes = new HashMap();

		HashMap<String, Integer> mapOfCards = new HashMap();

		winners.forEach(w -> {
			if (mapOfHeroes.get(w.getDackName()) == null)
			{
				mapOfHeroes.put(w.getDackName(), 0);
			}
			mapOfHeroes.put(w.getDackName(), mapOfHeroes.get(w.getDackName()) + 1);
		});

		for (String s : mapOfHeroes.keySet())
		{
			log.warn("Hero : " + s + " Amount : " + mapOfHeroes.get(s));
		}
/*		winners.forEach(w -> {
			w.getCards()
			if (mapOfHeroes.get(w.getClass().getSimpleName()) == null)
			{
				mapOfHeroes.put(w.getClass().getSimpleName(), 0);
			}
			mapOfHeroes.put(w.getClass().getSimpleName(), mapOfHeroes.get(w.getClass().getSimpleName()) + 1);
		});*/

/*		winners.forEach(w -> {
			log.warn(w.getClass().getSimpleName());
			*//*String cards = "";
			for (AbstractCard card : w.getCards())
			{
				cards = cards + card.getCost() + " ";
			}

			log.warn(cards);*//*
		});*/

		log.warn((t2 - t1) + " Nano");
	}
}
