package com.codegnan.ecom.model.cart;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.codegnan.ecom.model.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*CREATE TABLE `carts` (
		  `id` int NOT NULL AUTO_INCREMENT,
		  `user_id` int NOT NULL,
		  PRIMARY KEY (`id`),
		  KEY `FK_user_id` (`user_id`),
		  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
		) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
		SELECT * FROM springsecurity.user;

CREATE TABLE `users` (
		  `id` int NOT NULL AUTO_INCREMENT,
		  `email` varchar(50) NOT NULL,
		  `password` varchar(64) NOT NULL,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
		) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


springsecurity:
	CREATE TABLE `user` (
			  `id` bigint NOT NULL AUTO_INCREMENT,
			  `business_title` varchar(255) DEFAULT NULL,
			  `email` varchar(255) DEFAULT NULL,
			  `name` varchar(255) DEFAULT NULL,
			  `password` varchar(255) DEFAULT NULL,
			  `phone` varchar(255) DEFAULT NULL,
			  `username` varchar(255) DEFAULT NULL,
			  PRIMARY KEY (`id`)
			) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;*/


@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<CartItem> items = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }
}
