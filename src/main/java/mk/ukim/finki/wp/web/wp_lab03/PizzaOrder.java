package mk.ukim.finki.wp.web.wp_lab03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pizzaSize = request.getParameter("size");
        request.getSession().setAttribute("pizzaSize", pizzaSize);
        PrintWriter writer = response.getWriter();
        writer.write("<html>" +
                "<h2>Delivery Info</h2>" +
                "<form action='/AddressInfo.do' method='post'>" +
                "Name: <input type='text' name='fname'><br>" +
                "Address: <input type='text' name='address'><br>" +
                "<button type='submit'>Submit</button>" +
                "</form>" +
                "</html>");
    }

}
