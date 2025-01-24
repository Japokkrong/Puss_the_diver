package logic.player;

import gui.PaneState;
import gui.RootPane;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import logic.Interface.Movable;
import utils.Config;
import utils.Goto;
import utils.Input;

public class Player implements Movable {
	private Image idleImage;
	private Image walkImage;
	private Image stunImage;
	private Image winImage;
	private Image loseImage;
	private ImageView playerImageView;
	private static Player instance;
	private PlayerState state;
	private double horizontalSpeed;
	private double verticalSpeed;
	private Direction currentDirection;
	private Thread stunThread;

	private Player(double x, double y) {
		this.horizontalSpeed = 0;
		this.verticalSpeed = 0;
		currentDirection = Direction.RIGHT;
		state = PlayerState.IDLE;
		loadResource();
		this.playerImageView = new ImageView(idleImage);
		this.playerImageView.setFitWidth(Config.PLAYER_SIZE);
		this.playerImageView.setFitHeight(Config.PLAYER_SIZE);
		this.setPos(x, y);
	}

	public void update() {
		if (state.equals(PlayerState.WIN)) {
			playerImageView.setImage(winImage);
			if (!RootPane.getInstance().getCurrentPane().equals(PaneState.RESTAURANT))
				Goto.restaurantPage();
			return;
		} else if (state.equals(PlayerState.LOSE)) {
			playerImageView.setImage(loseImage);
			if (!RootPane.getInstance().getCurrentPane().equals(PaneState.RESTAURANT))
				Goto.restaurantPage();
			return;
		} else if (state.equals(PlayerState.STUN)) {
			return;
		}
		if (RootPane.getInstance().getCurrentPane().equals(PaneState.RESTAURANT)
				|| RootPane.getInstance().getCurrentPane().equals(PaneState.DIVING)) {
			if (Input.getKeyPressed(KeyCode.A)) {
				horizontalSpeed = -Config.PLAYER_SPEED;
				currentDirection = Direction.LEFT;
				state = PlayerState.WALK;
			}
			if (Input.getKeyPressed(KeyCode.D)) {
				horizontalSpeed = Config.PLAYER_SPEED;
				currentDirection = Direction.RIGHT;
				state = PlayerState.WALK;
			}
			if (!Input.getKeyPressed(KeyCode.A) && !Input.getKeyPressed(KeyCode.D)) {
				horizontalSpeed = 0;
				state = PlayerState.IDLE;
			}
		}
		if (RootPane.getInstance().getCurrentPane().equals(PaneState.DIVING)) {
			if (Input.getKeyPressed(KeyCode.W)) {
				verticalSpeed = -Config.PLAYER_SPEED;
				state = PlayerState.WALK;
			}
			if (Input.getKeyPressed(KeyCode.S)) {
				verticalSpeed = Config.PLAYER_SPEED;
				state = PlayerState.WALK;
			}
			if (!Input.getKeyPressed(KeyCode.W) && !Input.getKeyPressed(KeyCode.S)) {
				verticalSpeed = 0;
				state = PlayerState.IDLE;
			}
		}
		updatePlayerImage();
		move();
	}

	public double getX() {
		return playerImageView.getX();
	}

	public double getY() {
		return playerImageView.getY();
	}

	public void setPos(double x, double y) {
		this.playerImageView.setX(x);
		this.playerImageView.setY(y);
	}

	public void move() {
		double newX = playerImageView.getX() + horizontalSpeed;
		double newY = playerImageView.getY() + verticalSpeed;

		playerImageView.setX(Math.max(0, Math.min(newX, Config.SCREEN_WIDTH)));

		if (newY >= 0 && newY <= Config.SCREEN_HEIGHT - playerImageView.getFitHeight()) {
			playerImageView.setY(newY);
		}
	}

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 1.25 + 10);
		}
		return instance;
	}

	private void loadResource() {
		idleImage = new Image(ClassLoader.getSystemResource("player/idle.png").toString());
		walkImage = new Image(ClassLoader.getSystemResource("player/walk.png").toString());
		stunImage = new Image(ClassLoader.getSystemResource("player/stun.png").toString());
		winImage = new Image(ClassLoader.getSystemResource("player/win.png").toString());
		loseImage = new Image(ClassLoader.getSystemResource("player/lose.png").toString());
	}

	public ImageView getImageView() {
		return playerImageView;
	}

	private void updatePlayerImage() {
		if (horizontalSpeed != 0 || verticalSpeed != 0) {
			playerImageView.setImage(walkImage);
			playerImageView.setScaleX(currentDirection == Direction.RIGHT ? 1 : -1);
		} else {
			playerImageView.setImage(idleImage);
			playerImageView.setScaleX(1);
		}
	}

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

	public void stun() {
		if (state.equals(PlayerState.STUN)) {
			return;
		}

		stunThread = new Thread(() -> {
			try {
				Platform.runLater(() -> {
					playerImageView.setImage(stunImage);
					state = PlayerState.STUN;
				});

				Thread.sleep(Config.STUN_TIME * 1000);

				Platform.runLater(() -> {
					if (state.equals(PlayerState.STUN)) {
						playerImageView.setImage(idleImage);
						state = PlayerState.IDLE;
					}
				});

			} catch (InterruptedException e) {
				Platform.runLater(() -> {
					playerImageView.setImage(idleImage);
					state = PlayerState.IDLE;
				});
				Thread.currentThread().interrupt();
			}
		});

		stunThread.setDaemon(true);
		stunThread.start();
	}

	public void cancelStun() {
		if (stunThread != null && state.equals(PlayerState.STUN)) {
			stunThread.interrupt();
			state = PlayerState.IDLE;
		}
	}

	public void clearSpeed() {
		horizontalSpeed = 0;
		verticalSpeed = 0;
	}

	public void clear() {
		cancelStun();
		clearSpeed();
		setPos(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 1.25 + 10);
	}
}