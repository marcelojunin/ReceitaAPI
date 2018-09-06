package br.com.receita.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import br.com.receita.ReceitaApplicationTests;
import br.com.receita.domain.Usuario;
import io.restassured.http.ContentType;

@Sql(value = "/limpar-dados.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/insere-dados.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/limpar-dados.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioResourceTest extends ReceitaApplicationTests{

	@Test
	public void procurarUsuarioPorEmail() throws Exception {
		given()
			.pathParam("email", "crane@gmail.com")
		.get("/api/v1/usuarios/{email}/email")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value())
			.and()
			.body(
					"id", equalTo(1),
					"nome", equalTo("Crane"),
					"email", equalTo("crane@gmail.com")
					);
			
	}
	
	@Test
	public void retornarExcecaoAoBuscarPorEmailInexistente () throws Exception {
		given()
		.pathParam("email", "crane6@hotmail.com")
		.get("/api/v1/usuarios/{email}/email")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.and()
			.body("msg", equalTo("Usuario não encontrado"));
	}
	
	@Test
	public void salvarUsuario () throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Manuel L.");
		usuario.setEmail("manuell@gmail.com");
		usuario.setSenha("123");
		
		given()
			.request()
			.header("Accept", ContentType.ANY)
			.header("content-type", ContentType.JSON)
			.body(usuario)
		.when()
		.post("/api/v1/usuarios/")
		.then()	
				.log().headers()
			.and()
				.log().body()
			.and()
				.statusCode(HttpStatus.CREATED.value())
				.header("Location", equalTo("http://localhost:"+porta+"/api/v1/usuarios/2"));
			
	}
	
	@Test
	public void salvar_usuario_com_email_duplicado () throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Crane 2");
		usuario.setEmail("crane@gmail.com");
		usuario.setSenha("123");
		
		given()
			.request()
			.header("Accept", ContentType.ANY)
			.header("content-type", ContentType.JSON)
			.body(usuario)
		.when()
		.post("/api/v1/usuarios/")
		.then()	
				.log().headers()
			.and()
				.log().body()
			.and()
				.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.and()
				.body("msg", equalTo("O e-mail crane@gmail.com já está sendo usado."));
			
	}

}
