////////////////////////////////////////////////////////////////////
// [ALESSANDRO] [FLORI] [1186916]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {

    public enum itemType {
        Gelati,
        Budini,
        Bevande;
    }

    private itemType category;
    private String name;
    private double price;

    public MenuItem(itemType category, String name, double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public MenuItem() {

    }

    public itemType getCategory() {
        return category;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
    
}