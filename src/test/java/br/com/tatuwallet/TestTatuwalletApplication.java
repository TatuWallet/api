package br.com.tatuwallet;

import org.springframework.boot.SpringApplication;

public class TestTatuwalletApplication {

	public static void main(String[] args) {
		SpringApplication.from(TatuwalletApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
