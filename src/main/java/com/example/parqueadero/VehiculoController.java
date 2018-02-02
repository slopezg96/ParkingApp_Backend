package com.example.parqueadero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parqueadero.repository.VehiculoRepositorio;

@RestController
@RequestMapping("/api")
public class VehiculoController {
	@Autowired
    VehiculoRepositorio vehiculoRepositorio;

}
