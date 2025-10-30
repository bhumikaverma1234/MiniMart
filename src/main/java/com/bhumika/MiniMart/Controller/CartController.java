package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Entity.CartItem;
import com.bhumika.MiniMart.Service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Cart", description = "APIs for managing shopping cart")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Constructor injection (optional, but good practice)
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //Add Item to Cart
    @Operation(summary = "Add item to cart", description = "Add a product to the customer's shopping cart")
    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        CartItem savedItem = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(savedItem);
    }

    //Get all cart items of a user
    @Operation(summary = "View cart", description = "Fetch all items currently in the customer's cart")
    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    //Remove one item from cart
    @DeleteMapping("/{cartItemId}")
    @Operation(summary = "Remove item from cart", description = "Remove a specific item from the customer's cart")
    public void removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
    }

    //Clear full cart of a user
    @Operation(summary = "Clear cart", description = "Remove all items from the customer's cart")
    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
