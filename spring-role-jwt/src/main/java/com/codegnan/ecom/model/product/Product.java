package com.codegnan.ecom.model.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;*/

import com.fasterxml.jackson.annotation.JsonBackReference;

//import net.codejava.cart.CartItem;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    
    @Column(nullable = false, length = 128)
   // @NotNull @Length(min = 5, max = 128)
    private String name;
    
    private float price;
    
   

    public Product() {
    }
    
    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

   
}
