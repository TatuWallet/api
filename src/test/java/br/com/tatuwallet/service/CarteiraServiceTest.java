package br.com.tatuwallet.service;

import br.com.tatuwallet.entity.Carteira;
import br.com.tatuwallet.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarteiraServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    void deveImpedirAtualizacaoComVersaoDesatualizada() {

        TransactionTemplate transacao = new TransactionTemplate(transactionManager);

        String identificador = UUID.randomUUID().toString();

        // Cria um usuário e uma carteira para o teste
        Carteira carteiraAntiga = transacao.execute(status -> {

            User usuario = new User();
            usuario.setName("Usuario Teste");
            usuario.setUsername("teste_" + identificador.substring(0, 12));
            usuario.setEmail("teste_" + identificador + "@exemplo.com");

            entityManager.persist(usuario);

            Carteira carteira = new Carteira();
            carteira.setNome("Carteira de Teste");
            carteira.setEndereco("0x" + identificador.replace("-", ""));
            carteira.setRede("ANVIL");
            carteira.setUsuario(usuario);

            entityManager.persist(carteira);
            entityManager.flush();

            return carteira;
        });

        assertNotNull(carteiraAntiga);

        Long carteiraId = carteiraAntiga.getId();
        UUID usuarioId = carteiraAntiga.getUsuario().getId();

        try {

            // Primeira transação atualiza a carteira
            transacao.executeWithoutResult(status -> {
                Carteira carteiraAtual = entityManager.find(Carteira.class, carteiraId);
                carteiraAtual.setNome("Atualizada pela primeira transacao");
            });

            // Segunda transação tenta utilizar a versão antiga
            assertThrows(OptimisticLockException.class, () ->
                    transacao.executeWithoutResult(status -> {
                        Carteira carteira = entityManager.merge(carteiraAntiga);
                        carteira.setNome("Atualizada pela segunda transacao");
                        entityManager.flush();
                    })
            );

        } finally {

            // Remove os registros criados pelo teste
            transacao.executeWithoutResult(status -> {
                Carteira carteira = entityManager.find(Carteira.class, carteiraId);
                User usuario = entityManager.find(User.class, usuarioId);

                if (carteira != null) {
                    entityManager.remove(carteira);
                }

                if (usuario != null) {
                    entityManager.remove(usuario);
                }
            });
        }
    }
}