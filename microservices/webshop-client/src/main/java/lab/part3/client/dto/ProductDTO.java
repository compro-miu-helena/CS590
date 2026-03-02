package lab.part3.client.dto;

public class ProductDTO {
    private String productnumber;
    private double price;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO(String productnumber, String description, double price) {
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
