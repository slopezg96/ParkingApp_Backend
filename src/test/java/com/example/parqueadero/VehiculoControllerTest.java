package com.example.parqueadero;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.parqueadero.repository.VehiculoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoControllerTest {
	
	@Mock
	private VehiculoRepositorio vehiculoRepositorio;

	@InjectMocks
	@Autowired
	VehiculoController vehiculoController;
	
	@Test
	public void ff(){
		
	}
}
