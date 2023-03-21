package model;

public class Member {
	String name;
	int id;
	String type; //type: bronze, silver, gold

	//Assuming member can purchase at most 5 products
	Product theirCart[] = new Product[5];
	int noItems = 0;

	double payAmount;

	public Member(String name, int id, String type) {
		this.name = name;
		this.id = id;
		this.type = type;
	}

	public String toString() {
		return this.name;
	}

	public void addCart(Product product) {

		if(this.noItems < 5) {
			if(this.type == "bronze") {
				payAmount += product.getPrice();
			}
			else if (this.type == "silver") {
				payAmount += product.getPrice() * 0.9;
			}
			else if (this.type == "gold") {
				payAmount += product.getPrice() * 0.85;
			}

			this.theirCart[noItems] = product;

			noItems ++;
		}

	}

	public void removeFromCart() {


		if(this.type == "bronze") {
			payAmount -= this.theirCart[noItems - 1].getPrice();
		}
		else if (this.type == "silver") {
			payAmount -= this.theirCart[noItems - 1].getPrice() * 0.9;
		}
		else if (this.type == "gold") {
			payAmount -= this.theirCart[noItems - 1].getPrice() * 0.85;
		}

		this.theirCart[noItems - 1] = null;
		noItems --;
	}

	public String getName() {
		return name;
	}
	
	public int getNoItems() {
		return noItems;
	}
	
	public double getPayAmount() {
		return payAmount;
	}
	
	public String getType() {
		return type;
	}



}
