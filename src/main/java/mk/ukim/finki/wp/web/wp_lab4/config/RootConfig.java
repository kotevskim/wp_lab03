package mk.ukim.finki.wp.web.wp_lab4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/* This class defines the root configuration (for the ContextLoaderListener) */

@Configuration
@ComponentScan(
        basePackages = {"mk.ukim.finki.wp.web.wp_lab4"},
        excludeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        value = EnableWebMvc.class)
        }
)
public class RootConfig {
}
