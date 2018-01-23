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
import com.example.parqueadero.model.VehiculoParqueado;
import com.example.parqueadero.repository.VehiculoParqueaderoRepositorio;


@RestController
@RequestMapping("/api")
public class VehiculoParqueadoController {
	@Autowired
    VehiculoParqueaderoRepositorio vehiculoParqueaderoRepositorio;

 // Traer todos los VehiculosParqueados
    @GetMapping("/vehiculosParqueados")
    public List<VehiculoParqueado> getTodosVehiculosParqueados() {
        return vehiculoParqueaderoRepositorio.findAll();
    }

 // Crear un nuevo VehiculoParqueado
    @PostMapping("/vehiculosParqueados")
    public VehiculoParqueado crearVehiculoParqueado(@Valid @RequestBody VehiculoParqueado vehiculoParqueado) {
        return vehiculoParqueaderoRepositorio.save(vehiculoParqueado);
    }

 // Traer un solo TipoVehiculo
    @GetMapping("/vehiculosParqueados/{placa}")
    public ResponseEntity<VehiculoParqueado> getVehiculoParqueadoXPlaca(@PathVariable(value = "placa") String placa) {
    	VehiculoParqueado vehiculoParqueado = vehiculoParqueaderoRepositorio.findOne(placa);
        if(vehiculoParqueado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(vehiculoParqueado);
    }
    
 // Traer un solo TipoVehiculo
    @GetMapping("/vehiculosParqueados/{tipoVehiculo}")
    public ResponseEntity<VehiculoParqueado> getVehiculoParqueadoXTipoVehiculo(@PathVariable(value = "tipoVehiculo") String tipo) {
    	VehiculoParqueado vehiculoParqueado = vehiculoParqueaderoRepositorio.findOne(tipo);
        if(vehiculoParqueado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(vehiculoParqueado);
    }

 // Actualizar un TipoVehiculo
    @PutMapping("/vehiculosParqueados/{placa}")
    public ResponseEntity<VehiculoParqueado> updateVehiculoParqueado(@PathVariable(value = "placa") String placa, 
                                           @Valid @RequestBody VehiculoParqueado vehiculoParqueadoDetalle) {
    	VehiculoParqueado vehiculoParqueado = vehiculoParqueaderoRepositorio.findOne(placa);
        if(vehiculoParqueado == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculoParqueado.setFechaIngreso(vehiculoParqueadoDetalle.getFechaIngreso());
        vehiculoParqueado.setFechaSalida(vehiculoParqueadoDetalle.getFechaSalida());
        vehiculoParqueado.setValor(vehiculoParqueadoDetalle.getValor());
        VehiculoParqueado updatedVehiculoParqueado = vehiculoParqueaderoRepositorio.save(vehiculoParqueado);
        return ResponseEntity.ok(updatedVehiculoParqueado);
    }

 // Eliminar un TipoVehiculo
    @DeleteMapping("/vehiculosParqueados/{placa}")
    public ResponseEntity<VehiculoParqueado> deleteVehiculoParqueado(@PathVariable(value = "placa") String placa) {
    	VehiculoParqueado vehiculoParqueado = vehiculoParqueaderoRepositorio.findOne(placa);
        if(vehiculoParqueado == null) {
            return ResponseEntity.notFound().build();
        }

        vehiculoParqueaderoRepositorio.delete(vehiculoParqueado);
        return ResponseEntity.ok().build();
    }	
}
