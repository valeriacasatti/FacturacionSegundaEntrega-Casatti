package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Venta;
import com.coderhouse.services.VentaService;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	//GET ALL VENTAS
	@GetMapping
	public ResponseEntity<List<Venta>> getAllVentas() {
		try {
			List<Venta> ventas = ventaService.getAllVentas();
			return ResponseEntity.ok(ventas); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	//GET VENTA BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Venta> getProductoById(@PathVariable Long id) {
		try {
			Venta venta = ventaService.getVentaById(id);
			return ResponseEntity.ok(venta);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//CREAR VENTA
	@PostMapping
	public ResponseEntity<Venta> newVenta(@RequestBody Venta venta){
		try {
			Venta nuevaVenta = ventaService.newVenta(venta);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	//ACTUALIZAR VENTA
	@PutMapping("/{id}")
	public ResponseEntity<Venta> updateVentaById(@PathVariable Long id, @RequestBody Venta ventaInfo){
		try {
			Venta updateVenta = ventaService.updateVentaById(id, ventaInfo);
			return ResponseEntity.ok(updateVenta);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); 
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	//ELIMINAR VENTA
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVentaById(@PathVariable Long id){
		try {
			ventaService.deleteVentaById(id);
			return ResponseEntity.noContent().build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); 
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}
}
