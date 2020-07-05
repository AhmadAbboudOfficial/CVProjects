/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds;

/**
 *
 * @author chaosprince
 */
public class Product {

    private int Id;
    private String name;
    private int ProductNumber;
    private float price;

    public Product(int Id, String name, int ProductNumber, float price) {
        this.Id = Id;
        this.name = name;
        this.ProductNumber = ProductNumber;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public int getProductNumber() {
        return ProductNumber;
    }

    public float getPrice() {
        return price;
    }

}
