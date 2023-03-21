package tests;

import model.Member;
import model.Product;
import model.Store;

public class tests {

	public static void main(String[] args) {
		Store walmart = new Store();
		
		Product apple = new Product("apple", 5);
		Product orange = new Product("orange", 6);
		Product banana = new Product("banana", 7);
		Product strawberry = new Product("strawberry", 10);
		Product blackberry = new Product("blackberry", 9);
		
		walmart.addProduct(apple);
		walmart.addProduct(orange);
		walmart.addProduct(banana);
		walmart.addProduct(strawberry);
		walmart.addProduct(blackberry);
		System.out.println("----Listing store's products----");
		System.out.println(String.format("walmart has these products:\n %s($%.2f),\n %s($%.2f),\n %s($%.2f),\n %s($%.2f),\n %s($%.2f)",
															   apple.getName(), apple.getPrice(),
															   orange.getName(), orange.getPrice(),
															   banana.getName(), banana.getPrice(), 
															   strawberry.getName(), strawberry.getPrice(),
															   blackberry.getName(), blackberry.getPrice()));
		
		Member alan = new Member("alan", 1, "bronze");
		Member tom = new Member("tom", 2, "silver");
		Member mark = new Member("mark", 3, "gold");
		Member jessica = new Member("jessica", 4, "gold");
		Member emily = new Member("emily", 5, "gold");
		
		walmart.addMember(alan);
		walmart.addMember(tom);
		walmart.addMember(mark);
		walmart.addMember(jessica);
		walmart.addMember(emily);
		System.out.println("\n----Listing store's members----");
		System.out.println(String.format("walmart has these members:\n %s(%s),\n %s(%s),\n %s(%s),\n %s(%s),\n %s(%s)",
															   alan.getName(), alan.getType(),
															   tom.getName(), tom.getType(),
															   mark.getName(), mark.getType(), 
															   jessica.getName(), jessica.getType(),
															   emily.getName(), emily.getType()));
		
		System.out.println("\n----Tom's turn to checkout items----");
		//////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
		//tom adds 3 apples to his cart
		tom.addCart(apple);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		tom.addCart(apple);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		tom.addCart(apple);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		
		//tom removes an apple from his cart
		tom.removeFromCart();
		System.out.println(String.format("%s just removed an item from the cart", tom.getName()));
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		
		//tom adds 3 more apples to his cart
		tom.addCart(apple);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		tom.addCart(apple);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		tom.addCart(apple);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", tom.getName(), tom.getNoItems(), tom.getName(), tom.getPayAmount()));
		//////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
		
		System.out.println("\n----Mark's turn to checkout items----");
		
		//////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
		//mark adds 3 orange to his cart
		mark.addCart(orange);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		mark.addCart(orange);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		mark.addCart(orange);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		
		//mark removes an apple from his cart
		mark.removeFromCart();
		System.out.println(String.format("%s just removed an item from the cart", mark.getName()));
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		
		//mark adds 3 more apples to his cart
		mark.addCart(orange);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		mark.addCart(orange);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		mark.addCart(orange);
		System.out.println(String.format("%s has %d items in his cart. %s's total payment is %.1f", mark.getName(), mark.getNoItems(), mark.getName(), mark.getPayAmount()));
		//////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////

	}

}
