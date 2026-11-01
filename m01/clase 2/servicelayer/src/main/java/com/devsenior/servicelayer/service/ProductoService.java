package com.devsenior.servicelayer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.servicelayer.model.Producto;
import com.devsenior.servicelayer.repository.iProductoRepository;

@Service
public class ProductoService implements IProductoService {

    final iProductoRepository repository;

    public ProductoService(iProductoRepository repository) {
        this.repository = repository;
    }
    
    public Producto crear(Producto producto) {

        if (producto.getPrecio() <= 0) {
            throw new RuntimeException("El precio del producto debe ser mayor a 0");
        }

        boolean existe = repository.findAll().stream()
            .anyMatch(p -> p.getNombre().equals(producto.getNombre()));

        if (existe) {
            throw new RuntimeException("El producto ya existe");
        }

        return repository.save(producto);
    }

    public List<Producto> listar() {
        return repository.findAll();
    }
    
    public List<Producto> productosCaros() {
        return repository.findAll().stream()
            .filter(p -> p.getPrecio() > 100)
            .toList();
    }

    public List<Producto> buscarPorNombre(String texto) {
        return repository.findAll().stream()
            .filter(p -> p.getNombre().toLowerCase().contains(texto.toLowerCase()))
            .toList();
    }

    // promedio de precios de los productos

    public double promedioDePrecios() {
        return repository.findAll().stream() // stream de productos
            .mapToDouble(p -> p.getPrecio()) // stream de precios
            .average() // promedio de precios
            .orElse(0); // si no hay productos, devuelve 0
    }

    // eliminar un producto (validar que exista)
    public void eliminar(Long id) {
        if (!repository.findById(id).isPresent()) { // si no existe, lanza una excepción
            throw new RuntimeException("El producto no existe"); // mensaje de error
        }
        repository.deleteById(id); // si existe, lo elimina
    }


}
