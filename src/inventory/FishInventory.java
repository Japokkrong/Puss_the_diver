package inventory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class FishInventory {

	private static FishInventory instance; // Singleton instance
	private Pane inventoryPane;
	private Map<String, Integer> fishCounts; // Dynamic fish count tracking
	private Map<String, Image> fishImages; // Map for dynamic image handling
	private Map<String, ImageView> fishImageViews; // ImageView objects for dynamic inventory UI updates
	private Map<String, Text> fishCountTexts; // Text fields dynamically bound to fish counts

	/**
	 * Private constructor to ensure Singleton behavior
	 */
	private FishInventory() {
		fishCounts = new HashMap<>();
		fishImages = new HashMap<>();
		fishImageViews = new HashMap<>();
		fishCountTexts = new HashMap<>();

		loadFishImages();
		setupInventoryUI();

	}

	/**
	 * Load images for fish types dynamically
	 */
	private void loadFishImages() {
		try {
			fishImages.put("Salmon", new Image(ClassLoader.getSystemResource("fish/salmon.png").toString()));
			fishImages.put("Tuna", new Image(ClassLoader.getSystemResource("fish/tuna.png").toString()));
			fishImages.put("BlueDiscus", new Image(ClassLoader.getSystemResource("fish/BlueDiscus.png").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets up the inventory UI dynamically
	 */
	private void setupInventoryUI() {
		inventoryPane = new Pane();
		inventoryPane.setLayoutX(10);
		inventoryPane.setLayoutY(10);
		inventoryPane.setPrefSize(200, 100);

		int yOffset = 20; // Space between rows dynamically

		for (String fishType : fishImages.keySet()) {
			// Create ImageView for each fish type dynamically
			ImageView imageView = new ImageView(fishImages.get(fishType));
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			imageView.setX(10);
			imageView.setY(yOffset);

			// Create count text dynamically
			Text countText = new Text(fishType + ": 0");
			countText.setFill(Color.WHITE);
			countText.setX(40);
			countText.setY(yOffset + 15);

			// Map these views for easier access later
			fishImageViews.put(fishType, imageView);
			fishCountTexts.put(fishType, countText);

			// Add to the inventory pane
			inventoryPane.getChildren().addAll(imageView, countText);

			// Initialize count
			fishCounts.put(fishType, 0);

			yOffset += 30; // Space each row
		}
	}

	/**
	 * Returns the singleton instance of FishInventory
	 */
	public static FishInventory getInstance() {
		if (instance == null) {
			instance = new FishInventory();
		}
		return instance;
	}

	public Pane getInventoryPane() {
		return inventoryPane;
	}

	/**
	 * Adds a specific fish type to inventory and updates the display
	 */
	public void addFish(String fishType) {
		if (fishCounts.containsKey(fishType)) {
			fishCounts.put(fishType, fishCounts.get(fishType) + 1);
			updateInventoryDisplay();
		}
	}

	/**
	 * Removes a specific fish type if it exists and updates the display
	 */
	public void removeFish(String fishType) {
		if (fishCounts.containsKey(fishType)) {
			fishCounts.put(fishType, Math.max(0, fishCounts.get(fishType) - 1));
			updateInventoryDisplay();
		}
	}

	/**
	 * Updates UI text to reflect the current fish counts dynamically
	 */
	private void updateInventoryDisplay() {
		for (String fishType : fishCounts.keySet()) {
			Text countText = fishCountTexts.get(fishType);
			countText.setText(fishType + ": " + fishCounts.get(fishType));
		}
	}

	/**
	 * Gets the current number of a fish type
	 */
	public int getFishCount(String fishType) {
		return fishCounts.getOrDefault(fishType, 0);
	}

	public void restartFishInventory() {
		for (String fishType : fishCounts.keySet()) {
			fishCounts.put(fishType, 0);
		}
		updateInventoryDisplay();
	}

}