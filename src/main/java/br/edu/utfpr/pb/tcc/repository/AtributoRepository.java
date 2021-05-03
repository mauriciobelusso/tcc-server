package br.edu.utfpr.pb.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.tcc.model.Atributo;

public interface AtributoRepository extends JpaRepository<Atributo, Long> {

	List<Atributo> findByDescricao(String descricao);
}
