package logic;

import gui.DivingPane;
import gui.PaneState;
import gui.RestaurantPane;
import gui.RootPane;
import inventory.FishInventory;
import logic.cooking.CookingLogic;
import logic.cooking.OrderLogic;
import logic.diving.DivingLogic;
import logic.player.Player;
import logic.player.PlayerState;
import utils.Config;

public class GameLogic {
	private static GameLogic instance;
	private boolean isGameStart;
	private boolean isGameEnd;
	private long startTime;
	private long remainingTime;

	private GameLogic() {
		this.newGame();
	}

	public void newGame() {
		if (isGameEnd) {
			OrderLogic.getInstance().restartOrder();
			RestaurantPane.getInstance().getWinText().setVisible(false);
			RestaurantPane.getInstance().getGameOverText().setVisible(false);
			RestaurantPane.getInstance().getOverlay().setVisible(false);
			RestaurantPane.getInstance().getQuitButton().setVisible(false);
			RestaurantPane.getInstance().getCookingText().setText("");
		}
		setGameEnd(false);
		setGameStart(false);
	}

	public void countDownTimer() {
		if (isGameStart) {
			long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
			setRemainingTime(Config.GAME_TIME - (int) elapsedTime);
			RestaurantPane.getInstance().getTimerText().setText("Time: " + remainingTime);
		}
	}

	public void startGame() {
		setRemainingTime(Config.GAME_TIME);
		setStartTime(System.currentTimeMillis());
		setGameStart(true);
		Player.getInstance().clear();
		Player.getInstance().setState(PlayerState.IDLE);
		FishInventory.getInstance().restartFishInventory();
		OrderLogic.getInstance().restartOrder();
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

	public void logicUpdate() {
		if (!isGameStart) {
			return;
		}
		Player.getInstance().update();
		if (RootPane.getInstance().getCurrentPane().equals(PaneState.DIVING)) {
			DivingLogic.getInstance().updateLogic();
		}
		if (RootPane.getInstance().getCurrentPane().equals(PaneState.RESTAURANT)) {
			CookingLogic.updateLogic();
		}
		if (isGameEnd) {
			return;
		}
		countDownTimer();
		checkGameEnd();
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	public void checkGameEnd() {
		if (!isGameEnd && OrderLogic.getInstance().getOrderQueue().isEmpty()) {
			setGameEnd(true);
			WINGame();
		}
		if (!isGameEnd && remainingTime <= 0) {
			setGameEnd(true);
			gameOver();
		}
	}

	private void WINGame() {
		RestaurantPane.getInstance().getWinText().setVisible(true);
		RestaurantPane.getInstance().getOverlay().setVisible(true);
		RestaurantPane.getInstance().getQuitButton().setVisible(true);
		Player.getInstance().setState(PlayerState.WIN);
		Player.getInstance().clear();
	}

	private void gameOver() {
		RestaurantPane.getInstance().getGameOverText().setVisible(true);
		RestaurantPane.getInstance().getOverlay().setVisible(true);
		RestaurantPane.getInstance().getQuitButton().setVisible(true);
		Player.getInstance().setState(PlayerState.LOSE);
		Player.getInstance().clear();
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
