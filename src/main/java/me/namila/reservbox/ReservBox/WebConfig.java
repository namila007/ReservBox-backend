package me.namila.reservbox.ReservBox;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter
{
	//enable cors for the server
	@Override
	public void addCorsMappings( CorsRegistry registry )
	{
		registry.addMapping( "/**" );
	}
}