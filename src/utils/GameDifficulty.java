package utils;

public class GameDifficulty {
	public static void easyGame() {
		Config.PLAYER_SPEED = 5;
		Config.BLUEDISCUS_SPEED = 10;
		Config.TUNA_SPEED = 5;
		Config.SALMON_SPEED = 8;
		Config.ROCK_SPEED = 2;
		Config.BLUEDISCUS_RATE = 1;
		Config.TUNA_RATE = 10;
		Config.SALMON_RATE = 5;
		Config.ROCK_RATE = 0.1;
		Config.STUN_TIME = 2;
		Config.GAME_TIME = 120;
		Config.DIVING_TIME = 15;
		Config.GOALMENU = 5;
		Config.SASHIMI_COOKING_TIME = 2;
		Config.GRILLED_COOKING_TIME = 3;
		Config.FRIED_COOKING_TIME = 5;
	}

	public static void mediumGame() {
		Config.PLAYER_SPEED = 5;
		Config.BLUEDISCUS_SPEED = 40;
		Config.TUNA_SPEED = 10;
		Config.SALMON_SPEED = 20;
		Config.ROCK_SPEED = 3;
		Config.BLUEDISCUS_RATE = 0.001;
		Config.TUNA_RATE = 10;
		Config.SALMON_RATE = 0.1;
		Config.ROCK_RATE = 3;
		Config.STUN_TIME = 3;
		Config.GAME_TIME = 120;
		Config.DIVING_TIME = 30;
		Config.GOALMENU = 10;
		Config.SASHIMI_COOKING_TIME = 4;
		Config.GRILLED_COOKING_TIME = 6;
		Config.FRIED_COOKING_TIME = 8;
	}

	public static void hardGame() {
		Config.PLAYER_SPEED = 5;
		Config.BLUEDISCUS_SPEED = 200;
		Config.TUNA_SPEED = 80;
		Config.SALMON_SPEED = 150;
		Config.ROCK_SPEED = 10;
		Config.BLUEDISCUS_RATE = 0.0001;
		Config.TUNA_RATE = 1;
		Config.SALMON_RATE = 0.01;
		Config.ROCK_RATE = 20;
		Config.STUN_TIME = 3;
		Config.GAME_TIME = 150;
		Config.DIVING_TIME = 5;
		Config.GOALMENU = 15;
		Config.SASHIMI_COOKING_TIME = 5;
		Config.GRILLED_COOKING_TIME = 7;
		Config.FRIED_COOKING_TIME = 10;
	}

	public static void hellGame() {
		Config.PLAYER_SPEED = 3;
		Config.BLUEDISCUS_SPEED = 50;
		Config.TUNA_SPEED = 50;
		Config.SALMON_SPEED = 50;
		Config.ROCK_SPEED = 10;
		Config.BLUEDISCUS_RATE = 0.00000001;
		Config.TUNA_RATE = 0.00000001;
		Config.SALMON_RATE = 0.00000001;
		Config.ROCK_RATE = 100;
		Config.STUN_TIME = 10;
		Config.GAME_TIME = 200;
		Config.DIVING_TIME = 5;
		Config.GOALMENU = 15;
		Config.SASHIMI_COOKING_TIME = 10;
		Config.GRILLED_COOKING_TIME = 10;
		Config.FRIED_COOKING_TIME = 10;
	}

}