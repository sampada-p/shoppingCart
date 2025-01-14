package sam.shopping.shoppingCart.model;

public class Category {

    private String name;
    private int taxPercent;

    public String getName() {
        return name;
    }

    public void setTaxPercent(int taxPercent) {
        this.taxPercent = taxPercent;
    }

    public int getTaxPercent() {
        return taxPercent;
    }

    public Category(String name, int taxPercent) {
        this.name = name;
        this.taxPercent = taxPercent;
    }

}
