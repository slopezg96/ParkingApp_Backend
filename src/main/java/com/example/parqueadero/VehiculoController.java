package com.example.parqueadero;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parqueadero.model.Vehiculo;
import com.example.parqueadero.repository.VehiculoRepositorio;

@RestController
@RequestMapping("/api")
public class VehiculoController {
	@Autowired
    VehiculoRepositorio vehiculoRepositorio;

 // Traer todos los Vehiculos
    @GetMapping("/vehiculos")
    public List<Vehiculo> getTodosVehiculos() {
        return vehiculoRepositorio.findAll();
    }

 // Crear un nuevo Vehiculo
    @PostMapping("/vehiculos")
    public Vehiculo crearVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        return vehiculoRepositorio.save(vehiculo);
    }

 // Traer un solo Vehiculo
    @GetMapping("/vehiculos/{placa}")
    public ResponseEntity<Vehiculo> getVehiculoXId(@PathVariable(value = "placa") String placa) {
    	Vehiculo vehiculo = vehiculoRepositorio.findOne(placa);
        if(vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(vehiculo);
    }

 // Actualizar un Vehiculo
    @PutMapping("/vehiculos/{placa}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable(value = "placa") String placa, 
                                           @Valid @RequestBody Vehiculo vehiculoDetalle) {
    	Vehiculo vehiculo = vehiculoRepositorio.findOne(placa);
        if(vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculo.setTipoVehiculo(vehiculoDetalle.getTipoVehiculo());
        vehiculo.setCilindraje(vehiculoDetalle.getCilindraje());
        Vehiculo updatedVehiculo = vehiculoRepositorio.save(vehiculo);
        return ResponseEntity.ok(updatedVehiculo);
    }

 // Eliminar un Vehiculo
    @DeleteMapping("/vehiculos/{placa}")
    public ResponseEntity<Vehiculo> deleteVehiculo(@PathVariable(value = "id") String placa) {
    	Vehiculo vehiculo = vehiculoRepositorio.findOne(placa);
        if(vehiculo == null) {
            return ResponseEntity.notFound().build();
        }

        vehiculoRepositorio.delete(vehiculo);
        return ResponseEntity.ok().build();
    }	
}
