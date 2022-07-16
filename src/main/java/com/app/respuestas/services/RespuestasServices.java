package com.app.respuestas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.respuestas.clients.PreguntasFeignClient;
import com.app.respuestas.models.Respuestas;
import com.app.respuestas.repository.RespuestasRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RespuestasServices implements IRespuestasServices {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	RespuestasRepository rRepository;

	@Autowired
	PreguntasFeignClient pClient;

	@Override
	public void creaRespuestasTotal(List<Respuestas> r) {
		r.forEach(respuesta -> {
			if (cbFactory.create("respuestas")
					.run(() -> pClient.existePregunta(respuesta.getIdProyecto(), respuesta.getNumeroPregunta(),
							respuesta.getFormulario()),
							e -> encontrarPregunta(respuesta.getIdProyecto(), respuesta.getNumeroPregunta(),
									respuesta.getFormulario(), e))) {
				if (respuesta.getFormulario() == null)
					respuesta.setFormulario(1);
				if (rRepository.existsByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(respuesta.getIdProyecto(),
						respuesta.getNumeroPregunta(), respuesta.getFormulario(), respuesta.getUsername())) {
					Respuestas res = rRepository.findByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(
							respuesta.getIdProyecto(), respuesta.getNumeroPregunta(), respuesta.getFormulario(),
							respuesta.getUsername());
					res.setRespuestas(respuesta.getRespuestas());
					rRepository.save(res);
				} else {
					rRepository.save(respuesta);
				}
			}
		});
	}

	@Override
	public void crearRespuesta(Respuestas respuesta) {
		if (cbFactory.create("respuestas")
				.run(() -> pClient.existePregunta(respuesta.getIdProyecto(), respuesta.getNumeroPregunta(),
						respuesta.getFormulario()),
						e -> encontrarPregunta(respuesta.getIdProyecto(), respuesta.getNumeroPregunta(),
								respuesta.getFormulario(), e))) {
			if (respuesta.getFormulario() == null)
				respuesta.setFormulario(1);
			if (rRepository.existsByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(respuesta.getIdProyecto(),
					respuesta.getNumeroPregunta(), respuesta.getFormulario(), respuesta.getUsername())) {
				Respuestas r = rRepository.findByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(
						respuesta.getIdProyecto(), respuesta.getNumeroPregunta(), respuesta.getFormulario(),
						respuesta.getUsername());
				r.setRespuestas(respuesta.getRespuestas());
				rRepository.save(r);
			} else {
				rRepository.save(respuesta);
			}
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pregunta no existe");
	}

	private Boolean encontrarPregunta(Integer codigoProyecto, Integer numeroPregunta, Integer formulario, Throwable e) {
		log.error(e.getMessage());
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Proyecto no existe");
	}

	@Override
	public Boolean existeProyectoFormulario(Integer idProyecto, Integer formulario) {
		return rRepository.existsByIdProyectoAndFormulario(idProyecto, formulario);
	}

	@Override
	public boolean existIdFormularioUsername(Integer idProyecto, Integer formulario, String username) {
		return rRepository.existsByIdProyectoAndFormularioAndUsername(idProyecto, formulario, username);
	}

	@Override
	public List<Respuestas> findIdFormularioUsername(Integer idProyecto, Integer formulario, String username) {
		return rRepository.findByIdProyectoAndFormularioAndUsername(idProyecto, formulario, username);
	}

	@Override
	public boolean existIdNumeroFormularioUsername(Integer idProyecto, Integer numeroPregunta, Integer formulario,
			String username) {
		return rRepository.existsByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(idProyecto, numeroPregunta,
				formulario, username);
	}

	@Override
	public Respuestas findIdNumeroFormularioUsername(Integer idProyecto, Integer numeroPregunta, Integer formulario,
			String username) {
		return rRepository.findByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(idProyecto, numeroPregunta,
				formulario, username);
	}

	@Override
	public boolean existIdFormularioNumero(Integer idProyecto, Integer formulario, Integer numeroPregunta) {
		return rRepository.existsByIdProyectoAndFormularioAndNumeroPregunta(idProyecto, formulario, numeroPregunta);
	}

	@Override
	public List<Respuestas> findIdFormularioNumero(Integer idProyecto, Integer formulario, Integer numeroPregunta) {
		return rRepository.findByIdProyectoAndFormularioAndNumeroPregunta(idProyecto, formulario, numeroPregunta);
	}

}
