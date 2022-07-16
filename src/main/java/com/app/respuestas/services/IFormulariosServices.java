package com.app.respuestas.services;

import com.app.respuestas.models.Formularios;

public interface IFormulariosServices {

	public void crearFormulario(Formularios formulario);

	public void finalizarFormulario(Integer idProyecto, Formularios formulario);
	
}
