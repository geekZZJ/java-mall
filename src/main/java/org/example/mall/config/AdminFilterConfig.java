package org.example.mall.config;

import org.example.mall.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminFilterConfig {

    @Bean
    public AdminFilter adminFilter() {
        return new AdminFilter();
    }

    @Bean(name = "adminFilterConf")
    public FilterRegistrationBean<AdminFilter> adminFilterConfig() {
        FilterRegistrationBean<AdminFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(adminFilter());
        bean.addUrlPatterns("/admin/category/*");
        bean.addUrlPatterns("/admin/product/*");
        bean.addUrlPatterns("/admin/order/*");
        bean.setName("adminFilterConfig");
        return bean;
    }
}
