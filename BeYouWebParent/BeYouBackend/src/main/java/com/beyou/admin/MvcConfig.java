package com.beyou.admin;
import java.util.List;

import com.beyou.admin.paging.PagingAndSortingArgumetResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     //For users
    //     exposeDirectory("user-photos", registry);
    //     //For Categories
    //     exposeDirectory("./category-images", registry);
    //     //For Brands
    //     exposeDirectory("./brand-logos", registry);
    //     //for Products
    //     exposeDirectory("./product-images", registry);
    //     //for General setting site Logo
    //     exposeDirectory("./site-logo", registry);
    // }

    // private void exposeDirectory(String pathPattern,ResourceHandlerRegistry registry){
    //     Path path = Paths.get(pathPattern);
    //     String absolutePath = path.toFile().getAbsolutePath();

    //     String logicalPath = pathPattern.replace("./", "") + "/**";

    //     registry.addResourceHandler(logicalPath)
    //         .addResourceLocations("file:/"+ absolutePath + "/");
    // }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PagingAndSortingArgumetResolver());
    }
    
    
    
}
