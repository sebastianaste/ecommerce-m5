package dto;

//product_id INT AUTO_INCREMENT PRIMARY KEY,
//category_id INT NOT NULL,
//product_name VARCHAR(200) NOT NULL,
//product_description TEXT,
//unit_price DECIMAL(9,2) NOT NULL CHECK (unit_price > 0),
//SKU INT NOT NULL UNIQUE,
//FOREIGN KEY (category_id) REFERENCES categories(category_id)

public class ProductDto {
    private int id;
    private int categoryId;
    private String productName;
    private String productDescription;
    private Double unitPrice;
    private int sku;

    public ProductDto() {
    }

    public ProductDto(int id, int categoryId, String productName, String productDescription, Double unitPrice, int sku) {
        this.id = id;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.sku = sku;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }
}
