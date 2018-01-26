package com.example.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.parqueadero.model.TipoVehiculo;

@Repository
public interface TipoVehiculoRepositorio extends JpaRepository<TipoVehiculo, Long>{
	
	@Query("SELECT t FROM tipo_vehiculo t WHERE t.nombre = ?1")
    TipoVehiculo findByNombre(String nombre);
}
