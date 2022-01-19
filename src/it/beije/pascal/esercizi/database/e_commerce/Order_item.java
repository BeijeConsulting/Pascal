package it.beije.pascal.esercizi.database.e_commerce;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class Order_item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "order_id")
	private int order_id;

	@Column(name = "product_id")
	private int product_id;

	@Column(name = "sell_price")
	private double sell_price;

	@Column(name = "quantity")
	private int quantity;

	public Order_item() {
		super();
	}

	public Order_item(int order_id, int product_id, double sell_price, int quantity) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.sell_price = sell_price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getSell_price() {
		return sell_price;
	}

	public void setSell_price(double sell_price) {
		this.sell_price = sell_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder().append("{ id : ").append(this.id).append(", order_id : ")
				.append(this.order_id).append(", product_id : ").append(this.product_id).append(", sell_price : ")
				.append(this.sell_price).append(", quantity : ").append(this.quantity).append(" }");

		return builder.toString();
	}

}
