package com.example.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parqueadero.model.VehiculoParqueado;

public interface VehiculoParqueaderoRepositorio extends JpaRepository<VehiculoParqueado, String>{

	
}
