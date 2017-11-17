package mk.ukim.finki.wp.web.wp_lab03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddressInfo.do")
public class AddressInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pizzaSize = request.getSession().getAttribute("pizzaSize").toString();
        String name = request.getParameter("fname");
        String address = request.getParameter("address");
        String browserDetails = request.getHeader("User-Agent");
        PrintWriter writer = response.getWriter();
        writer.println(name + " " + address + " " + pizzaSize);
        writer.println(browserDetails);
        writer.flush();
    }


}
