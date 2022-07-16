package com.app.respuestas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.respuestas.models.Respuestas;

public interface RespuestasRepository extends MongoRepository<Respuestas, String> {

	@RestResource(path = "buscar-lista-respuestas")
	public List<Respuestas> findByIdProyectoAndFormulario(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario);

	@RestResource(path = "buscar-lista-respuestas-pregunta")
	public List<Respuestas> findByIdProyectoAndFormularioAndNumeroPregunta(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario, @Param("numeroPregunta") Integer numeroPregunta);

	@RestResource(path = "buscar-lista-respuesta-pregunta-especifica")
	public List<Respuestas> findByIdProyectoAndFormularioAndUsername(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario, @Param("username") String username);

	@RestResource(path = "buscar-respuesta-pregunta-especifica")
	public Respuestas findByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(@Param("idProyecto") Integer idProyecto,
			@Param("numeroPregunta") Integer numeroPregunta, @Param("formulario") Integer formulario,
			@Param("username") String username);

	@RestResource(path = "existe-lista-respuestas-proyecto")
	public Boolean existsByIdProyectoAndFormulario(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario);

	@RestResource(path = "existe-lista-respuestas-pregunta-proyecto")
	public Boolean existsByIdProyectoAndFormularioAndNumeroPregunta(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario, @Param("numeroPregunta") Integer numeroPregunta);

	@RestResource(path = "existe-lista-respuestas-usuario")
	public Boolean existsByIdProyectoAndFormularioAndUsername(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario, @Param("username") String username);

	@RestResource(path = "existe-respuesta-usuario")
	public Boolean existsByIdProyectoAndNumeroPreguntaAndFormularioAndUsername(@Param("idProyecto") Integer idProyecto,
			@Param("numeroPregunta") Integer numeroPregunta, @Param("formulario") Integer formulario,
			@Param("username") String username);

	@RestResource(path = "eliminar-respuesta-pregunta")
	public void deleteByIdProyectoAndNumeroPreguntaAndFormulario(@Param("idProyecto") Integer idProyecto,
			@Param("numeroPregunta") Integer numeroPregunta, @Param("formulario") Integer formulario);

	@RestResource(path = "eliminar-preguntas")
	public void deleteByIdProyectoAndFormulario(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario);

	@RestResource(path = "eliminar-pregunta")
	public void deleteByIdProyectoAndFormularioAndNumeroPregunta(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario, @Param("numeroPregunta") Integer numeroPregunta);

	@RestResource(path = "eliminar-proyectos")
	public void deleteByIdProyecto(@Param("idProyecto") Integer idProyecto);

}