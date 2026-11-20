package com.dev.security;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping 
    public List<Product> getAllProductos(){
        return List.of(
            new Product(10L, "Teclado Inalambrico", 89.90),
            new Product(200L, "monitor", 300.3)
            
        );
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return  new Product(id, "Laptop lenovo", 450.8);
    }

    @PostMapping 
    public Product createProduct(@RequestBody Product product){
        product.setId(100L);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        return product;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return "Producto con ID" + id + " eliminado exitosamente";
    }
}
