package model;

import java.io.Serializable;

public class Item implements Comparable<Item> , Serializable{

	// Attributes of item.
	private String type = "";
	private int quantity;
	private double price;

	// Constructor to make objects of item.
	public Item() {
	}

	// Another constructor to make objects of item with some attributes.
	public Item(String type) {
		if (type != null && !type.isEmpty())
			this.type = type;
	}

	// This method to get type of item.
	public String getType() {
		return type;
	}

	// This method to get quantity of item.
	public int getQuantity() {
		return quantity;
	}

	// This method to set new quantity.
	public Item setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	// This method to get price of item.
	public double getPrice() {
		return price;
	}

	// This method to set new price.
	public Item setPrice(double price) {
		this.price = price;
		return this;
	}

	// This method to increase the quantity.
	public Item update(int qtyIncrease) {
		quantity += qtyIncrease;
		return this;
	}

	// This method to update price.
	public Item update(double adjustmentFactor) {
		price += adjustmentFactor * price;
		return this;
	}

	// This method to compare of two price items.
	@Override
	public int compareTo(Item o) throws IllegalArgumentException{
		if (o == null)
			throw new IllegalArgumentException("The Item is null") ;
		return (int) (this.getPrice() - o.getPrice());
	}
	
	// this method return if the items are equal with type
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj instanceof Brand)
			return false;
		
		if(obj instanceof Item) {
			Item item = (Item) obj;
			if(item.getType().equals(this.getType()))
				return true;
		}
		
		return super.equals(obj);
	}

}
