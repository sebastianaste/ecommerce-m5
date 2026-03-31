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

@WebServlet("/product/new")
public class NewProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("categories", new CategoryDao().findAll());
        request.getRequestDispatcher("/product-new.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String categoryIdStr = request.getParameter("categoryId");
        String unitPriceStr = request.getParameter("unitPrice");
        String skuStr = request.getParameter("sku");

        if (productName == null || productName.trim().isEmpty() ||
                productDescription == null || productDescription.trim().isEmpty() ||
                categoryIdStr == null || unitPriceStr == null || skuStr == null) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/product-new.jsp").forward(request, response);
            return;
        }

        double unitPrice;
        try {
            unitPrice = Double.parseDouble(unitPriceStr);
            if (unitPrice <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Price must be a number greater than 0.");
            request.getRequestDispatcher("/product-new.jsp").forward(request, response);
            return;
        }

        ProductDto p = new ProductDto(0, Integer.parseInt(categoryIdStr), productName.trim(),
                productDescription.trim(), unitPrice, Integer.parseInt(skuStr));

        new ProductDao().save(p);

        response.sendRedirect(request.getContextPath() + "/product/list");
    }
}
