package it.beije.pascal.ecommerce;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import it.beije.pascal.ecommerce.bean.Order;
import it.beije.pascal.ecommerce.bean.OrderItem;
import it.beije.pascal.ecommerce.bean.Product;
import it.beije.pascal.ecommerce.exception.OrderException;

public class OrderUtil {

	public static final String DATETIME_FORMAT = "YYYY-MM-DD hh:mm:ss.mmm";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String dateTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
		String s  = LocalDateTime.now().format(formatter);
		return s;
	}
		
	public static void placeOrder(Product chosenProd, int amount) throws OrderException {
		Order order = new Order(0, null, amount, amount);
		
	}

	public static void addToOrder(Order currentOrder, OrderItem orderItem) {
		
	}

}
