package com.app.respuestas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "app-preguntas")
public interface PreguntasFeignClient {

	@GetMapping("/preguntas/existe-pregunta/")
	public Boolean existePregunta(@RequestParam("idProyecto") Integer idProyecto,
			@RequestParam("numeroPregunta") Integer numeroPregunta, @RequestParam("formulario") Integer formulario);

}
