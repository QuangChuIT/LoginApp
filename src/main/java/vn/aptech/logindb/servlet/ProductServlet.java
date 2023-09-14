package vn.aptech.logindb.servlet;


import vn.aptech.logindb.entity.Product;
import vn.aptech.logindb.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.process(req, resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("vao day: " + action);
        if (action == null) {
            String name = req.getParameter("name");
            List<Product> products = ProductService.getInstance().getProductsUsingJPA(name);
            req.setAttribute("products", products);
            req.getRequestDispatcher("product_list.jsp").forward(req, resp);
        } else if (action.equals("delete")) {

            // TODO delete product by id
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                req.setAttribute("errMsg", "Product id is required");
                req.getRequestDispatcher("product_list.jsp").forward(req, resp);
            } else {
                Long id = Long.parseLong(idStr);
                boolean result = ProductService.getInstance().delete(id);
                if (result) {
                    resp.sendRedirect("/ProductServlet");
                } else {
                    req.setAttribute("errMsg", "Delete product with id " + id + " failure");
                    req.getRequestDispatcher("product_list.jsp").forward(req, resp);
                }
            }
        } else if (action.equals("create")) {
            req.getRequestDispatcher("productadd.jsp").forward(req, resp);
        } else if (action.equals("insert")) {
            // TODO insert product to database
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("productName");
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            Long price = Long.parseLong(req.getParameter("price"));

            String result = ProductService.getInstance().createProduct(id, name, quantity, price);
            if (result.equals("OK")) {
                resp.sendRedirect("/ProductServlet");
            } else {
                req.setAttribute("errMsg", result);
                req.getRequestDispatcher("productadd.jsp").forward(req, resp);
            }
        }
    }
}
