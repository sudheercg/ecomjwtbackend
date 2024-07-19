package com.codegnan.ecom.model.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;

    // Get cart for a user
    @GetMapping
   // @RolesAllowed("ROLE_CUSTOMER")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCart(@RequestParam Long userId) {
    	
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@getCart");
        Optional<Cart> cart = cartService.findByUserId(userId);
        if (cart.isPresent()) {
        	System.out.println("@@@@@@@@@@@@@@@@@@@@@ISpRESE T");
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.status(404).body("{\"message\": \"Cart not found\"}");
        }
    }

    // Add product to cart
    @PostMapping("/add")
    //@RolesAllowed("ROLE_CUSTOMER")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItemRequest cartItemRequest) {
        try {
            cartService.addProductToCart(cartItemRequest.getUserId(), cartItemRequest.getProductId(), cartItemRequest.getQuantity());
            return ResponseEntity.ok("{\"message\": \"Product added to cart\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"message\": \"Server error\"}");
        }
    }

    // Update product quantity in cart
    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
 //   @RolesAllowed("ROLE_CUSTOMER")
    public ResponseEntity<?> updateProductQuantityInCart(@RequestBody CartItemRequest cartItemRequest) {
        try {
            cartService.updateProductQuantityInCart(cartItemRequest.getUserId(), cartItemRequest.getProductId(), cartItemRequest.getQuantity());
            return ResponseEntity.ok("{\"message\": \"Cart updated\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"message\": \"Server error\"}");
        }
    }
   
    
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteProductFromCart(@RequestBody CartItemRequest cartItemRequest) {
        try {
            cartService.removeItemFromCart(cartItemRequest.getUserId(), cartItemRequest.getProductId());
            return ResponseEntity.ok("{\"message\": \"Product removed from cart\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"message\": \"Server error: " + e.getMessage() + "\"}");
        }
    }
    
    
}
