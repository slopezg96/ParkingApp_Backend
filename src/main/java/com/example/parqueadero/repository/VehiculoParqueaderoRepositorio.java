package com.example.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.parqueadero.model.VehiculoParqueado;

public interface VehiculoParqueaderoRepositorio extends JpaRepository<VehiculoParqueado, String>{
	
	@Modifying
	@Query("SELECT count(placa) FROM VehiculoParqueado WHERE tipoVehiculo = :tipoVehiculo")
	public int findByMoto(@Param("tipoVehiculo") String tipoVehiculo);
	
}
