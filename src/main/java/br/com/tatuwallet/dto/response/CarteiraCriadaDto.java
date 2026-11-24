package br.com.tatuwallet.dto.response;

public record CarteiraCriadaDto(CarteiraDto carteira, String mnemonic, String aviso) {

    @Override
    public String toString() {
        return "CarteiraCriadaDto[carteira=" + carteira + ", mnemonic=****]";
    }
}