package com.sergio.curso.springboot.webapp.springbootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
// Para que funcionen otro tipo de configuraciones
@PropertySources({
		@PropertySource(value = "classpath:values.properties", encoding = "UTF-8"), 
})
public class ValuesConfig {

}
