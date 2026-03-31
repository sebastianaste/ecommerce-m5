package servlets;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product/delete")
public class DeleteProductServlet extends HttpServlet {

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

        request.getRequestDispatcher("/product-delete.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            request.setAttribute("error", "Invalid product ID.");
            request.getRequestDispatcher("/product-delete.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(idStr);
        ProductDao productDao = new ProductDao();

        if (productDao.findById(id) == null) {
            request.setAttribute("error", "No product found with ID: " + id);
            request.getRequestDispatcher("/product-delete.jsp").forward(request, response);
            return;
        }

        productDao.delete(id);
        response.sendRedirect(request.getContextPath() + "/product/list");
    }
}
