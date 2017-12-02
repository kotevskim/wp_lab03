package mk.ukim.finki.wp.web.wp_lab4.web.listeners;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener{

    private Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.info("SESSION: Session with ID " + session.getId() + " Created!");
        Map<Long, Order> orders = new HashMap<>();
        session.setAttribute("orders", orders);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String sessionId = httpSessionEvent.getSession().getId();
        logger.info("SESSION: Session with id" + sessionId + " Destroyed!");
    }
}
