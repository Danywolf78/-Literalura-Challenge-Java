package com.allura.literalura;

import com.allura.literalura.model.Datos;
import com.allura.literalura.model.DatosLibros;
import com.allura.literalura.principal.Principal;
import com.allura.literalura.repository.AutorRepository;
import com.allura.literalura.repository.LibrosRepository;
import com.allura.literalura.service.ConsumoAPI;
import com.allura.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LibrosRepository librosrepository;
	@Autowired
	private AutorRepository autorrepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(librosrepository,autorrepository);
		principal.muestraElMenu();


	}
}
