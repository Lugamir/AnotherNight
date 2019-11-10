package armase.anothernight.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.utils.Utils;

public class CLI {
	private static String version = "v0.42069";
	// TODO : scoreboard persistence
	private static ArrayList<String> scoreboard = new ArrayList<String>();
	
	private static String[] versionStampMsgs = new String[] {
			"~ Another Night ~ " + version + " ~ by Team ARMASE <3",
			"~ Another Night ~ " + version + " ~ by Team ARMASE :3",
			"~ Another Night ~ " + version + " ~ by Team ARMASE ¯\\_(ツ)_/¯",
			"~ Another Night ~ " + version + " ~ by Team ARMASE c:"
			};
	private static String[] welcomeMsgs = new String[] {
			"                       _   _                 _   _ _       _     _   \r\n" + 
			"     /\\               | | | |               | \\ | (_)     | |   | |  \r\n" + 
			"    /  \\   _ __   ___ | |_| |__   ___ _ __  |  \\| |_  __ _| |__ | |_ \r\n" + 
			"   / /\\ \\ | '_ \\ / _ \\| __| '_ \\ / _ \\ '__| | . ` | |/ _` | '_ \\| __|\r\n" + 
			"  / ____ \\| | | | (_) | |_| | | |  __/ |    | |\\  | | (_| | | | | |_ \r\n" + 
			" /_/    \\_\\_| |_|\\___/ \\__|_| |_|\\___|_|    |_| \\_|_|\\__, |_| |_|\\__|\r\n" + 
			"                                                      __/ |          \r\n" + 
			"            ~ Console Version ~                      |___/ " +
			"~ " + version
			};
	private static String[] motds = new String[] {
			"~ Full of hidden bugs for you to find! ~",
			"~ Now with new, more sexist warrior guilds! ~",
			"~ Don't blame the player, blame the game! ~",
			"~ Most certainly not viable on the game market! ~",
			"~ Misusing known bugs might lead to victory! ~",
			"~ Doesn't work when there are whitespaces in the path! ~",
			"~ Now with random messages of the day! ~",
			"~ Trust the system! ~"
			};
	private static String[] badInputMsgs = new String[] {
			"Please choose an available option, friend!",
			"Friend, you should really pay attention to what you try to tell me.",
			"Ehm... yes! Wait, what?"
			};
	private static String[] continueMsgs = new String[] {
			"~ As your opponent falls the sun finally rises...",
			"~ You cringe in disgust of what is left of your enemy...",
			"~ Seems like you actually might get some hours of sleep...",
			"~ Will you ever get into the Amazing Warrior Guild (TM) you wonder..."
			};
	private static String[] buffedMsgs = new String[] {
			" has gotten stronger!",
			" feels a rise of confidence!",
			"'s stats were buffed!"
			};
	private static String[] debuffedMsgs = new String[] {
			" looks with eyes full of fear...",
			" feels limp...",
			" ponders about existence..."
			};
	private static String[] confusedMsgs = new String[] {
			" is lost in confusion...",
			" wonders how much time was won by not checking writing for spelling mistakes...",
			" is busy admiring the world's beauty...",
			" used SPLASH! But nothing happened!",
			" is daydreaming..."
			};
	private static String[] deathMsgs = new String[] {
			" succumbed to all the hurties.",
			" takes a last breath and falls.",
			" has fainted!",
			"'s life has ended."
			};

	public static String readConsoleInput() {
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String outString = scanner.nextLine().toString();
		// scanner.close() breaks everything
		return outString;
	}
	
	public static String enterToContinue() {
		System.out.println();
		System.out.println("Press [ ENTER ] to continue!");
		return readConsoleInput();
	}
	
	public static void writeWelcomeMsg() {
		System.out.println(welcomeMsgs[Utils.generateRandomMinMax(0, welcomeMsgs.length - 1)]);
	}
	
	public static void writeRandomMotd() {
		System.out.println(motds[Utils.generateRandomMinMax(0, motds.length - 1)]);
	}

	public static void writeMainMenu() {
		System.out.println();
		System.out.println("              _                                 \r\n" + 
				"  /\\/\\   __ _(_)_ __     /\\/\\   ___ _ __  _   _ \r\n" + 
				" /    \\ / _` | | '_ \\   /    \\ / _ \\ '_ \\| | | |\r\n" + 
				"/ /\\/\\ \\ (_| | | | | | / /\\/\\ \\  __/ | | | |_| |\r\n" + 
				"\\/    \\/\\__,_|_|_| |_| \\/    \\/\\___|_| |_|\\__,_|\r\n" + 
				"\n     ~  PLAY  | SCORES  |  INFO  |  QUIT  ~" +
				"\n       [play] | [scobo] | [info] | [quit]");
	}
	
	public static void writeBadInput() {
		System.out.println();
		System.out.println(badInputMsgs[Utils.generateRandomMinMax(0, badInputMsgs.length - 1)]);
	}
	
	public static void writeScoreboard() {
		System.out.println();
		System.out.println(
				"                     _                 _ \r\n" + 
				" ___ ___ ___ ___ ___| |_ ___ ___ ___ _| |\r\n" + 
				"|_ -|  _| . |  _| -_| . | . | .'|  _| . |\r\n" + 
				"|___|___|___|_| |___|___|___|__,|_| |___|\r\n" +
				"-----------not-persistent-yet------------");
		if(scoreboard.isEmpty())
			System.out.println("~ no scores yet ~");
		else {
			Collections.reverse(scoreboard);
			scoreboard.forEach((score) -> System.out.println(score));
			Collections.reverse(scoreboard);
		}
	}
	
	public static void writeGameInfo() {
		System.out.println();
		System.out.println("Another Night is a story about a girl named Anni."
				+ "\nAnni wants to get into the Amazing Warrior Guild (TM) but they are sexist."
				+ "\nThe objective is to survive ten nights in the Forest of DooOOOoom"
				+ "\n         to prove your worth to the Amazing Warrior Guild (TM)...");
		System.out.println();
		System.out.println("(ANother NIght -> Anni ~ get it?)"
				+ "\n" + versionStampMsgs[Utils.generateRandomMinMax(0, versionStampMsgs.length - 1)]);
	}

	public static void writeJourneyBeginning() {
		System.out.println();
		System.out.println("~ And so your journey begins...");
	}
	
	public static void writeJourneyContinue() {
		System.out.println();
		System.out.println(continueMsgs[Utils.generateRandomMinMax(0, continueMsgs.length - 1)]);
	}
	
	public static void writeFightMenu() {
		System.out.println();
		System.out.println("Do what? [ atk | bc | su | info ]");
	}
	
	public static void writeFightMenuInfo() {
		System.out.println();
		System.out.println("# atk - Attack | bc - Battle Cry | su - Shields Up"
				+ "\n# Beware of spelling mistakes..."
				+ "\n"
				+ "\nYou used a turn to rethink your possibilities...");
	}
	
	public static void writeNightCount(int nightCount) {
		System.out.println();
		System.out.println(
				" _  _  ____  ___  _   _  ____ \r\n" + 
				"( \\( )(_  _)/ __)( )_( )(_  _)\r\n" + 
				" )  (  _)(_( (_-. ) _ (   )(  \r\n" + 
				"(_)\\_)(____)\\___/(_) (_) (__) \r\n" + 
				"\r\n" + 
				"              " + nightCount);
	}

	public static void writeCreatureAppearance(Creature creature) {
		System.out.println();
		System.out.println("A " + creature.getName() + " appeared!");
	}

	public static void writeCreatureStatus(Creature creature) {
		System.out.println();
		System.out.println("### STATUS: " + creature.getName()
				+ "\nHP: " + creature.getCurrentHp() + " / " + creature.getMaxHp()
				+ " - Pow: " + creature.getPower() + " - Def: " + creature.getDefense());
	}
	
	public static void writeEnemyGotFirstMove(Creature enemy) {
		System.out.println();
		System.out.println(enemy.getName() + " tricks you and gets first turn!");
	}

	public static void writeCreatureAttacksCreature(Creature attacker, Creature receiver) {
		System.out.println();
		System.out.println(attacker.getName() + " attacks " + receiver.getName() + "!");
	}
	
	public static void writeCreatureAttacksCreature(Creature attacker, Creature receiver, int dmg) {
		System.out.println();
		System.out.println(attacker.getName() + " deals " + dmg + " damage to " + receiver.getName() + "!");
	}

	public static void writeCreatureReceivesDamage(Creature creature, int dmg) {
		System.out.println();
		System.out.println(creature.getName() + " receives " + dmg + " damage!");
	}
	
	public static void writeCreatureBattleCry(Creature creature) {
		System.out.println();
		System.out.println(creature.getName() + " screams ferociously!");
	}
	
	public static void writeCreatureShieldsUp(Creature creature) {
		System.out.println();
		System.out.println(creature.getName() + " prepares for the enemy's attack...");
	}

	public static void writeCreatureBuffed(Creature creature) {
		System.out.println();
		System.out.println(creature.getName() + buffedMsgs[Utils.generateRandomMinMax(0, buffedMsgs.length - 1)]);
	}

	public static void writeCreatureDebuffed(Creature creature) {
		System.out.println();
		System.out.println(creature.getName() + debuffedMsgs[Utils.generateRandomMinMax(0, debuffedMsgs.length - 1)]);
	}
	
	public static void writeCreatureConfused(Creature creature) {
		System.out.println();
		System.out.println(creature.getName() + confusedMsgs[Utils.generateRandomMinMax(0, confusedMsgs.length - 1)]);
	}

	public static void writeCreatureDeath(Creature creature) {
		System.out.println();
		System.out.println(creature.getName() + deathMsgs[Utils.generateRandomMinMax(0, deathMsgs.length - 1)]);
	}
	
	public static void writeBossDeath(Creature boss) {
		System.out.println();
		System.out.println(
				"Finally, " + boss.getName() + "'s body slowly descends to the ground."
				+ "\nIt lays there quietly with its colorless eyes staring into nothingness.."
				+ "\n"
				+ "\nYou look at the lifeless corpse of the " + boss.getName() + "..."
				+ "\n'What a shame that such an ancient being had to die only for me"
				+ "\n  to get into that stupid warrior guild...' you say to yourself."
				+ "\nYou walk up to the " + boss.getName() + " and gently close it's eyes."
				+ "\nHaving learned a valuable lesson about the life of adventurers you stand up"
				+ "\n                     and make your way to the Amazing Warrior Guild (TM)...");
	}
	
	public static void writeYouDied() {
		System.out.println();
		System.out.println(
				"                  (            (     \r\n" + 
				" (          (     )\\ )(    (  )\\ )  \r\n" + 
				" )\\ )  (   ))\\   (()( )\\  ))\\(()/(  \r\n" + 
				"(()/(  )\\ /((_)   ((_)(_)/((_)((_)) \r\n" + 
				" )(_))((_|_))(    _| |(_|_))  _| |  \r\n" + 
				"| || / _ \\ || | / _` || / -_) _` |  \r\n" + 
				" \\_, \\___/\\_,_| \\__,_||_\\___\\__,_|  \r\n" + 
				" |__/                                "+ 
				"");
	}
	
	public static void writeScoreOnRunEnd(Creature player, int nightReached) {
		System.out.println();
		if(nightReached <= 3) {
			System.out.println("Haha, you see?"
					+ "\nThis is exactly why we don't accept you in out guild"
					+ "\nNight reached : " + nightReached
					+ "\nPathetic!");
		} else if(nightReached <= 6)
			System.out.println("That wasn't half bad."
					+ "\nBut I do expect more from you if you want join our Amazing Warrior Guild (TM)."
					+ "\nNight reached : " + nightReached);
		else if(nightReached <= 9)
			System.out.println("Not bad, but not good enough for the Amazing Warrior Guild (TM)!"
					+ "\nNight reached : " + nightReached);
		else if(nightReached >= 10 && !player.isAlive())
			System.out.println("What a shame to die on the last night..."
					+ "\nOnly a little more and you would've shown those sexists!"
					+ "\nNight reached : " + nightReached);
		else if(nightReached >= 10 && player.isAlive()) {
			System.out.println("Very well, it seems that you've proven us wrong, girlie."
					+ "\nWelcome to the Amazing Warrior Guild (TM)!"
					+ "\nLet me introduce you to the dudes...");
			System.out.println();
			System.out.println("Night reached : " + nightReached
					+ "\nSurvived with " + player.getCurrentHp() + " / " + player.getMaxHp() + " HP");
		} else
			System.out.println("Something broke, your score was lost to the Bad Byte Gang!");
	}

	public static void writeExitMsg() {
		System.out.println();
		System.out.println("This is goodbye I guess."
				+ "\nFarewell, my friend...");
	}
	
	public static void writeAddScore() {
		System.out.println();
		System.out.println("Want to add your score to the scoreboard? [ y | n ]");
	}
	
	public static String createScoreStringNameNightHp(String name, int night, int hpLeft) {
		String scoreEntry = "~ " + Utils.getDateTimeStamp() + " - " + name + " reached night " + night;
		if(night >= 10 && hpLeft <= 0)
			scoreEntry += " but died...";
		else if(night >= 10 && hpLeft > 0)
			scoreEntry += " with " + hpLeft + " HP to spare!";
		return scoreEntry;
	}
	
	public static void writeDontSaveScore() {
		System.out.println();
		System.out.println("Don't worry, I'll take this secret to my grave!");
	}
	
	public static void writeAskName() {
		System.out.println();
		System.out.println("Great decision! What's your name, honey?");
	}
	
	public static void addScoreToScoreboard(String scoreString) {
		scoreboard.add(scoreString);
		System.out.println();
		System.out.println("Score was added to scoreboard!");
	}

	// ### GETTERS & SETTERS
	
	public static ArrayList<String> getScoreboard() {
		return scoreboard;
	}
}
