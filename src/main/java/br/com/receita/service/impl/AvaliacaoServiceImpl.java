package br.com.receita.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.receita.domain.Avaliacao;
import br.com.receita.domain.Receita;
import br.com.receita.repository.AvaliacaoRepository;
import br.com.receita.repository.ReceitaRepository;
import br.com.receita.service.AvaliacaoService;
import br.com.receita.service.UsuarioService;

/**
 * @Author Marcelo Nascimento
 * @Date 27 de out de 2018
 * @Project receita
 * @Package br.com.receita.service.impl
 * @Desc 
 */
@Service
public class AvaliacaoServiceImpl implements AvaliacaoService{
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	/* (non-Javadoc)
	 * @see br.com.receita.service.AvaliacaoService#listarTodos()
	 * @return
	 * @Project receita
	 * @Author Marcelo Nascimento
	 * @Date 23:16:02
	 */
	@Override
	public List<Avaliacao> listarTodos() {
		return avaliacaoRepository.findAll();
	}
	/* (non-Javadoc)
	 * @see br.com.receita.service.AvaliacaoService#salvar(br.com.receita.domain.Avaliacao)
	 * @param avaliacao
	 * @return
	 * @Project receita
	 * @Author Marcelo Nascimento
	 * @Date 23:45:10
	 */
	@Override
	public Avaliacao salvar(Avaliacao avaliacao, Integer receita_id) throws Exception {
		Receita receita = receitaRepository.findOne(receita_id);
		avaliacao.setReceita(receita);
		avaliacao.setUsuario(usuarioService.pesquisaUsuarioLogado());
		return avaliacaoRepository.save(avaliacao);
	}
	/* (non-Javadoc)
	 * @see br.com.receita.service.AvaliacaoService#deletar(java.lang.Integer)
	 * @param id
	 * @Project receita
	 * @Author Marcelo Nascimento
	 * @Date 16:23:04
	 */
	@Override
	public void deletar(Integer id) {
		avaliacaoRepository.delete(id);
	}

}
