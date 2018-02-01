package com.example.parqueadero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.parqueadero.model.TipoVehiculo;
import com.example.parqueadero.repository.TipoVehiculoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipoVehiculoControllerTest {

	
	@Mock
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	@InjectMocks
	@Autowired	
	TipoVehiculoController tipoVehiculoController;
	
	@Before
	public void setUp(){
		
	}
	
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
		Mockito.when(tipoVehiculoRepositorio.findOne(1L)).thenReturn(moto);
		TipoVehiculo tipoVehiculo = tipoVehiculoController.getTipoVehiculoXId(1L).getBody();
		Assert.assertEquals(tipoVehiculo.getNombre(), moto.getNombre());
	}
	
	@Test
	public void metodoGuardarTipoVehiculoRetornaUnTipoVehiculo(){
		TipoVehiculo tipoVehiculo =  getTipoVehiculo();
		Mockito.when(tipoVehiculoRepositorio.save(tipoVehiculo)).thenReturn(tipoVehiculo);
		TipoVehiculo respuesta = tipoVehiculoController.crearTipoVehiculo(tipoVehiculo);
		Assert.assertSame(tipoVehiculo.getNombre(), respuesta.getNombre());
	}
	
	@Test
	public void metodoGetTodosTiposVehiculosRetornaUnaLista(){
		List<TipoVehiculo> tiposVehiculos = new ArrayList<>();
		tiposVehiculos.add(getTipoVehiculo());
		Mockito.when(tipoVehiculoRepositorio.findAll()).thenReturn(tiposVehiculos);
		List<TipoVehiculo> respuesta = tipoVehiculoController.getTodosTiposVehiculo();
		Assert.assertSame(tiposVehiculos, respuesta);
		
	}
	
	

}
