package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	//GET ALL PRODUCTOS
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();
	}
	
	//GET PRODUCTO BY ID
	public Producto getProductoById(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Producto no encontrado"));
	}
	
	//CREAR PRODUCTO
	@Transactional
	public Producto newProducto(Producto productoInfo) {
		return productoRepository.save(productoInfo);
	}
	
	//ACTUALIZAR PRODUCTO
	@Transactional
	public Producto updateProductoById(Long id, Producto productoInfo) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado"));
		if(productoInfo.getNombre() != null && !productoInfo.getNombre().isEmpty()) {
			producto.setNombre(productoInfo.getNombre());
		}
		
		if(productoInfo.getPrecio() != 0) {
			producto.setPrecio(productoInfo.getPrecio());
		}
		return productoRepository.save(producto);
	}
	
	//DELETE PRODUCTO
	public void deleteProductoById(Long id) {
		if(!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		productoRepository.deleteById(id);
	}
}
