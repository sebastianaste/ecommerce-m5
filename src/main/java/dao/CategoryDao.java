package dao;

import config.ConexionBD;
import dto.CategoryDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private final ConexionBD db = ConexionBD.getInstance();

    public List<CategoryDto> findAll() {
        List<CategoryDto> lista = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new CategoryDto(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}