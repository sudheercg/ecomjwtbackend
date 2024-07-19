package com.codegnan.ecom.model.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.ecom.dao.UserRepository;
import com.codegnan.ecom.model.product.Product;
import com.codegnan.ecom.model.product.ProductRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Optional<Cart> findByUserId(Long userId) {
        return Optional.ofNullable(cartRepository.findByUserId(userId));
    }

    public void addProductToCart(Long userId, Integer productId, int quantity) throws Exception {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findById(userId).orElseThrow(() -> new Exception("User not found")));
            cart = cartRepository.save(cart);
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));
        Optional<CartItem> cartItemOpt = cartItemRepository.findByCartAndProduct(cart, product);
        CartItem cartItem;
        if (cartItemOpt.isPresent()) {
            cartItem = cartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        cartItemRepository.save(cartItem);
    }

    public void updateProductQuantityInCart(Long userId, Integer productId, int quantity) throws Exception {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new Exception("Cart not found");
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new Exception("Product not found in cart"));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
    
    
    public void removeItemFromCart(Long userId, Integer productId) throws Exception {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new Exception("Cart not found");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new Exception("Product not found in cart"));
        cartItemRepository.delete(cartItem);
    }
    
    
    
    
}
