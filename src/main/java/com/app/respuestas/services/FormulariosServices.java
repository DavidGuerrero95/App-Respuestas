package com.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.respuestas.clients.GamificacionFeignClient;
import com.app.respuestas.clients.ProyectosFeignClient;
import com.app.respuestas.models.Formularios;
import com.app.respuestas.repository.FormulariosRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FormulariosServices implements IFormulariosServices {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	FormulariosRepository fRepository;

	@Autowired
	ProyectosFeignClient pClient;

	@Autowired
	GamificacionFeignClient gClient;

	@Override
	public void crearFormulario(Formularios formulario) {
		if (formulario.getFormulario() == null)
			formulario.setFormulario(1);
		if (formulario.getRespondido() == null)
			formulario.setRespondido(false);
		if (fRepository.existsByIdProyectoAndFormularioAndUsername(formulario.getIdProyecto(),
				formulario.getFormulario(), formulario.getUsername())) {
			Formularios f = fRepository.findByIdProyectoAndFormularioAndUsername(formulario.getIdProyecto(),
					formulario.getFormulario(), formulario.getUsername());
			f.setRespondido(formulario.getRespondido());
			fRepository.save(f);
		} else {
			fRepository.save(formulario);
		}
	}

	@Override
	public void finalizarFormulario(Integer idProyecto, Formularios formulario) {
		if (formulario.getFormulario() == null)
			formulario.setFormulario(1);
		if (formulario.getRespondido() == null)
			formulario.setRespondido(true);
		if (fRepository.existsByIdProyectoAndFormularioAndUsername(idProyecto, formulario.getFormulario(),
				formulario.getUsername())) {
			Formularios f = fRepository.findByIdProyectoAndFormularioAndUsername(formulario.getIdProyecto(),
					formulario.getFormulario(), formulario.getUsername());
			f.setRespondido(formulario.getRespondido());

			Boolean flag1 = cbFactory.create("usuario").run(() -> pClient.verEstadoGamificacion(idProyecto),
					e -> errorConexionProyectos(e));
			Boolean flag2 = cbFactory.create("usuario").run(
					() -> gClient.verHabilitadoProyecto(formulario.getUsername()), e -> errorConexionGamificacion(e));

			if (flag1 && flag2) {
				if (cbFactory.create("usuario").run(
						() -> gClient.agregarParticipante(idProyecto, formulario.getUsername()),
						e -> errorConexion(e))) {
					log.info("Se agrego correctamente el usuario a la gamificacion");
				}
			}

			fRepository.save(f);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Proyecto no existe");
		}
	}

//  ****************************	FUNCIONES TOLERANCIA A FALLOS	***********************************  //

	private Boolean errorConexion(Throwable e) {
		log.info("Error creacion parmetros: " + e.getMessage());
		return false;
	}

	private Boolean errorConexionProyectos(Throwable e) {
		log.info("Error creacion parmetros: " + e.getMessage());
		return false;
	}

	private Boolean errorConexionGamificacion(Throwable e) {
		log.info("Error creacion parmetros: " + e.getMessage());
		return false;
	}

}
