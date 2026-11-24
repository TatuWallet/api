package br.com.tatuwallet.controller;

import br.com.tatuwallet.dto.response.CotacaoDto;
import br.com.tatuwallet.service.CotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cotacoes")
public class CotacaoController {
    private final CotacaoService cotacaoService;

    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @GetMapping("/{simbolo}")
    public ResponseEntity<CotacaoDto> cotacao(@PathVariable String simbolo) {
        return ResponseEntity.ok(cotacaoService.obterCotacao(simbolo));
    }
}
