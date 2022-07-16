package com.app.respuestas.services;

import java.util.List;

import com.app.respuestas.models.Respuestas;

public interface IRespuestasServices {

	public void creaRespuestasTotal(List<Respuestas> r);

	public void crearRespuesta(Respuestas respuesta);

	public Boolean existeProyectoFormulario(Integer idProyecto, Integer formulario);

	public boolean existIdFormularioUsername(Integer idProyecto, Integer formulario, String username);

	public List<Respuestas> findIdFormularioUsername(Integer idProyecto, Integer formulario, String username);

	public boolean existIdNumeroFormularioUsername(Integer idProyecto, Integer numeroPregunta, Integer formulario,
			String username);

	public Respuestas findIdNumeroFormularioUsername(Integer idProyecto, Integer numeroPregunta, Integer formulario,
			String username);

	public boolean existIdFormularioNumero(Integer idProyecto, Integer formulario, Integer numeroPregunta);

	public List<Respuestas> findIdFormularioNumero(Integer idProyecto, Integer formulario, Integer numeroPregunta);

}
