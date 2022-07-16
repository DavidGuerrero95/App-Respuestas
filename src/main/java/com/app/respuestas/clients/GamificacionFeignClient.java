package com.app.respuestas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "app-gamification")
public interface GamificacionFeignClient {

	@PutMapping("/gamificacion/proyectos/agregar-participante/{idProyecto}")
	public Boolean agregarParticipante(@PathVariable("idProyecto") Integer idProyecto,
			@RequestParam("username") String username);

	@GetMapping("/gamificacion/proyectos/ver-habilitado/{nombre}")
	public Boolean verHabilitadoProyecto(@PathVariable("nombre") String nombre);
}
