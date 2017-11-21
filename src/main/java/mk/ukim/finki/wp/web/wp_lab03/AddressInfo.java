package mk.ukim.finki.wp.web.wp_lab03;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddressInfo.do")
public class AddressInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String pizzaSize = session.getAttribute("pizzaSize").toString();
        String name = request.getParameter("fname");
        session.setAttribute("name", name);
        String address = request.getParameter("address");
        session.setAttribute("address", address);
        String browserDetails = request.getHeader("User-Agent");
        PrintWriter writer = response.getWriter();
        writer.println(name + " " + address + " " + pizzaSize);
        writer.println(browserDetails);
        writer.flush();
    }


}
