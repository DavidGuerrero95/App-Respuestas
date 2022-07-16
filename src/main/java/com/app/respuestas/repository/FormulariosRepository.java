package com.app.respuestas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.respuestas.models.Formularios;

public interface FormulariosRepository extends MongoRepository<Formularios, String> {

	@RestResource(path = "buscar-formulario-usuario")
	public Formularios findByIdProyectoAndFormularioAndUsername(@Param("codigoProyecto") Integer codigoProyecto,
			@Param("formulario") Integer formulario, @Param("username") String username);

	@RestResource(path = "buscar-formularios-proyecto")
	public List<Formularios> findByIdProyectoAndFormulario(@Param("codigoProyecto") Integer codigoProyecto,
			@Param("formulario") Integer formulario);

	@RestResource(path = "existe-formulario-usuario")
	public Boolean existsByIdProyectoAndFormularioAndUsername(@Param("codigoProyecto") Integer codigoProyecto,
			@Param("formulario") Integer formulario, @Param("username") String username);

	@RestResource(path = "existe-formulario-proyecto")
	public Boolean existsByIdProyectoAndFormulario(@Param("codigoProyecto") Integer codigoProyecto,
			@Param("formulario") Integer formulario);

	@RestResource(path = "eliminar-proyecto-formularios")
	public void deleteByIdProyecto(@Param("idProyecto") Integer idProyecto);

	@RestResource(path = "eliminar-formularios")
	public void deleteByIdProyectoAndFormulario(@Param("idProyecto") Integer idProyecto,
			@Param("formulario") Integer formulario);

}
