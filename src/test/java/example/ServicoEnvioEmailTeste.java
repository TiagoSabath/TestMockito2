package example;


import org.example.Email;
import org.example.Formato;
import org.example.PlataformaDeEnvio;
import org.example.ServicoEnvioEmail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTeste {

    @Mock
    private PlataformaDeEnvio plataforma;
    @InjectMocks
    private ServicoEnvioEmail servico;
    @Captor
    private ArgumentCaptor<Email> captor;

    @Test
    void validarDadosEnviadosParaAPlataforma(){

        String enderecoEmail = "tiago@teste.com";
        String mensagem = "Olá mundo teste mensagem";
        boolean ehFormatoHtml = false;

        servico.enviaEmail(enderecoEmail, mensagem , ehFormatoHtml);

        Mockito.verify(plataforma).enviaEmail(captor.capture());

        Email emailCapturado = captor.getValue();

        assertEquals(enderecoEmail, emailCapturado.getEnderecoEmail());
        assertEquals(mensagem,emailCapturado.getMensagem());
        assertEquals(Formato.TEXTO, emailCapturado.getFormato());
    }

}
