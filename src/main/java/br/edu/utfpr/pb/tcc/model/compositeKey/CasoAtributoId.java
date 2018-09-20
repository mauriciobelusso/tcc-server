package br.edu.utfpr.pb.tcc.model.compositeKey;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"idCaso","idAtributo"})
public class CasoAtributoId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idCaso;
	private Long idAtributo;
}
