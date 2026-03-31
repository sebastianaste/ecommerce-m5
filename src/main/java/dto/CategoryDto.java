package dto;

//category_id INT AUTO_INCREMENT PRIMARY KEY,
//category_name VARCHAR(100) NOT NULL UNIQUE,
//category_description TEXT

public class CategoryDto {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;

    public CategoryDto() {
    }

    public CategoryDto(int categoryId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
