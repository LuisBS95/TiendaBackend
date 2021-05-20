package com.tienda.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tienda.dtos.ProductosCat;
import com.tienda.entities.ProductoEntity;

public interface ProductoService {
	
	public Page<ProductoEntity> encontrarProductos(Pageable pageable);
	public List<ProductosCat> encontrarProductosCat(Long id);
	public List<ProductosCat> encontrarProductosCatSinLimites(Long id);
	public ProductosCat encontrarProductoPorId(Long Id);
	

}
