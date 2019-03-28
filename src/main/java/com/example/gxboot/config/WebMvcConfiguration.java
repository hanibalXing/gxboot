package com.example.gxboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ClassName: WebMvcConfiguration<br/>
 * Description: java类作用描述<br/>
 * date 2019/3/28 13:34
 *
 * @author gx
 * @Version 1.0
 * @since 1.7
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	//addResourceHandler是指你想在url请求的路径

	//addResourceLocations是图片存放的真实路径

	 registry.addResourceHandler("/image/**").addResourceLocations("file:D://gx/");
	 super.addResourceHandlers(registry);
	}
}
