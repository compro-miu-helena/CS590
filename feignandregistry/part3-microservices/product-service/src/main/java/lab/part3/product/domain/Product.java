package lab.part3.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    @Id
    private String productnumber;
    private double price;
    private String description;

    public Product() {
    }

    public Product(String productnumber, String description, double price) {
        this.productnumber = productnumber;
        this.price = price;
        this.description = description;
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
