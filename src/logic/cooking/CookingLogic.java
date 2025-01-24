package logic.cooking;

import gui.RestaurantPane;
import inventory.FishInventory;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.player.Player;
import utils.Config;
import utils.Input;

public class CookingLogic {

	private static boolean isCooking;

	public static void updateLogic() {
		if (Input.getKeyPressed(KeyCode.E) && RestaurantPane.getInstance().getCookingObj().getBoundsInParent()
				.intersects(Player.getInstance().getImageView().getBoundsInParent())) {
			if (!GameLogic.getInstance().isGameEnd()) {
				if (FishInventory.getInstance().getFishCount(OrderLogic.getInstance().getFishname().get(0)) == 0) {
					RestaurantPane.getInstance().getCookingText()
							.setText("You don't have enough this kind of fish to cook");
					return;
				}
				int time;
				CookState AimState;
				if (OrderLogic.getInstance().getFishState().get(0) == "SASHIMI") {
					time = Config.SASHIMI_COOKING_TIME;
					AimState = CookState.SASHIMI;
				} else if (OrderLogic.getInstance().getFishState().get(0) == "GRILLED") {
					time = Config.GRILLED_COOKING_TIME;
					AimState = CookState.GRILLED;
				} else {
					time = Config.FRIED_COOKING_TIME;
					AimState = CookState.FRIED;
				}
				startCooking(OrderLogic.getInstance().getFishname().get(0), time,
						RestaurantPane.getInstance().getCookingText(), AimState);
			}
		}
	}

	public static void startCooking(String fish, int durationSeconds, Text text, CookState state) {
		if (isCooking) {
			text.setText("Already cooking! Wait for the current task to finish.");
			return;
		}
		isCooking = true;
		text.setText("Cooking " + fish + "...");

		Thread cookingThread = new Thread(() -> {
			for (int i = 1; i <= durationSeconds; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Platform.runLater(() -> {
				text.setText(state.toString() + " " + fish + " is ready!");
				isCooking = false;
				FishInventory.getInstance().removeFish(fish);
				OrderLogic.getInstance().getFishname().remove(0);
				OrderLogic.getInstance().getFishState().remove(0);
				OrderLogic.getInstance().getOrderQueue().remove(0);
				OrderLogic.getInstance().SetNextOrderText();
			});

		});
		cookingThread.setDaemon(true);
		cookingThread.start();
	}

}
