package com.app.respuestas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("app-estadistica")
public interface EstadisticaFeignClient {

	@PutMapping("/estadisticas/{idProyecto}/{formulario}/")
	public Boolean obtenerEstadisticaResultado(@PathVariable("idProyecto") Integer idProyecto,
			@PathVariable("formulario") Integer formulario, @RequestParam("numeroPregunta") Integer numeroPregunta);

}
