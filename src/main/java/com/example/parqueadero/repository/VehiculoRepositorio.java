package com.example.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parqueadero.model.Vehiculo;


public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{

}
