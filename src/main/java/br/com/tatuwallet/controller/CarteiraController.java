package br.com.tatuwallet.controller;

import br.com.tatuwallet.dto.request.CriarCarteiraRequest;
import br.com.tatuwallet.dto.response.CarteiraCriadaDto;
import br.com.tatuwallet.dto.response.CarteiraDto;
import br.com.tatuwallet.dto.response.SaldoDto;
import br.com.tatuwallet.entity.Saldo;
import br.com.tatuwallet.service.CarteiraService;
import br.com.tatuwallet.service.SaldoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CarteiraService service;
    // private final SaldoService saldoService;

    public CarteiraController(CarteiraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarteiraCriadaDto> criar(@Valid @RequestBody CriarCarteiraRequest req) {
        CarteiraCriadaDto criada = service.criar(req);
        return ResponseEntity
                .created(URI.create("/carteiras/" + criada.carteira().id()))
                .body(criada);
    }


    @GetMapping("/{id}")
    public CarteiraDto buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    /*
    @GetMapping("/{id}/saldo")
    public SaldoDto getSaldo(@PathVariable Long id) {
        return SaldoService.buscar_saldo(id);
    }
    */
}