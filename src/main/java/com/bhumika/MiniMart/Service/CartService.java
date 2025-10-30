package com.bhumika.MiniMart.Service;

import com.bhumika.MiniMart.Entity.CartItem;
import java.util.List;

public interface CartService {
    List<CartItem> getCartItems(Long userId);   // ✅ userId parameter added
    CartItem addToCart(Long userId, Long productId, int quantity); // ✅ updated signature
    void removeCartItem(Long cartItemId);       // ✅ fixed type
    void clearCart(Long userId);                // ✅ clear only one user’s cart
}

