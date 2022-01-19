package it.beije.pascal.esercizi.database.e_commerce;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "creation_datetime")
	private LocalDateTime creation_datetime;

	@Column(name = "amount")
	private double amount;

	@Column(name = "user_id")
	private int user_id;

	public Order() {
		super();
	}

	public Order(double amount, int user_id) {
		super();
		this.creation_datetime = LocalDateTime.now();
		this.amount = amount;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreation_datetime() {
		return creation_datetime;
	}

	public void setCreation_datetime(LocalDateTime creation_datetime) {
		this.creation_datetime = creation_datetime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder().append("{ id : ").append(this.id).append(", creation_datetime : ")
				.append(this.creation_datetime).append(", amount : ").append(this.amount).append(", user_id : ")
				.append(this.user_id).append(" }");

		return builder.toString();
	}

}
