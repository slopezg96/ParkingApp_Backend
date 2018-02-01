package com.example.parqueadero;

import java.awt.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.parqueadero.model.TipoVehiculo;
import com.example.parqueadero.repository.TipoVehiculoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoApplicationTests {

	private TipoVehiculoController tipoVehiculoController;
	private TipoVehiculoRepositorio tipoVehiculoRepositorio;
	
	@Before
	public void setUp(){
		tipoVehiculoController = new TipoVehiculoController();
	}
	
	@Test
	public void metodoGetTiposVehiculosRetornaUnaLista() {
		Assert.assertEquals(tipoVehiculoController.getTodosTiposVehiculo(), new List());
	}

}
