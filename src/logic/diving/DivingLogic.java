package logic.diving;

import java.util.ArrayList;

import gui.DivingPane;
import inventory.FishInventory;
import logic.fish.BlueDiscus;
import logic.fish.BaseFish;
import logic.fish.Salmon;
import logic.fish.Tuna;
import logic.player.Player;
import utils.Config;
import utils.Goto;
import utils.Input;
import utils.Randomizer;

public class DivingLogic {
	private static DivingLogic instance;
	private boolean isGameStart;
	private boolean isGameEnd;
	private ArrayList<BaseFish> fishList = new ArrayList<>();
	private ArrayList<Rock> rockList = new ArrayList<>();
	private long startTime;
	private long remainingTime;

	private DivingLogic() {
		this.newGame();
	}

	public void newGame() {
		setGameEnd(false);
		setGameStart(true);
		setRemainingTime(Config.DIVING_TIME);
		setStartTime(System.currentTimeMillis());
	}

	public void updateLogic() {
		spawnFish();
		updateFish();
		checkCollisions();
		spawnRock();
		updateRock();
		checkRockCollisions();
		countDownTimer();
		checkGameEnd();
	}

	public void countDownTimer() {
		long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
		setRemainingTime(Config.DIVING_TIME - (int) elapsedTime);
		DivingPane.getInstance().getTimerText().setText("Time: " + remainingTime);
	}

	public void checkGameEnd() {
		if (remainingTime <= 0 && !isGameEnd) {
			setGameEnd(true);
			Player.getInstance().clear();
			Goto.restaurantPage();
		}
	}

	private void spawnFish() {
		if (Randomizer.getRandomizer().nextInt(100) < Config.TUNA_RATE) {
			Tuna newTuna = new Tuna(DivingPane.getInstance());
			fishList.add(newTuna);
		}

		if (Randomizer.getRandomizer().nextInt(100) < Config.SALMON_RATE) {
			Salmon newSalmon = new Salmon(DivingPane.getInstance());
			fishList.add(newSalmon);
		}

		if (Randomizer.getRandomizer().nextInt(100) < Config.BLUEDISCUS_RATE) {
			BlueDiscus newDiscus = new BlueDiscus(DivingPane.getInstance());
			fishList.add(newDiscus);
		}
	}

	private void spawnRock() {
		if (Randomizer.getRandomizer().nextInt(100) < Config.ROCK_RATE) {
			Rock rock = new Rock(DivingPane.getInstance());
			rockList.add(rock);
		}
	}

	private void checkCollisions() {
		ArrayList<BaseFish> caughtFish = new ArrayList<>();

		for (BaseFish fish : fishList) {
			if (fish.getImageView().getBoundsInParent()
					.intersects(Player.getInstance().getImageView().getBoundsInParent())) {
				caughtFish.add(fish);
				DivingPane.getInstance().getChildren().remove(fish.getImageView());
				FishInventory.getInstance().addFish(fish.getName());
			}
		}

		fishList.removeAll(caughtFish);
	}

	private void checkRockCollisions() {
		for (Rock rock : rockList) {
			if (rock.getImageView().getBoundsInParent()
					.intersects(Player.getInstance().getImageView().getBoundsInParent())) {
				Player.getInstance().stun();
				DivingPane.getInstance().getChildren().remove(rock.getImageView());
			}
		}
	}

	private void updateFish() {
		ArrayList<BaseFish> toRemove = new ArrayList<>();
		for (BaseFish fish : fishList) {
			fish.move();

			if (fish.isOutOfBounds()) {
				DivingPane.getInstance().getChildren().remove(fish.getImageView());
				toRemove.add(fish);
			}
		}
		fishList.removeAll(toRemove);
	}

	private void updateRock() {
		ArrayList<Rock> toRemove = new ArrayList<>();
		for (Rock rock : rockList) {
			rock.move();

			if (rock.isOutOfBounds()) {
				DivingPane.getInstance().getChildren().remove(rock.getImageView());
				toRemove.add(rock);
			}
		}
		rockList.removeAll(toRemove);
	}

	public static DivingLogic getInstance() {
		if (instance == null)
			instance = new DivingLogic();
		return instance;
	}

	public boolean isGameStart() {
		return isGameStart;
	}

	public void setGameStart(boolean isGameStart) {
		this.isGameStart = isGameStart;
	}

	public boolean isGameEnd() {
		return isGameEnd;
	}

	public void setGameEnd(boolean isGameEnd) {
		this.isGameEnd = isGameEnd;
	}

	public ArrayList<BaseFish> getFishList() {
		return fishList;
	}

	public void setFishList(ArrayList<BaseFish> fishList) {
		this.fishList = fishList;
	}

	public long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(long remainingTime) {
		this.remainingTime = Math.max(0, remainingTime);
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = Math.max(0, startTime);
	}

}
