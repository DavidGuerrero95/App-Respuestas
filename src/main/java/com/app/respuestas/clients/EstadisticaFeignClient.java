package com.app.respuestas.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.respuestas.models.Respuestas;

@FeignClient("app-estadistica")
public interface EstadisticaFeignClient {

	@PutMapping("/estadisticas/{idProyecto}/{formulario}/")
	public Boolean obtenerEstadistica(@PathVariable("idProyecto") Integer idProyecto,
			@PathVariable("formulario") Integer formulario);

	@PostMapping("/estadisticas/respuestas/crear/una/")
	public Boolean crearRespuestas(@RequestBody Respuestas r);

	@PostMapping("/estadisticas/respuestas/crear/lista/")
	public Boolean crearRespuestasLista(@RequestBody List<Respuestas> r);

	@DeleteMapping("/estadisticas/respuestas/eliminar/proyecto/formulario/{idProyecto}")
	public Boolean eliminarRespuestasProyectoFormulario(@PathVariable("idProyecto") Integer idProyecto,
			@RequestParam("formulario") Integer formulario);

	@DeleteMapping("/estadisticas/respuestas/eliminar/proyecto/pregunta/{idProyecto}")
	public Boolean eliminarRespuestasProyectoFormularioPregunta(@PathVariable("idProyecto") Integer idProyecto,
			@RequestParam("formulario") Integer formulario, @PathVariable("numeroPregunta") Integer numeroPregunta);

	@DeleteMapping("/estadisticas/respuestas/eliminar/proyecto/todo/{idProyecto}")
	public Boolean eliminarRespuestasProyecto(@PathVariable("idProyecto") Integer idProyecto);

}
