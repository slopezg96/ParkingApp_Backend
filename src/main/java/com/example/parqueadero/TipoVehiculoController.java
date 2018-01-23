package com.example.parqueadero;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.parqueadero.model.TipoVehiculo;
import com.example.parqueadero.repository.TipoVehiculoRepositorio;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TipoVehiculoController {
	 @Autowired
	    TipoVehiculoRepositorio tipoVehiculoRepositorio;

	 // Traer todos los TipoVehiculo
	    @GetMapping("/tiposVehiculo")
	    public List<TipoVehiculo> getTodosTiposVehiculo() {
	        return tipoVehiculoRepositorio.findAll();
	    }

	 // Crear un nuevo TipoVehiculo
	    @PostMapping("/tiposVehiculo")
	    public TipoVehiculo crearTipoVehiculo(@Valid @RequestBody TipoVehiculo tipoVehiculo) {
	        return tipoVehiculoRepositorio.save(tipoVehiculo);
	    }

	 // Traer un solo TipoVehiculo
	    @GetMapping("/tiposVehiculo/{id}")
	    public ResponseEntity<TipoVehiculo> getTipoVehiculoXId(@PathVariable(value = "id") Long tipoVehiculoId) {
	        TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findOne(tipoVehiculoId);
	        if(tipoVehiculo == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(tipoVehiculo);
	    }

	 // Actualizar un TipoVehiculo
	    @PutMapping("/tiposVehiculo/{id}")
	    public ResponseEntity<TipoVehiculo> updateTipoVehiculo(@PathVariable(value = "id") Long tipoVehiculoId, 
	                                           @Valid @RequestBody TipoVehiculo tipoVehiculoDetalle) {
	        TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findOne(tipoVehiculoId);
	        if(tipoVehiculo == null) {
	            return ResponseEntity.notFound().build();
	        }
	        tipoVehiculo.setNombre(tipoVehiculoDetalle.getNombre());
	        tipoVehiculo.setValorDia(tipoVehiculoDetalle.getValorDia());
	        tipoVehiculo.setValorHora(tipoVehiculoDetalle.getValorHora());
	        TipoVehiculo updatedTipoVehiculo = tipoVehiculoRepositorio.save(tipoVehiculo);
	        return ResponseEntity.ok(updatedTipoVehiculo);
	    }

	 // Eliminar un TipoVehiculo
	    @DeleteMapping("/tiposVehiculo/{id}")
	    public ResponseEntity<TipoVehiculo> deleteTipoVehiculo(@PathVariable(value = "id") Long tipoVehiculoId) {
	        TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findOne(tipoVehiculoId);
	        if(tipoVehiculo == null) {
	            return ResponseEntity.notFound().build();
	        }

	        tipoVehiculoRepositorio.delete(tipoVehiculo);
	        return ResponseEntity.ok().build();
	    }	
}
