package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Venta;
import com.coderhouse.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	//GET ALL VENTAS
	public List<Venta> getAllVentas(){
		return ventaRepository.findAll();
	}
	
	//GET VENTA BY ID
	public Venta getVentaById(Long id) {
		return ventaRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada"));
	}
	
	//CREAR VENTA
	@Transactional
	public Venta newVenta(Venta ventaInfo) {
		return ventaRepository.save(ventaInfo);
	}
	
	//ACTUALIZAR VENTA
	@Transactional
	public Venta updateVentaById(Long id, Venta ventaInfo) {
		Venta venta = ventaRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Venta no encontrada"));
		if(ventaInfo.getFecha() != null) {
			venta.setFecha(ventaInfo.getFecha());
		}
		
		if(ventaInfo.getTotal() != 0) {
			venta.setTotal(ventaInfo.getTotal());
		}
		
		if(ventaInfo.getCliente() != null) {
			venta.setCliente(ventaInfo.getCliente());
		}
		
		venta.setProductos(ventaInfo.getProductos());
		return ventaRepository.save(venta);
	}
	
	//ELIMINAR VENTA
	public void deleteVentaById(Long id) {
		if(!ventaRepository.existsById(id)) {
			throw new IllegalArgumentException("Venta no encontrada");
		}
		ventaRepository.deleteById(id);
	}
}
