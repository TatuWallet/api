package br.com.tatuwallet.mapper;

import br.com.tatuwallet.Enum.TipoCarteira;
import br.com.tatuwallet.dto.request.CriarCarteiraRequest;
import br.com.tatuwallet.dto.response.CarteiraDto;
import br.com.tatuwallet.entity.Carteira;
import br.com.tatuwallet.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CarteiraMapper {

    public CarteiraDto toDto(Carteira c) {
        return new CarteiraDto(c.getId(), c.getNome(), c.getEndereco(), c.getRede() ,c.getSaldoFiat(), c.getUsuario().getId(), c.getDataCriacao(), c.getTipo(), c.isVerified());
    }

    public Carteira toEntity(CriarCarteiraRequest req, User usuario, String endereco, String rede) {
        Carteira c = new Carteira();
        c.setNome(req.nome());
        c.setUsuario(usuario);
        c.setEndereco(endereco);
        c.setRede(rede);
        c.setTipo(TipoCarteira.SELF_CUSTODY);
        return c;
    }
}
