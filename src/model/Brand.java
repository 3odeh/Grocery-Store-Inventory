package model;

public class Brand extends Item {

	// Attributes of brand.
	private String brand = "";

	// Constructor to make objects of brand.
	public Brand() {

	}

	// Another constructor to make objects of brand with some attributes.
	public Brand(String brand, String type) {
		super(type);
		if (brand != null && !brand.isEmpty())
			this.brand = brand;
	}

	// This method to get brand.
	public String getBrand() {
		return brand;
	}

	// this method return if the items are equal with brand name and type
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(obj instanceof Brand) {
			Brand item = (Brand) obj;
			if(item.getType().equals(this.getType()) && item.getBrand().equals(this.getBrand()))
				return true;
		}
		
		return super.equals(obj);
	}
}
