package servlets;

import dao.CategoryDao;
import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/product/edit", "/product/update"})
public class EditProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        ProductDao productDao = new ProductDao();

        if (idStr != null && !idStr.trim().isEmpty()) {
            ProductDto product = productDao.findById(Integer.parseInt(idStr));
            if (product == null) {
                request.setAttribute("error", "No product found with ID: " + idStr);
            } else {
                request.setAttribute("product", product);
            }
        } else if (name != null && !name.trim().isEmpty()) {
            List<ProductDto> results = productDao.findByName(name);
            if (results.isEmpty()) {
                request.setAttribute("error", "No products found matching: " + name);
            } else if (results.size() == 1) {
                request.setAttribute("product", results.get(0));
            } else {
                request.setAttribute("results", results);
            }
        }
        request.setAttribute("categories", new CategoryDao().findAll());
        request.getRequestDispatcher("/product-edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String categoryIdStr = request.getParameter("categoryId");
        String unitPriceStr = request.getParameter("unitPrice");
        String skuStr = request.getParameter("sku");

        if (productName == null || productName.trim().isEmpty() ||
                productDescription == null || productDescription.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/product-edit.jsp").forward(request, response);
            return;
        }

        double unitPrice;
        try {
            unitPrice = Double.parseDouble(unitPriceStr);
            if (unitPrice <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Price must be a number greater than 0.");
            request.getRequestDispatcher("/product-edit.jsp").forward(request, response);
            return;
        }

        ProductDto p = new ProductDto(Integer.parseInt(idStr), Integer.parseInt(categoryIdStr),
                productName.trim(), productDescription.trim(), unitPrice, Integer.parseInt(skuStr));

        new ProductDao().update(p);
        response.sendRedirect(request.getContextPath() + "/product/list");
    }
}
