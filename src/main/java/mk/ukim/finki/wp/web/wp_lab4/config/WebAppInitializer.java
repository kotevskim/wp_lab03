package mk.ukim.finki.wp.web.wp_lab4.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
This class is used to configure the DispatcherServlet
and the Spring application context
    *Any class that extends AbstractAnnotationConfigDispatcherServletInitializer
    will automatically be used to configure DispatcherServlet and the
    Spring application context in te application's servlet context.
AbstractAnnotationConfigDispatcherServletInitializer creates both a
DispatcherServlet and a ContextLoaderListener (servlet listener).
Both DispatcherServlet and ContextLoaderListener create two
application contexts.
*/
public class WebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    /*
     This method should return @Configuration classes that will be used
     to configure the application context created by ContextLoaderListener.
     Loads beans (other than web components) in the application
      */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    // def1:
    // This method should return @Configuration classes that will define beans
    // for the application context created by the DispatcherServlet.
    // def2:
    // Loads the Spring application context with beans (web components such as
    // controllers, view resolvers, and handler mappings) defined in the
    // WebConfig configuration class (using Java configuration)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    /*
    This method identifies the paths that the DispatcherServlet will be mapped to.
    In this case it's mapped to '/', indicating that it will be the application's
    default servlet. It will handle all requests coming into the application.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
