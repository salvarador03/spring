package com.sergio.curso.springboot.di.factura.springbootdifactura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import com.sergio.curso.springboot.di.factura.springbootdifactura.models.Item;
import com.sergio.curso.springboot.di.factura.springbootdifactura.models.Product;

import java.util.Arrays;
import java.util.List;


@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {

    @Primary
    @Bean
    List<Item> itemsInvoice() {
        return Arrays.asList(
            new Item(new Product("Camara Sony", 800), 2),
            new Item(new Product("Bicicleta Bianchi 26", 1200), 4)
        );
    }

    @Bean("default")
    List<Item> itemsInvoiceOffice() {
        return Arrays.asList(
            new Item(new Product("Monitor Asus 24", 700), 2),
            new Item(new Product("Notebook Razer", 2400), 4),
            new Item(new Product("Impresora HP", 800), 4),
            new Item(new Product("Escritorio oficina", 900), 4)
        );
    }
}
