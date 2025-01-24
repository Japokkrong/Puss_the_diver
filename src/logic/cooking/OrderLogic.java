package logic.cooking;

import java.util.ArrayList;
import java.util.List;

import gui.RestaurantPane;
import utils.Config;
import utils.Randomizer;

public class OrderLogic {

	private static OrderLogic instance;
	private List<String> orderQueue;
	private List<String> fishname;
	private List<String> fishState;

	// Private constructor for singleton
	private OrderLogic() {
		this.orderQueue = new ArrayList<>();
		this.fishname = new ArrayList<>();
		this.fishState = new ArrayList<>();
		generateRandomMenu();
	}

	// Public method to get the singleton instance
	public static OrderLogic getInstance() {
		if (instance == null) {
			instance = new OrderLogic();
		}
		return instance;
	}

	// Generates a Randomizer.getRandomizer()om menu
	public void generateRandomMenu() {
		for (int i = 0; i < Config.GOALMENU; i++) {
			String fishType = getRandomFish();
			String state = getRandomState();
			fishname.add(fishType);
			fishState.add(state);
			orderQueue.add(fishType + " - " + state);
		}
	}

	private String getRandomFish() {
		int choice = Randomizer.getRandomizer().nextInt(3);
		switch (choice) {
		case 0:
			return "Salmon";
		case 1:
			return "Tuna";
		default:
			return "BlueDiscus";
		}
	}

	private String getRandomState() {
		int choice = Randomizer.getRandomizer().nextInt(3);
		switch (choice) {
		case 0:
			return "SASHIMI";
		case 1:
			return "GRILLED";
		default:
			return "FRIED";
		}
	}

	public void SetNextOrderText() {
		if (this.getOrderQueue().isEmpty()) {
			RestaurantPane.getInstance().getOrderQueueText().setText("");
			return;
		}
		RestaurantPane.getInstance().getOrderQueueText().setText("Next Order : " + this.getOrderQueue().get(0));
	}

	public List<String> getOrderQueue() {
		return orderQueue;
	}

	public List<String> getFishname() {
		return fishname;
	}

	public List<String> getFishState() {
		return fishState;
	}
	
	public void restartOrder() {
		orderQueue.clear();
		fishname.clear();
		fishState.clear();
		generateRandomMenu();
	}
}