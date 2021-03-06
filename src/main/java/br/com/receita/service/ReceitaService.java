package br.com.receita.service;

import java.util.Collection;
import java.util.List;

import br.com.receita.domain.ItemReceita;
import br.com.receita.domain.Receita;
import br.com.receita.dto.ItemReceitaDTO;
import br.com.receita.dto.TagDTO;
import br.com.receita.repository.filtro.ReceitaFiltro;

public interface ReceitaService {

	List<Receita> listar();

	/**
	 * @param receita
	 * @throws Exception 
	 * @Author Marcelo Nascimento
	 * @Date 25 de set de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Método para salvar receita
	 */
	Receita salvar(Receita receita) throws Exception;

	/**
	 * @param id
	 * @return
	 * @Author Marcelo Nascimento
	 * @Date 26 de set de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc 
	 */
	Receita pesquisar(Integer id);
	
	/**
	 * @param itemReceitaDTO
	 * @return
	 * @Author Marcelo Nascimento
	 * @Date 6 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Transforma objeto dto de item receita em objeto real.
	 */
	ItemReceita fromDTO(ItemReceitaDTO itemReceitaDTO);
	
	/**
	 * @param idIngreceita
	 * @param idReceita
	 * @Author Marcelo Nascimento
	 * @Date 6 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Monta a receita a partir dos ingredientes enviados
	 */
	void montarReceita(ItemReceita itemReceita);

	/**
	 * @param itemReceita
	 * @Author Marcelo Nascimento
	 * @Date 6 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Remove da receita o ingrediente enviado.
	 */
	void desmontarReceita(ItemReceita itemReceita);

	/**
	 * @param filtro
	 * @return
	 * @Author Marcelo Nascimento
	 * @Date 7 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Realiza a filtragem dos dados de receita com base nos atributos informados.
	 */
	List<Receita> filtro(ReceitaFiltro filtro);

	/**
	 * @return
	 * @throws Exception 
	 * @Author Marcelo Nascimento
	 * @Date 12 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Lista todas as receitas completas do usuário logado.
	 */
	List<Receita> minhasReceitasCompletas() throws Exception;

	/**
	 * @return
	 * @Author Marcelo Nascimento
	 * @Date 12 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Lista todas as receitas incompletas do usuário logado.
	 */
	List<Receita> minhasReceitasIncompletas() throws Exception;

	/**
	 * @param receita
	 * @param id
	 * @Author Marcelo Nascimento
	 * @Date 13 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Atualiza os dados da receita.
	 */
	void atualizar(Receita receita, Integer id);

	/**
	 * @param tags
	 * @return
	 * @Author Marcelo Nascimento
	 * @Date 20 de out de 2018
	 * @Project receita
	 * @Package br.com.receita.service
	 * @Desc Metodo responsavel por filtrar as receitas pelas tags.
	 */
	List<Receita> pesquisarReceitas(Collection<TagDTO> tags);

}
