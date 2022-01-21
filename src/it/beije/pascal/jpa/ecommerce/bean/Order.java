package it.beije.pascal.jpa.ecommerce.bean;

import java.time.LocalDate;

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
	
	@Column(name = "date")
	LocalDate date;
	
	@Column(name = "amount")
	double amount;
	
	@Column(name = "id")
	int user_id;
	
}

//CREATE TABLE `order` (
//		  `id` int(11) NOT NULL AUTO_INCREMENT,
//		  `creation_datetime` datetime NOT NULL,
//		  `amount` double NOT NULL,
//		  `user_id` int(11) NOT NULL,
//		  PRIMARY KEY (`id`),
//		  KEY `fk_user_idx` (`user_id`),
//		  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
//		) ENGINE=InnoDB;