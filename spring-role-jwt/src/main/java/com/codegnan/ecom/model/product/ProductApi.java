package com.codegnan.ecom.model.product;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductApi {

	@Autowired private ProductRepository repo;
	
	@PostMapping
	//@RolesAllowed("ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		System.out.println("!!!!!!!!!!!!!!!In product create!!!!!!!!!!!!!!!!!!");
		Product savedProduct = repo.save(product);
		URI productURI = URI.create("/products/" + savedProduct.getId());
		return ResponseEntity.created(productURI).body(savedProduct);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	//@RolesAllowed({"ADMIN", "USER"})
	public List<Product> list() {
		return repo.findAll();
	}
}
