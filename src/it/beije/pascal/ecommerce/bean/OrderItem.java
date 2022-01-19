package it.beije.pascal.ecommerce.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

/*
 * CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `sell_price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_idx` (`order_id`),
  KEY `fk_product_idx` (`product_id`),
  CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB
 */

@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@Column(name = "order_id")
	private int orderId;
	
	@ManyToOne
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "sell_price")
	private double sellPrice;
	
	@Column(name = "quantity")
	private int quantity;

	public OrderItem(int id, int orderId, int productId, double sellPrice, int quantity) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.sellPrice = sellPrice;
		this.quantity = quantity;
	}

	public OrderItem(Order order, Product product, int quantity) {
		this.orderId = order.getId();
		this.productId = product.getId();
		this.quantity = quantity;
		this.sellPrice = product.getPrice();
		order.setAmount(order.getAmount()+ (product.getPrice() * quantity));
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
