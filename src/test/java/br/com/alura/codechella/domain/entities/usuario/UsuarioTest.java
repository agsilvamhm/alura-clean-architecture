package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {
    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("12345678999","Adalberto", LocalDate.parse("1990-08-08"),"email@gmail.com"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Usuario("123.456.78999","Adalberto", LocalDate.parse("1990-08-08"),"email@gmail.com"));
    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario(){
        FabricaDeUsuario fabrica = new FabricaDeUsuario();
        Usuario usuario = fabrica.comNomeCpfNascimento("Adalberto", "123.456.789-00", LocalDate.parse("1974-01-16"));
        Assertions.assertEquals("Adalberto", usuario.getNome());

        usuario = fabrica.incluirEndereco("58401-462", 926, "Rua Deputado Norberto Leal");
        Assertions.assertEquals(926 ,usuario.getEndereco().getNumero());
    }

    @Test
    public void naoDeveCadastrarUsuarioComMenosDe18anos() {
        LocalDate dataNascimento = LocalDate.now().minusYears(17);  // Um usuário com 17 anos
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("123.456.789-00", "Fulano", dataNascimento, "fulano@example.com");
        });
        Assertions.assertEquals("Idade não pode ser inferior a 18 anos!", exception.getMessage());
    }


}
