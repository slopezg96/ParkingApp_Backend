package com.example.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parqueadero.model.TipoVehiculo;

@Repository
public interface TipoVehiculoRepositorio extends JpaRepository<TipoVehiculo, Long>{
	
}
