package com.sergio.curso.springboot.jpa.springbootjpa;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sergio.curso.springboot.jpa.springbootjpa.entities.Persona;
import com.sergio.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;



@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		create();
		
	}
	@Transactional(readOnly = true)
	public void findOne() {
/* 		Persona persona = null;
		Optional<Persona> optionalPersona = personRepository.findOneName("Sergio");
		if(!optionalPersona.isEmpty()){
			persona = optionalPersona.get();
		}
		System.out.println(persona); */

		//personRepository.findById(1L).ifPresent(System.out::println);
		personRepository.findByNameContaining("gio").ifPresent(System.out::println);
	}

	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre:");
		String name = scanner.next();
		System.out.println("Apellido:");
		String lastname = scanner.next();
		System.out.println("Lenguaje de programación:");
		String programmingLanguage = scanner.next();
		scanner.close();
 
		Persona persona = new Persona(null, name, lastname, programmingLanguage);

		Persona personaNew = personRepository.save(persona);
		System.out.println("Nueva persona guardada: "+personaNew);

		personRepository.findById(personaNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void listData() {
		String texto;
		texto = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "LISTADO DE PERSONAS:";
		System.out.println(texto);
		List<Persona> personas = (List<Persona>) personRepository.findAll();
		personas.stream().forEach(persona -> System.out.println(persona));
		
		texto = "-----------------------------------" + "\n" + "Listado de personas ordenadas por su lenguaje de programación: ";
		System.out.println(texto);
		List<Persona> personas2 = (List<Persona>) personRepository.findByProgrammingLanguage("Python");
		personas2.stream().forEach(persona -> System.out.println(persona));
		

		texto = "-----------------------------------" + "\n" + "Listado de personas ordenadas por su lenguaje de programación y su nombre: ";
		System.out.println(texto);
		List<Persona> personas3 = (List<Persona>) personRepository.findByProgrammingLanguageAndName("Java", "Sergio");
		personas3.stream().forEach(persona -> System.out.println(persona));

		texto = "-----------------------------------" + "\n" + "Listado de datos de personas: ";
		System.out.println(texto);
		List<Object[]> personValues = personRepository.obtenerPersonData();
		personValues.stream().forEach(persona -> 
									System.out.println(persona[0] + " es un experto en " + persona[1]));

		texto = "-----------------------------------" + "\n" + "Listado de datos de personas con 1 argumento pasado: ";
		System.out.println(texto);
		List<Object[]> personValues2 = personRepository.obtenerPersonData("Java");
		personValues2.forEach(persona -> 
			System.out.println(persona[0] + " es un experto en " + persona[1]));

		texto = "-----------------------------------" + "\n" + "Listado de datos de personas con 2 argumentos pasados: ";
		System.out.println(texto);
		List<Object[]> personValues3 = personRepository.obtenerPersonData("Java", "Sergio");
        personValues3.forEach(persona -> 
            System.out.println(persona[0] + " es un experto en " + persona[1]));
	}

}
