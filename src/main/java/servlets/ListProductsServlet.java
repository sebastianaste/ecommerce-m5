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

@WebServlet("/product/list")
public class ListProductsServlet extends HttpServlet  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<ProductDto> list = productDao.findAll();

        request.setAttribute("list", list);
        request.getRequestDispatcher("/product-list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchId = request.getParameter("searchId");
        ProductDao productDao = new ProductDao();
        List<ProductDto> list = productDao.findByName(searchId);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/product-list.jsp").forward(request, response);
    }
}
