package com.app.respuestas.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "formularios")
@Data
@NoArgsConstructor
public class Formularios {

	@Id
	@JsonIgnore
	private String id;

	@NotNull(message = "id proyecto cannot be null")
	private Integer idProyecto;

	@NotNull(message = "Username de pregunta be null")
	@Size(max = 20)
	private String username;

	private Integer formulario;

	private Boolean respondido;
}
