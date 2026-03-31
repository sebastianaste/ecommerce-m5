package dao;

import config.ConexionBD;
import dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private final ConexionBD db = ConexionBD.getInstance();

    public List<ProductDto> findAll() {
        List<ProductDto> lista = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ProductDto(
                rs.getInt("product_id"),
                rs.getInt("category_id"),
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getDouble("unit_price"),
                rs.getInt("SKU")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<ProductDto> findByName(String name) {
        List<ProductDto> lista = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_name LIKE ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new ProductDto(
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getDouble("unit_price"),
                        rs.getInt("SKU")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ProductDto findById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ProductDto(
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getDouble("unit_price"),
                        rs.getInt("SKU")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void save(ProductDto p) {
        String sql = "INSERT INTO products (category_id, product_name, product_description, unit_price, SKU) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, p.getCategoryId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getProductDescription());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getSku());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ProductDto p) {
        String sql = "UPDATE products SET category_id = ?, product_name = ?, product_description = ?, unit_price = ?, SKU = ? WHERE product_id = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, p.getCategoryId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getProductDescription());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getSku());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
