package model;

public class Store {

	//Assuming store has at most 5 products
	Product products[] = new Product[5];
	int numberOfProducts = 0;

	//Assuming store can have at most 5 members
	Member members[] = new Member[5];
	int numberOfMembers = 0;


	public Store() {
	}

	public void addProduct(Product product) {
		if(numberOfProducts < 5) {
			this.products[numberOfProducts] = product;
		}
		numberOfProducts++;
		
	}

	public void addMember(Member member) {
		if(numberOfMembers < 5) {
			this.members[numberOfMembers] = member;
		}
		numberOfMembers++;

	}



}

