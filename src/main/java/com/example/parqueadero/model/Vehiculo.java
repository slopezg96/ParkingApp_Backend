package com.example.parqueadero.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "vehiculo")
@EntityListeners(AuditingEntityListener.class)
public class Vehiculo implements Serializable{
	@Id
    private String placa;
	
	@NotBlank
	private int cilindraje;
	
	@NotBlank
	private String tipoVehiculo;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
}
