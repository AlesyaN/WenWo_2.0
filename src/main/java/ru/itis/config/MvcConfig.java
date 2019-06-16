package ru.itis.config;
;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static String UPLOADED_FOLDER;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/users/**",
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/images/**")
                .addResourceLocations("file:///" + UPLOADED_FOLDER + "/users/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/fonts/",
                        "classpath:/static/images/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);
    }

    @Value("${my.files-url}")
    public void setUploadedFolder(String uploadedFolder) {
        UPLOADED_FOLDER = uploadedFolder;
    }
}
