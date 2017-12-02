package mk.ukim.finki.wp.web.wp_lab4.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    Logger logger = LoggerFactory.getLogger(SessionAttributeListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String attrName = httpSessionBindingEvent.getName();
        Object attrValue = httpSessionBindingEvent.getValue();
        logger.info("SESSION: Attribute " + attrName + " with value " + attrValue.toString() + " added to session");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String attrName = httpSessionBindingEvent.getName();
        Object attrValue = httpSessionBindingEvent.getValue();
        logger.info("SESSION: Attribute " + attrName + " was updated. New value: " + attrValue.toString());
    }
}
