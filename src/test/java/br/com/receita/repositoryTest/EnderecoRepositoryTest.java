/**
 * 
 */
package br.com.receita.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.receita.domain.Endereco;
import br.com.receita.repository.EnderecoRepository;
import br.com.receita.repository.filtro.EnderecoFiltro;

/**
 * @author Marcelo
 * 2 de set de 2018 
 */
@Sql(value = "/insere-dados.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/limpar-dados.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
public class EnderecoRepositoryTest {

	private static final String BAIRRO = "Braz de pina";
	private static final String CEP = "21012409";
	private static final String COMPLEMENTO = "Rua Trinta e Seis - Braz de Pina";
	private static final String LOCALIDADE = "Quadra C9";
	private static final String UF = "RJ";

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	private EnderecoFiltro filtro;
	private Endereco endereco;
	
	@Test
	public void filtrar_por_todos_os_dados_de_endereco() throws Exception{
		//cenario
		filtro = new EnderecoFiltro();
		filtro.setBairro(BAIRRO);
		filtro.setCep(CEP);
		filtro.setLocalidade(LOCALIDADE);
		filtro.setComplemento(COMPLEMENTO);
		filtro.setUf(UF);
		
		//acao
		List<Endereco> listaDeEndereco = enderecoRepository.filtrar(filtro);
		
		//verificacao
		assertThat(listaDeEndereco.get(0).getUf()).isEqualTo(UF);
		assertThat(listaDeEndereco.get(0).getCep()).isEqualTo(CEP);
		assertThat(listaDeEndereco.get(0).getBairro()).isEqualTo(BAIRRO);
		assertThat(listaDeEndereco.get(0).getComplemento()).isEqualTo(COMPLEMENTO);
		assertThat(listaDeEndereco.get(0).getLocalidade()).isEqualTo(LOCALIDADE);
		
	}
	
	@Test
	public void filtrar_enderecos_do_rj () throws Exception {
		//cenario
		filtro = new EnderecoFiltro();
		filtro.setUf(UF);
		
		//acao
		List<Endereco> listaDeEndereco = enderecoRepository.filtrar(filtro);
		
		//verificacao
		assertThat(listaDeEndereco.get(0).getUf()).isEqualTo(UF);
	}
	
	@Test
	public void verificar_se_endereco_esta_sendo_persistido () throws Exception {
		//cenario
		endereco = new Endereco();
		endereco.setBairro("Rua feliz");
		endereco.setCep("21444120");
		endereco.setLocalidade("Quadra 49");
		endereco.setUf("SP");
		endereco.setComplemento("Rua 2");
		//acao
		
		Endereco end = enderecoRepository.save(endereco);
		
		//verificacao
		assertThat(end.getBairro()).isEqualTo(endereco.getBairro());
		assertThat(end.getCep()).isEqualTo(endereco.getCep());
		assertThat(end.getLocalidade()).isEqualTo(endereco.getLocalidade());
		assertThat(end.getUf()).isEqualTo(endereco.getUf());
		assertThat(end.getComplemento()).isEqualTo(endereco.getComplemento());
	}
}
