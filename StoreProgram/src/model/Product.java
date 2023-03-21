package model;

public class Product {
	String name;
	double price;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product() {
	}
	
	public String toString() {
		return this.name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}

}
