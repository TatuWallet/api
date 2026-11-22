package br.com.tatuwallet.mapper;

import br.com.tatuwallet.dto.response.CarteiraDto;
import br.com.tatuwallet.entity.Carteira;

public class CarteiraMapper {

    public CarteiraDto toDto(Carteira c) {
        return new CarteiraDto(c.getId(), c.getNome(), c.getEndereco(), c.getRede() ,c.getSaldoFiat(), c.getUsuario(), c.getDataCriacao(), c.getTipo(), c.isVerified());
    }

    public Carteira toEntity(CarteiraDto dto) {
        Carteira u = new Carteira();
        u.setNome(dto.nome());
        u.setEndereco(dto.endereco());
        u.setRede(dto.rede());
        u.setSaldoFiat(dto.saldoFiat());
        u.setUsuario(dto.usuario());
        u.setDataCriacao(dto.dataCriacao());
        u.setTipo(dto.tipo());
        u.setVerified(dto.verified());
        return u;
    }
}
