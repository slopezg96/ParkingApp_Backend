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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.parqueadero.model.TipoVehiculo;
import com.example.parqueadero.model.VehiculoParqueado;
import com.example.parqueadero.repository.TipoVehiculoRepositorio;
import com.example.parqueadero.repository.VehiculoParqueaderoRepositorio;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoParqueadoControllerTest {

	@Mock
	private VehiculoParqueaderoRepositorio vehiculoParqueaderoRepositorio;
	
	@Mock
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	@InjectMocks
	@Autowired	
	VehiculoParqueadoController vehiculoParqueadoController;
	
	public TipoVehiculo getTipoVehiculoMoto(){
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1L);
		tipoVehiculo.setNombre("Moto");
		tipoVehiculo.setValorDia(4000);
		tipoVehiculo.setValorHora(500);
		return tipoVehiculo;
	}
	
	public TipoVehiculo getTipoVehiculoCarro(){
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(2L);
		tipoVehiculo.setNombre("Carro");
		tipoVehiculo.setValorDia(8000);
		tipoVehiculo.setValorHora(1000);
		return tipoVehiculo;
	}
	
	public VehiculoParqueado getMoto(){
		VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
		vehiculoParqueado.setPlaca("STF94D");
		vehiculoParqueado.setIdTipoVehiculo(1L);
		vehiculoParqueado.setTipoVehiculo("Moto");
		vehiculoParqueado.setCilindraje(150);
		vehiculoParqueado.setFechaIngreso("2018-02-05 10:00:00");
		vehiculoParqueado.setFechaSalida("2018-02-05 18:00:00");
		vehiculoParqueado.setValorDia(4000);
		vehiculoParqueado.setValorHora(500);
		return vehiculoParqueado;
	}
	
	public VehiculoParqueado getCarro(){
		VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
		vehiculoParqueado.setPlaca("KMN586");
		vehiculoParqueado.setIdTipoVehiculo(2L);
		vehiculoParqueado.setTipoVehiculo("Carro");
		vehiculoParqueado.setCilindraje(150);
		vehiculoParqueado.setFechaIngreso("2018-02-05 10:00:00");
		vehiculoParqueado.setFechaSalida("2018-02-05 13:00:00");
		vehiculoParqueado.setValorDia(8000);
		vehiculoParqueado.setValorHora(1000);
		return vehiculoParqueado;
	}
	
	@Test
	public void metodoCrearNuevoVehiculoParqueadoRetornaVehiculoParqueado(){
		VehiculoParqueado vehiculoParqueado = getMoto();
		Mockito.when(vehiculoParqueaderoRepositorio.save(vehiculoParqueado)).thenReturn(vehiculoParqueado);
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.crearVehiculoParqueado(vehiculoParqueado);
		Assert.assertSame(vehiculoParqueado, respuestaVehiculoParqueado);
	}
	
	@Test
	public void metodoGetVehiculoParqueadoXPlacaRetornaVehiculoParqueado(){
		VehiculoParqueado vehiculoParqueado = getMoto();
		Mockito.when(vehiculoParqueaderoRepositorio.findOne(vehiculoParqueado.getPlaca())).thenReturn(vehiculoParqueado);
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.getVehiculoParqueadoXPlaca(vehiculoParqueado.getPlaca()).getBody();
		Assert.assertSame(vehiculoParqueado, respuestaVehiculoParqueado);
	}
	
	@Test
	public void cuandoGetVehiculoXPlacaRetornaResponseEntityBodyNull(){
		VehiculoParqueado vehiculoParqueado = getMoto();
		Mockito.when(vehiculoParqueaderoRepositorio.findOne(vehiculoParqueado.getPlaca())).thenReturn(null);
		ResponseEntity<VehiculoParqueado> respuestaVehiculoParqueado = vehiculoParqueadoController.getVehiculoParqueadoXPlaca(vehiculoParqueado.getPlaca());
		Assert.assertNull(respuestaVehiculoParqueado.getBody());
	}
	
	@Test
	public void metodoGetTodosVehiculosParqueadosRetornaListaVehiculoParqueado(){
		List<VehiculoParqueado> vehiculosParqueados = new ArrayList<>();
		vehiculosParqueados.add(getMoto());
		Mockito.when(vehiculoParqueaderoRepositorio.findAll()).thenReturn(vehiculosParqueados);
		List<VehiculoParqueado> respuestaVehiculosParqueados = vehiculoParqueadoController.getTodosVehiculosParqueados();
		Assert.assertSame(vehiculosParqueados, respuestaVehiculosParqueados);
	}
	
	@Test
	public void metodoCobrarLlamaEliminarVehiculoParqueado(){
		VehiculoParqueado moto = getMoto();
		Mockito.when(tipoVehiculoRepositorio.findOne(moto.getIdTipoVehiculo())).thenReturn(getTipoVehiculoMoto());
		vehiculoParqueadoController.cobrar(getMoto());
		Mockito.verify(vehiculoParqueaderoRepositorio).delete(getMoto().getPlaca());
	}
	
	@Test
	public void cobrar4000AMotoPor8Horas(){
		VehiculoParqueado moto = getMoto();
		Mockito.when(tipoVehiculoRepositorio.findOne(moto.getIdTipoVehiculo())).thenReturn(getTipoVehiculoMoto());
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.cobrar(moto);
		Assert.assertEquals(4000, respuestaVehiculoParqueado.getValor(), 0);
	}
	
	@Test
	public void cobrar6000AMotoConCilindraje650Por10Horas(){
		VehiculoParqueado moto = getMoto();
		moto.setCilindraje(650);
		moto.setFechaSalida("2018-02-05 20:00:00");
		
		Mockito.when(tipoVehiculoRepositorio.findOne(moto.getIdTipoVehiculo())).thenReturn(getTipoVehiculoMoto());
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.cobrar(moto);
		Assert.assertEquals(6000, respuestaVehiculoParqueado.getValor(), 0);
	}
	
	@Test
	public void cobrar5500AMotoPoUnDiaY3Horas(){
		VehiculoParqueado moto = getMoto();
		moto.setCilindraje(150);
		moto.setFechaSalida("2018-02-06 13:00:00");
		
		Mockito.when(tipoVehiculoRepositorio.findOne(moto.getIdTipoVehiculo())).thenReturn(getTipoVehiculoMoto());
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.cobrar(moto);
		Assert.assertEquals(5500, respuestaVehiculoParqueado.getValor(), 0);
	}
	
	@Test
	public void cobrar3000ACarroPor3Horas(){
		VehiculoParqueado carro = getCarro();
		Mockito.when(tipoVehiculoRepositorio.findOne(carro.getIdTipoVehiculo())).thenReturn(getTipoVehiculoCarro());
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.cobrar(carro);
		Assert.assertEquals(3000, respuestaVehiculoParqueado.getValor(), 0);
	}
	
	@Test
	public void cobrar9000ACarroPor1DiaY1Hora(){
		VehiculoParqueado carro = getCarro();
		carro.setFechaSalida("2018-02-06 11:00:00");
		Mockito.when(tipoVehiculoRepositorio.findOne(carro.getIdTipoVehiculo())).thenReturn(getTipoVehiculoCarro());
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.cobrar(carro);
		Assert.assertEquals(9000, respuestaVehiculoParqueado.getValor(), 0);
	}
	
	@Test
	public void cobrarUnDiaDeCarroPor10Horas(){
		VehiculoParqueado carro = getCarro();
		carro.setFechaSalida("2018-02-05 20:00:00");
		
		Mockito.when(tipoVehiculoRepositorio.findOne(carro.getIdTipoVehiculo())).thenReturn(getTipoVehiculoCarro());
		VehiculoParqueado respuestaVehiculoParqueado = vehiculoParqueadoController.cobrar(carro);
		Assert.assertEquals(8000, respuestaVehiculoParqueado.getValor(), 0);
	}
}
