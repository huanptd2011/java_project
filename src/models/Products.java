package models;

public class Products {
    private int productID;
    private String productName;
    private String description;
    private String brand;
    private double price;
    private int quantity;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Product{" +
               "productID=" + productID +
               ", productName='" + productName + '\'' +
               ", description='" + description + '\'' +
               ", brand='" + brand + '\'' +
               ", quantity=" + quantity +
               // nếu có price thì thêm: ", price=" + price +
               '}';
    }
}
