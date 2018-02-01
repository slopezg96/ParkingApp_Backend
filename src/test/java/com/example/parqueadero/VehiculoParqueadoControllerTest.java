package com.example.parqueadero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.parqueadero.model.VehiculoParqueado;
import com.example.parqueadero.repository.VehiculoParqueaderoRepositorio;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoParqueadoControllerTest {

	@Mock
	private VehiculoParqueaderoRepositorio vehiculoParqueaderoRepositorio;
	
	@InjectMocks
	@Autowired	
	VehiculoParqueadoController vehiculoParqueadoController;
	
	public VehiculoParqueado getVehiculoParqueado(){
		VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
		vehiculoParqueado.setPlaca("STF94D");
		vehiculoParqueado.setIdTipoVehiculo(1L);
		vehiculoParqueado.setTipoVehiculo("Moto");
		vehiculoParqueado.setCilindraje(150);
		vehiculoParqueado.setFechaIngreso("2018-02-05 10:00:00");
		vehiculoParqueado.setFechaSalida("2018-02-05 13:00:00");
		vehiculoParqueado.setValorDia(4000);
		vehiculoParqueado.setValorHora(500);
		return vehiculoParqueado;
	}
	
	@Test
	public void metodoCrearNuevoVehiculoParqueadoRetornaVehiculoParqueado(){
		VehiculoParqueado vehiculoParqueado = getVehiculoParqueado();
		Mockito.when(vehiculoParqueaderoRepositorio.save(vehiculoParqueado)).thenReturn(vehiculoParqueado);
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.crearVehiculoParqueado(vehiculoParqueado);
		Assert.assertSame(vehiculoParqueado, respuestaVehiculoParqueado);
	}
	
	@Test
	public void metodoGetVehiculoParqueadoXPlacaRetornaVehiculoParqueado(){
		VehiculoParqueado vehiculoParqueado = getVehiculoParqueado();
		Mockito.when(vehiculoParqueaderoRepositorio.findOne(vehiculoParqueado.getPlaca())).thenReturn(vehiculoParqueado);
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.getVehiculoParqueadoXPlaca(vehiculoParqueado.getPlaca()).getBody();
		Assert.assertSame(vehiculoParqueado, respuestaVehiculoParqueado);
	}
	
	@Test
	public void metodoGetTodosVehiculosParqueadosRetornaListaVehiculoParqueado(){
		List<VehiculoParqueado> vehiculosParqueados = new ArrayList<>();
		vehiculosParqueados.add(getVehiculoParqueado());
		Mockito.when(vehiculoParqueaderoRepositorio.findAll()).thenReturn(vehiculosParqueados);
		List<VehiculoParqueado> respuestaVehiculosParqueados = vehiculoParqueadoController.getTodosVehiculosParqueados();
		Assert.assertSame(vehiculosParqueados, respuestaVehiculosParqueados);
	}
}
