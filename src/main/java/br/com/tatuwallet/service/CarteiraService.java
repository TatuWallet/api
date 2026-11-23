package br.com.tatuwallet.service;

import br.com.tatuwallet.blockchain.HdWalletGenerator;
import br.com.tatuwallet.blockchain.model.NovaCarteira;
import br.com.tatuwallet.dto.request.CriarCarteiraRequest;
import br.com.tatuwallet.dto.response.CarteiraCriadaDto;
import br.com.tatuwallet.dto.response.CarteiraDto;
import br.com.tatuwallet.entity.Carteira;
import br.com.tatuwallet.entity.User;
import br.com.tatuwallet.exception.CarteiraNaoEncontradaException;
import br.com.tatuwallet.exception.UsuarioNaoEncontradoException;
import br.com.tatuwallet.mapper.CarteiraMapper;
import br.com.tatuwallet.repository.CarteiraRepository;
import br.com.tatuwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    private static final String AVISO = "Guarde estas 12 palavras agora. Elas não ficam salvas "
            + "no servidor e não podem ser recuperadas. Quem as tiver controla a carteira.";

    private final CarteiraRepository repository;
    private final UserRepository userRepository;
    private final HdWalletGenerator gerador;
    private final CarteiraMapper mapper;
    private final String rede;

    public CarteiraService(CarteiraRepository repository, UserRepository userRepository,
                           HdWalletGenerator gerador, CarteiraMapper mapper,
                           @Value("${blockchain.rede}") String rede) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.gerador = gerador;
        this.mapper = mapper;
        this.rede = rede;
    }

    @Transactional
    public CarteiraCriadaDto criar(CriarCarteiraRequest req) {
        User usuario = userRepository.findById(req.idUsuario())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(req.idUsuario()));

        NovaCarteira nova = gerador.gerar();
        Carteira salva = repository.save(mapper.toEntity(req, usuario, nova.endereco(), rede));

        return new CarteiraCriadaDto(mapper.toDto(salva), nova.mnemonic(), AVISO);
    }

}