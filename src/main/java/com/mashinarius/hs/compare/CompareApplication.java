package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.cards.DeckHunter;
import com.mashinarius.hs.compare.cards.DeckWarlock;
import com.mashinarius.hs.compare.cards.DeckWarrior;
import com.mashinarius.hs.compare.hero.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.SerializationUtils;

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
		Long GAME_AMOUNT = new Long(100);
		// create heroes and decks

		List<AbstractDeck> winners = new ArrayList();
		long t1 = System.nanoTime();
		for (int i = 0; i < GAME_AMOUNT; i++)
		{
			List<AbstractDeck> warriorDecks = new ArrayList<>();
			List<AbstractDeck> warlockDecks = new ArrayList<>();
			List<AbstractDeck> hunterDecks = new ArrayList<>();

			warriorDecks.add(new DeckWarrior("light"));
			warriorDecks.add(new DeckWarrior("medium"));
			warriorDecks.add(new DeckWarrior("hard"));

			warlockDecks.add(new DeckWarlock("light"));
			warlockDecks.add(new DeckWarlock("medium"));
			warlockDecks.add(new DeckWarlock("hard"));

			hunterDecks.add(new DeckHunter("light"));
			hunterDecks.add(new DeckHunter("medium"));
			hunterDecks.add(new DeckHunter("hard"));


			AbstractHero garosh = new SimpleHero("Garosh", new AbilityWarrior());
			AbstractHero nancy = new SimpleHero("Nancy", new AbilityWarlock());
			AbstractHero rexar = new SimpleHero("Rexar", new AbilityHunter());

			testMethod(winners, warlockDecks, warriorDecks, nancy, garosh);

			testMethod(winners, hunterDecks, warriorDecks, rexar, garosh);

			testMethod(winners, warlockDecks, hunterDecks, nancy, rexar);


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

	private void testMethod(List<AbstractDeck> winners, List<AbstractDeck> deck1, List<AbstractDeck> deck2, AbstractHero hero1, AbstractHero hero2)
	{
		SimpleHero heroCopy1;
		AbstractHero heroCopy2;
		AbstractDeck deckCopy1;
		AbstractDeck deckCopy2;

		for (int index1 = 0; index1 < deck1.size(); index1++)
		{
			for (int index2 = 0; index2 < deck2.size(); index2++)
			{
				heroCopy1 =  new SimpleHero(hero1.getName(), hero1.getAbility());
				BeanUtils.copyProperties(hero1, heroCopy1);

				heroCopy2 =  new SimpleHero(hero2.getName(), hero2.getAbility());
				BeanUtils.copyProperties(hero2, heroCopy2);

				List backUpCards1 = new ArrayList();
				List backUpCards2 = new ArrayList();

				backUpCards1.addAll(deck1.get(index1).getCards());
				backUpCards2.addAll(deck2.get(index2).getCards());

				deckCopy1 = deck1.get(index1);
				deckCopy2 = deck2.get(index2);

				Gamer copyGamer1 = new Gamer(heroCopy1, deckCopy1);
				Gamer copyGamer2 = new Gamer(heroCopy2, deckCopy2);

				Gamer winner = new ClassicGame(copyGamer1, copyGamer2).startGame();

				deck1.get(index1).getCards().clear();
				deck1.get(index1).getCards().addAll(backUpCards1);

				deck2.get(index2).getCards().clear();
				deck2.get(index2).getCards().addAll(backUpCards2);

				if (winner != null)
					winners.add(winner.getDeck());
			}
		}
	}
}
