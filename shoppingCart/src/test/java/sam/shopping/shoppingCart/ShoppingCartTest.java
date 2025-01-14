package sam.shopping.shoppingCart;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.shopping.shoppingCart.model.Category;
import sam.shopping.shoppingCart.model.Product;

import java.text.DecimalFormat;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart ;
    private Category category = new Category("Food",0);

    private Category category1 = new Category("Clothing",10);

    private Category category2 = new Category("Cosmatics",12);

   @BeforeEach
   void setup(){
       shoppingCart = new ShoppingCart() ;
   }

    @Test
    public void testAddProduct(){
        Product product = new Product(1, "milk", 1,category);
        shoppingCart.addProduct(product,3);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 1);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getProduct().getPrice() == 1);
   }

    @Test
    public void testAddSameProduct(){
        Product product = new Product(1, "milk", 1,category);
        shoppingCart.addProduct(product,3);
        product = new Product(1, "milk", 1,category);
        shoppingCart.addProduct(product,5);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 1);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity()== 8) ;
   }

   @Test
   public void testAddMultipleProducts(){
       Product product = new Product(2,"Dress",12,category1);
       shoppingCart.addProduct(product,4);
       product =  new Product(3, "bread",2,category);
       shoppingCart.addProduct(product,5);
       product = new Product(4,"lipstick",10,category2);
       shoppingCart.addProduct(product,5);
       Assert.assertTrue(shoppingCart.getCartItems().size() == 3);
       Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity() == 4);
       Assert.assertTrue(shoppingCart.getCartItems().get(1).getQuantity() == 5);
       Assert.assertTrue(shoppingCart.getCartItems().get(2).getQuantity() == 5);
   }

    @Test
    public void testAddSameProductToMultipleItemCart(){
        Product product = new Product(2,"Dress",12,category1);
        shoppingCart.addProduct(product,4);
        product =  new Product(3, "bread",2,category);
        shoppingCart.addProduct(product,5);
        product = new Product(4,"lipstick",10,category2);
        shoppingCart.addProduct(product,5);
        product = new Product(2,"Dress",12,category1);
        shoppingCart.addProduct(product,6);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 3);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity() == 10);
        Assert.assertTrue(shoppingCart.getCartItems().get(1).getQuantity() == 5);
        Assert.assertTrue(shoppingCart.getCartItems().get(2).getQuantity() == 5);
    }

    @Test
    public void testRemoveProduct(){
        Product product = new Product(2,"Dress",12,category1);
        shoppingCart.addProduct(product,4);
        product =  new Product(3, "bread",2,category);
        shoppingCart.addProduct(product,5);
        product = new Product(4,"lipstick",10,category2);
        shoppingCart.addProduct(product,5);
        product = new Product(2,"Dress",12,category1);
        shoppingCart.addProduct(product,6);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 3);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity() == 10);
        Assert.assertTrue(shoppingCart.getCartItems().get(1).getQuantity() == 5);
        Assert.assertTrue(shoppingCart.getCartItems().get(2).getQuantity() == 5);
        shoppingCart.removeProduct(2);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 2);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity() == 5);
        Assert.assertTrue(shoppingCart.getCartItems().get(1).getQuantity() == 5);
    }

    @Test
    public void testCalculateItemPriceWithoutTax(){
        Product product = new Product(2,"Dress",12.2458,category1);
        shoppingCart.addProduct(product,4);
        product =  new Product(3, "bread",2.2643,category);
        shoppingCart.addProduct(product,5);
        product = new Product(4,"lipstick",10.8724,category2);
        shoppingCart.addProduct(product,5);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 3);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity() == 4);
        Assert.assertTrue(shoppingCart.getCartItems().get(1).getQuantity() == 5);
        Assert.assertTrue(shoppingCart.getCartItems().get(2).getQuantity() == 5);
        double total = shoppingCart.calculateItemPriceWithoutTax();
        Assert.assertTrue(total == 114.67 );
    }

    @Test
    public void testcalculateTotalPrice(){
        Product product = new Product(2,"Dress",12.2458,category1);
        shoppingCart.addProduct(product,4);
        product =  new Product(3, "bread",2.2643,category);
        shoppingCart.addProduct(product,5);
        product = new Product(4,"lipstick",10.8724,category2);
        shoppingCart.addProduct(product,5);
        Assert.assertTrue(shoppingCart.getCartItems().size() == 3);
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getQuantity() == 4);
        Assert.assertTrue(shoppingCart.getCartItems().get(1).getQuantity() == 5);
        Assert.assertTrue(shoppingCart.getCartItems().get(2).getQuantity() == 5);
        double total = shoppingCart.calculateTotalPrice();
        Assert.assertTrue(total == 126.09 );
        Assert.assertTrue(shoppingCart.getCartItems().get(0).getProduct().getPrice() == 12.2458);
        Assert.assertTrue(shoppingCart.getCartItems().get(1).getProduct().getPrice() == 2.2643);
        Assert.assertTrue(shoppingCart.getCartItems().get(2).getProduct().getPrice() == 10.8724);
    }

    @Test
    public void testTaxForCart(){
        Product product = new Product(2,"Dress",12.2458,category1);
        shoppingCart.addProduct(product,4);
        product =  new Product(3, "bread",2.2643,category);
        shoppingCart.addProduct(product,5);
        product = new Product(4,"lipstick",10.8724,category2);
        shoppingCart.addProduct(product,5);
        double priceWitoutTax = shoppingCart.calculateItemPriceWithoutTax();
        double total = shoppingCart.calculateTotalPrice();
        double tax = total - priceWitoutTax ;
        DecimalFormat decfor = new DecimalFormat("0.00");
        Assert.assertTrue(Double.parseDouble(decfor.format(tax)) == 11.42 );
    }

}