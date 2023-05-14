package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.*;

public class Inventory {

	// Attributes of Inventory.
	private ArrayList<Item> inventory;
	private String category = "";
	private FileEditor editor;
	private String message = "";

	// Constructor to make objects of inventory.
	public Inventory(String category) {
		if (category != null && !category.isEmpty())
			this.category = category;
		inventory = new ArrayList<Item>();

		try {
			editor = new FileEditor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// This method to make new item.
	public void newItem(String type, int quantity, double price) {

		if (findItem(type, true) == -1) {
			inventory.add(new Item(type).setQuantity(quantity).setPrice(price));
			message = "";
		}

	}

	// This method to make new brand.
	public void newItem(String brand, String type, int quantity, double price) {

		if (findItem(type, true, brand) == -1) {
			inventory.add(new Brand(brand, type).setQuantity(quantity).setPrice(price));
			message = "";
		}
	}

	// This method to set new quantity of item.
	public void setQuantity(String type, int quantity) {

		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setQuantity(quantity);
			message = "";
		}
	}

	// This method to set new quantity of brand.
	public void setQuantity(String brand, String type, int quantity) {

		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setQuantity(quantity);
			message = "";
		}
	}

	// This method to set new price of item.
	public void setPrice(String type, double price) {
		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setPrice(price);
			message = "";
		}
	}

	// This method to set new price of brand.
	public void setPrice(String brand, String type, double price) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setPrice(price);
			message = "";
		}
	}

	// This method to get quantity of item.
	public int getQuantity(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			message = "";
			return inventory.get(index).getQuantity();
		}
		return -1;
	}

	// This method to get quantity of brand.
	public int getQuantity(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			message = "";
			return inventory.get(index).getQuantity();
		}
		return 0;
	}

	// This method to delete brand.
	public void deleteItem(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			message = "";
			inventory.remove(index);
		}
	}

	// This method to delete item.
	public void deleteItem(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			message = "";
			inventory.remove(index);
		}
	}

	// This method to get price of item.
	public double getPrice(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			message = "";
			return inventory.get(index).getPrice();
		}
		return Double.NaN;
	}

	// This method to get price of brand.
	public double getPrice(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			message = "";
			return inventory.get(index).getPrice();
		}
		return Double.NaN;
	}

	// This method to increase the quantity of item.
	public void update(String type, int qtyIncrease) {

		int index = findItem(type, false);
		if (index != -1) {
			message = "";
			inventory.get(index).update(qtyIncrease);
		}
	}

	// This method to increase the quantity of brand.
	public void update(String brand, String type, int qtyIncrease) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			message = "";
			inventory.get(index).update(qtyIncrease);
		}
	}

	// This method to update price of item.
	public void update(String type, double adjustmentFactor) {
		int index = findItem(type, false);
		if (index != -1) {
			message = "";
			inventory.get(index).update(adjustmentFactor);
		}
	}

	// This method to update price of brand.
	public void update(String brand, String type, double adjustmentFactor) {

		int index = findItem(type, false, brand);
		if (index != -1) {
			message = "";
			inventory.get(index).update(adjustmentFactor);
		}
	}

	// this method to get brand data from database store
	public Brand getBrand(String brand, String type) {

		int index = findItem(type, false, brand);
		if (index != -1) {
			return (Brand) inventory.get(index);
		} else {
			message = "";
			message = "The brand is not exist";
			return null;
		}

	}

	// this method to get item data from database store
	public Item getItem(String type) {

		int index = findItem(type, false);
		if (index != -1) {
			message = "";
			return inventory.get(index);
		} else {
			message = "The item is not exist";
			return null;
		}

	}

	// This method to find index of item in the array list of inventories.
	private int findItem(String type, boolean warningIfFound) {

		int index = -1;
		int itemsFound = 0;

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getType().equals(type) && !(inventory.get(i) instanceof Brand)) {
				index = i;
				itemsFound++;
			}

		}
		if (itemsFound == 0 && !warningIfFound)
			message = "cannot find " + type;
		if (itemsFound != 0 && warningIfFound)
			message = type + " already exists";
		if (itemsFound > 1)
			message = "found more than one brand of " + type;

		return (itemsFound == 1) ? index : -1;
	}

	// This method to find index of brand in the array list of inventories.
	private int findItem(String type, boolean warningIfFound, String brand) {

		int index = -1;
		int itemsFound = 0;

		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (item.getType().equals(type) && item instanceof Brand && ((Brand) item).getBrand().equals(brand)) {
				index = i;
				itemsFound++;
			}

		}
		if (itemsFound == 0 && !warningIfFound)
			message = "cannot find " + brand + " " + type;
		if (itemsFound != 0 && warningIfFound)
			message = brand + " " + type + " already exists";

		return (itemsFound == 1) ? index : -1;
	}

	// This method to get the information of all items and brands
	public void stockReport() {
		double total = 0;
		String text = "";
		for (int i = 0; i < inventory.size(); i++) {

			Item item = inventory.get(i);
			total += item.getPrice() * item.getQuantity();

			if (item instanceof Brand) {
				Brand b = (Brand) item;
				text += b.getBrand() + " " + b.getType() + " - in stock: " + item.getQuantity() + ", price: "
						+ item.getPrice() + "\n";
			} else
				text += item.getType() + " - in stock: " + item.getQuantity() + ", price: " + item.getPrice() + "\n";

		}
		text += "Total value: " + total;

		message = text;
	}

	// this method to print the report on the input file
	public void printOnFile(String file) throws IllegalArgumentException, FileNotFoundException {
		stockReport();
		editor.printStock(file, message);
	}

	// this method to get all data from the inventory database file
	public void readFromDataFile() {
		inventory = editor.readMyData();

		// delete the repeated values in the array
		deleteTheRepeatValues();
		deleteTheRepeatValues();
	}

	// this method to write all data on the inventory database file
	public void printOnDataFile() {
		editor.writeMyData(inventory);
	}

	// this method to remove the repeat values
	private void deleteTheRepeatValues() {

		for (int i = 0; i < inventory.size(); i++) {

			Item item = inventory.get(i);

			if (item instanceof Brand) {
				Brand brand = (Brand) item;
				for (int j = i + 1; j < inventory.size(); j++) {

					if ((inventory.get(j)).equals(brand)) {
						inventory.remove(j);
					}

				}

			} else {
				for (int j = i + 1; j < inventory.size(); j++) {

					if (item.equals(inventory.get(j))) {
						inventory.remove(j);
					}

				}
			}
		}
	}

	// this method to return the message
	public String getMessage() {
		return message;
	}

}
