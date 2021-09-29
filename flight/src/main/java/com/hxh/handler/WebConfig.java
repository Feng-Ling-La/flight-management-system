package com.hxh.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将我们写的 ”拦截器“ 注册进项目中。
 *  @Configuration 表示这个类是一个配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * addPathPatterns("/admin/**")：表示拦截 "/admin/ 下的所有请求
         *
         * excludePathPatterns("/admin")
         * excludePathPatterns("/admin/login")：代码意思是：拦截的时候，排除在两个请求。
         *      即：不拦截 /admin 请求，该请求是显示 管理员登录界面，如果拦截了，则管理员无法登录
         *         不拦截 /admin/login，如果拦截该请求，则管理员无法登录成功
         */
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login")
                //detailFlight页面使用了admin路径下的css;解除对/admin/下css文件的拦截
                .excludePathPatterns("/**/*.css");
    }
}
