package com.mashinarius.hs.compare;

import com.mashinarius.hs.compare.cards.AbstractDeck;
import com.mashinarius.hs.compare.cards.DeckHunter;
import com.mashinarius.hs.compare.cards.DeckWarlock;
import com.mashinarius.hs.compare.cards.DeckWarrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication
public class CompareApplication
{
	private final Logger log = LoggerFactory.getLogger(CompareApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(CompareApplication.class, args);
	}

	final Integer THREAD_COUNT = 8;

	@PostConstruct
	public void init()
	{
		Long GAME_AMOUNT = new Long(1000);

		List<AbstractDeck> winners = new ArrayList();
		long t1 = System.nanoTime();
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

		Future<List<AbstractDeck>> future = null;// = executor.submit(new MyTask());

		for (int i = 0; i < GAME_AMOUNT; i++)
		{

			future = executor.submit(new MyTask());
		}

		try
		{
			log.debug("Collector Future Task Started..");
			winners.addAll(future.get(1, TimeUnit.HOURS));
			log.debug("Collector Future Task Finished!");
		} catch (TimeoutException e)
		{
			future.cancel(true);
			log.error("TimeoutException");
			log.error(e.getMessage());
		} catch (InterruptedException e)
		{
			log.error("InterruptedException");
			log.error(e.getLocalizedMessage());
		} catch (ExecutionException e)
		{
			log.error("ExecutionException");
			log.error(e.getLocalizedMessage());
		}

			/*compareHeroes(winners, Hero.HUNTER, Hero.WARLOCK);
			compareHeroes(winners, Hero.WARRIOR, Hero.WARLOCK);
			compareHeroes(winners, Hero.WARRIOR, Hero.HUNTER); */

		executor.shutdownNow();
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
		log.warn((t2 - t1) + " Nano");
	}

	class MyTask implements Callable<List<AbstractDeck>>
	{

		@Override
		public ArrayList<AbstractDeck> call()
		{
			ArrayList<AbstractDeck> winnerDecks = new ArrayList<>();

			compareHeroes(winnerDecks, Hero.HUNTER, Hero.WARLOCK);
			compareHeroes(winnerDecks, Hero.WARRIOR, Hero.WARLOCK);
			compareHeroes(winnerDecks, Hero.WARRIOR, Hero.HUNTER);

			return winnerDecks;
		}
	}

	private void compareHeroes(List<AbstractDeck> winners, Hero heroType1, Hero heroType2)
	{

		Gamer winner;
		String[] types = new String[] { "light", "medium", "hard" };

		for (String deckType1 : types)
		{
			for (String deckType2 : types)
			{
				AbstractDeck deck1 = null;
				AbstractDeck deck2 = null;

				deck1 = getAbstractDeck(heroType1, deckType1);
				deck2 = getAbstractDeck(heroType2, deckType2);

				winner = new ClassicGame(heroType1, heroType2, Strategy.CONTROL, Strategy.CONTROL, deck1, deck2).startGame();
				if (winner != null)
					winners.add(winner.getDeck());

			}

		}
	}

	private AbstractDeck getAbstractDeck(Hero heroType, String deckType)
	{
		AbstractDeck deck = null;
		switch (heroType)
		{
		case WARRIOR:
		{
			deck = new DeckWarrior(deckType);
			break;
		}
		case WARLOCK:
		{
			deck = new DeckWarlock(deckType);
			break;

		}
		case HUNTER:
		{
			deck = new DeckHunter(deckType);
			break;
		}
		}
		return deck;
	}

}
