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
}
