package com.devsenior.servicelayer.service;

import java.util.List;

import com.devsenior.servicelayer.model.Producto;

public interface IProductoService {

    Producto crear(Producto producto);
    List<Producto> listar();
    List<Producto> productosCaros();
    List<Producto> buscarPorNombre(String texto);
    double promedioDePrecios();
    void eliminar(Long id);

}
