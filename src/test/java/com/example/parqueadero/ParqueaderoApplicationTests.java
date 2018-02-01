package com.example.parqueadero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.parqueadero.model.TipoVehiculo;
import com.example.parqueadero.repository.TipoVehiculoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoApplicationTests {

	@Mock
	@Autowired
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	@Autowired
	private TipoVehiculoController tipoVehiculoController;
	
	
	private List<TipoVehiculo> resultado;
	
	public TipoVehiculo getTipoVehiculo(){
		TipoVehiculo moto = new TipoVehiculo();
		moto.setId(1L);
		moto.setNombre("Moto");
		moto.setValorDia(2000);
		moto.setValorHora(500);
		return moto;
	}
	
	@Test
	public void verificarMetodoBusquedaDeTipoVehiculoMoto() {
		
		TipoVehiculo moto =  getTipoVehiculo();
		
		TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findOne(1L);
		Assert.assertEquals(tipoVehiculo.getNombre(), moto.getNombre());
	}
	
	@Test
	public void metodoGuardarTipoVehiculoRetornaUnTipoVehiculo(){
		TipoVehiculo tipoVehiculo =  getTipoVehiculo();
		Mockito.when(tipoVehiculoRepositorio.save(tipoVehiculo)).thenReturn(tipoVehiculo);
		TipoVehiculo respuesta = tipoVehiculoController.crearTipoVehiculo(tipoVehiculo);
		Assert.assertSame(tipoVehiculo.getNombre(), respuesta.getNombre());
	}
	

}
