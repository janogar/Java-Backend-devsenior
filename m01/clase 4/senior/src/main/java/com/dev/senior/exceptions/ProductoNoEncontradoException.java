package com.dev.senior.exceptions;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
