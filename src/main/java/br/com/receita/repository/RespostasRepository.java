package br.com.receita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.receita.domain.Resposta;

/**
 * @Author Marcelo Nascimento
 * @Date 27 de out de 2018
 * @Project receita
 * @Package br.com.receita.repository
 * @Desc Repositorio responsavel pelas aceos com db de respostas.
 */
public interface RespostasRepository extends JpaRepository<Resposta, Integer>{

}
