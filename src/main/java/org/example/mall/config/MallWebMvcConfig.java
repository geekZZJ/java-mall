package org.example.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MallWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 后续可在这里配置静态资源 / 上传目录映射
        // registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/admin/");
        // registry.addResourceHandler("/images/**")
        //         .addResourceLocations("file:" + Constant.FILE_UPLOAD_DIR);
    }
}
