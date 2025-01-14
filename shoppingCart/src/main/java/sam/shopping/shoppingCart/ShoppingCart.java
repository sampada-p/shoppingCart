package sam.shopping.shoppingCart;

import sam.shopping.shoppingCart.model.CartItem;
import sam.shopping.shoppingCart.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> cartItems;
    private DecimalFormat decfor = new DecimalFormat("0.00");

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void removeProduct(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    public double calculateItemPriceWithoutTax() {
        return Double.parseDouble(decfor.format(cartItems.stream().mapToDouble(CartItem::getQuantityPrice).sum()));
    }

    public double calculateTotalPrice() {
        return cartItems.stream().mapToDouble(t ->
                getTotalForCartItem(t)).sum();
    }

    private double getTotalForCartItem(CartItem cartItem) {

        double itemPrice = cartItem.getQuantityPrice();
        double taxForItem = itemPrice * cartItem.getProduct().getCategory().getTaxPercent() / 100;
        return Double.parseDouble(decfor.format(itemPrice + taxForItem));
    }
}
