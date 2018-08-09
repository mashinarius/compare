package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.cards.DeckWarlock;
import com.mashinarius.hs.compare.cards.DeckWarrior;
import com.mashinarius.hs.compare.hero.AbstractHero;
import com.mashinarius.hs.compare.hero.Hero1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CompareApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CompareApplication.class, args);
	}

	@PostConstruct
	public void init () {


		// create heroes and decks
		DeckWarrior deckWarLight = new DeckWarrior("light");
		DeckWarrior deckWarMedium = new DeckWarrior("medium");
		DeckWarrior deckWarHard = new DeckWarrior("hard");

		DeckWarlock deckLockLight = new DeckWarlock("light");
		DeckWarlock deckLockMedium = new DeckWarlock("medium");
		DeckWarlock deckLockHard = new DeckWarlock("hard");

		List<AbstractDeck> winners = new ArrayList();
		for (int i = 0; i < 10; i++)
		{

			AbstractHero hero1 = new Hero1(deckWarLight);
			AbstractHero hero2 = new Hero1(deckLockLight);
			Gamer gamer1 = new Gamer(hero1);


			winners.add(new ClassicGame().startGame(hero1, hero2));
			winners.add(new ClassicGame().startGame(deckWarLight, deckLockMedium));
			winners.add(new ClassicGame().startGame(deckWarLight, deckLockHard));

			winners.add(new ClassicGame().startGame(deckWarMedium, deckLockLight));
			winners.add(new ClassicGame().startGame(deckWarMedium, deckLockMedium));
			winners.add(new ClassicGame().startGame(deckWarMedium, deckLockHard));

			winners.add(new ClassicGame().startGame(deckWarHard, deckLockLight));
			winners.add(new ClassicGame().startGame(deckWarHard, deckLockMedium));
			winners.add(new ClassicGame().startGame(deckWarHard, deckLockHard));
		}






	}

}
