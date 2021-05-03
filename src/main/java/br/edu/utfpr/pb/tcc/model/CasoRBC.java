package br.edu.utfpr.pb.tcc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CasoRBC {
	
	private List<Caso> casos;
    private List<CasoSimilaridade> casosSimilares = new ArrayList<>();
	
	public void processar(List<Caso> casos, Caso novoCaso) {
		this.casos = casos;
        List<CasoSimilaridade> casosSimilares = new ArrayList<>();
        
        this.casos.forEach(
        		caso -> 
        		casosSimilares.add(
        				similaridade(novoCaso, caso)
        		)
        );
        
        this.casosSimilares.addAll(
	    	casosSimilares
	        	.stream()
	        	.sorted(
	        			(caso1, caso2) -> 
	        				caso1.getSimilaridade() >= caso2.getSimilaridade() ? -1 : 1
	        	)
	        	.collect(Collectors.toList())
        );
	}
	
	private Double similaridadeNumerica(Double a1, Double a2, Double max, Double min) {
		if (max-min != 0D) {
	        return (1.0 - (Math.abs(a2 - a1) / (max - min)));			
		} else {
			return 0D;
		}
    }
	
	private Double somaSimilaridade;
	private CasoSimilaridade similaridade(Caso novoCaso, Caso caso) {
        somaSimilaridade = 0D;
        CasoAtributoPeso peso = new CasoAtributoPeso();
        
        novoCaso.getAtributos().forEach(atributo -> {
        	Atributo atributoCaso = caso.getAtributo(atributo.getDescricao());
        	
        	if (atributoCaso.getPeso() > 0) {
	        	peso.addAtributo(
	        			new Atributo(
	        					atributoCaso.getDescricao(),
	        					atributoCaso.getPeso(),
	        					10D,
	        					0D
	        			)
	        		);
	
		        somaSimilaridade +=
		        		similaridadeNumerica(
		        				atributoCaso.getPeso(),
	        					atributoCaso.getPeso(),
		        				10D,
		        				0D
		        		);
        	}
    	});

        if (peso.getTotal() > 0) {
            return new CasoSimilaridade(caso, somaSimilaridade / peso.getTotal());	
        } else {
        	return new CasoSimilaridade(caso, 0D);
        }
    }

	public List<CasoSimilaridade> getCasosSimilares() {
		return casosSimilares;
	}
}
