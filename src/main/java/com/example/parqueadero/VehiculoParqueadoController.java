package com.example.parqueadero;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parqueadero.model.TipoVehiculo;
import com.example.parqueadero.model.VehiculoParqueado;
import com.example.parqueadero.repository.TipoVehiculoRepositorio;
import com.example.parqueadero.repository.VehiculoParqueaderoRepositorio;
import com.example.parqueadero.util.DateHelper;


@RestController
@RequestMapping("/api")
public class VehiculoParqueadoController {
	private static final String MOTO = "Moto";
	private static final String CARRO = "Carro";
	private static final double VALOR_ADICIONAL_X_CILINDRAJE = 2000;
	@Autowired
    VehiculoParqueaderoRepositorio vehiculoParqueaderoRepositorio;
	
	@Autowired
    TipoVehiculoRepositorio tipoVehiculoRepositorio;

 // Traer todos los VehiculosParqueados
    @GetMapping("/vehiculosParqueados")
    public List<VehiculoParqueado> getTodosVehiculosParqueados() {
    	return vehiculoParqueaderoRepositorio.findAll();
    }

 // Crear un nuevo VehiculoParqueado
    @PostMapping("/vehiculosParqueados")
    public VehiculoParqueado crearVehiculoParqueado(@Valid @RequestBody VehiculoParqueado vehiculoParqueado) {
        return vehiculoParqueaderoRepositorio.save(vehiculoParqueado);
    }

 // Traer un solo TipoVehiculo
    @GetMapping("/vehiculosParqueados/{placa}")
    public ResponseEntity<VehiculoParqueado> getVehiculoParqueadoXPlaca(@PathVariable(value = "placa") String placa) {
    	VehiculoParqueado vehiculoParqueado = vehiculoParqueaderoRepositorio.findOne(placa);
        if(vehiculoParqueado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(vehiculoParqueado);
    }
    
    @PostMapping("/vehiculosParqueados/cobrar")
    public VehiculoParqueado cobrar(@Valid @RequestBody VehiculoParqueado vehiculoParqueado) {
    	 DateHelper dateHelper = new DateHelper(vehiculoParqueado.getFechaIngreso(), vehiculoParqueado.getFechaSalida());
         long dias = dateHelper.diferenciaDias();
         long horas = dateHelper.diferenciaHoras();
         double valorTotal = 0;
         
         TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findOne((long) vehiculoParqueado.getIdTipoVehiculo());
         switch (tipoVehiculo.getNombre()) {
             case MOTO:
                 if (dias > 0) {
                     valorTotal = (dias * tipoVehiculo.getValorDia()) + ((horas % 24) * tipoVehiculo.getValorHora());
                 } else if (horas >= 9) {
                     valorTotal = tipoVehiculo.getValorDia();
                 } else {
                     valorTotal = horas * tipoVehiculo.getValorHora();
                 }
                 vehiculoParqueado.setValor(vehiculoParqueado.getCilindraje() > 500 ? valorTotal + VALOR_ADICIONAL_X_CILINDRAJE : valorTotal);
             break;
             case CARRO:
                 if (dias > 0) {
                     valorTotal = (dias * tipoVehiculo.getValorDia()) + ((horas % 24) * tipoVehiculo.getValorHora());
                 } else if (horas >= 9) {
                     valorTotal = tipoVehiculo.getValorDia();
                 } else {
                     valorTotal = horas * tipoVehiculo.getValorHora();
                 }
                 vehiculoParqueado.setValor(valorTotal);
             break;
             default:
            	 vehiculoParqueado.setValor(0);
            	 break;
         }
         vehiculoParqueaderoRepositorio.delete(vehiculoParqueado.getPlaca());
         return vehiculoParqueado;
    }
}
