package vn.aptech.logindb.servlet;

import vn.aptech.logindb.entity.User;
import vn.aptech.logindb.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("") || password.equals("")) {
            request.setAttribute("errMsg", "Username and password is required !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            User user = userService.login(username, password);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("id", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("name", user.getName());
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                System.out.println("vao day");
                request.setAttribute("errMsg", "Wrong username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
