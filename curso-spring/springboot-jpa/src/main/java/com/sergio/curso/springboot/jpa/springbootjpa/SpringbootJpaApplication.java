package com.sergio.curso.springboot.jpa.springbootjpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sergio.curso.springboot.jpa.springbootjpa.dto.PersonaDto;
import com.sergio.curso.springboot.jpa.springbootjpa.entities.Persona;
import com.sergio.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;



@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository personRepository;

	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		update();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println("========== consulta por objecto persona y lenguaje de programacion ==========");
		List<Object[]> personRegs = personRepository.findAllMixPerson();

		personRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println("==================== consulta que puebla y devuelve objeto entity de una instancia personalizada ====================");
		List<Persona> personas = personRepository.findAllObjectPersonPersonalized();
		personas.forEach(System.out::println);

		System.out.println("================ consulta que puebla y devuelve objeto dto de una clase personalizada ================");
		List<PersonaDto> personasDto = personRepository.findAllPersonDto();
		personasDto.forEach(System.out::println);
	}	

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("========== consultas por rangos (id) ==========");
		List<Persona> personas = personRepository.findByIdBetweenOrderByNameAsc(1L,5L);
		// List<Persona> personas = personRepository.findByIdBetween(1L,5L);
		personas.forEach(System.out::println);

		System.out.println("========== consultas por rangos (name) ==========");
		personas = personRepository.findByNameBetweenOrderByNameDescLastNameAsc("J", "Q");
		// personas = personRepository.findByNameBetween("J", "Q");
		personas.forEach(System.out::println);

		System.out.println("========== Order by sin between ==========");
		// personas = personRepository.getAllOrdered();
		personas = personRepository.findAllByOrderByNameAscLastNameDesc();
		personas.forEach(System.out::println);



	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("========== Consulta por el nombre más corto y su largo ==========");
		List<Object[]> registers = personRepository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name="+name+", length="+length);
		});
		System.out.println("========== Consulta para obtener el último registro de persona ==========");
		Optional<Persona> optionalPersona = personRepository.getLastRegistration();
		optionalPersona.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("========== Consulta whereIn() ==========");
		List<Persona> personas = personRepository.getPeopleByIds(Arrays.asList(1L, 2L, 4L, 8L));
		personas.forEach(System.out::println);
	}


	@Transactional
	public void queriesFunctionAggregation() {

		System.out.println("========== Consulta con el total, el valor minimo y maximo de id ==========");
		Long count = personRepository.totalPerson();
		Long min = personRepository.getMinId();
		Long max = personRepository.getMaxId();
		System.out.println("Total de registros="+count+", \nvalor minimo de id="+min+", \nvalor maximo de id="+max);
		System.out.println("========== Consulta con el nombre y su largo ==========");
		List<Object[]> regs = personRepository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name="+name+", length="+length);
		});
		System.out.println("========== Consulta con el nombre más corto y el nombre más largo ==========");
		Integer minLengthName = personRepository.getMinLengthName();
		Integer maxLengthName = personRepository.getMaxLengthName();
		System.out.println("Nombre más corto="+minLengthName+", nombre más largo="+maxLengthName);
		
		System.out.println("========== Consulta con resumen de funciones de agregación min, max, sum, avg y count ==========");
		Object[] resumeReg = (Object[]) personRepository.getResumeAggregation();
		System.out.println("min="+resumeReg[0]+
						   ", max="+resumeReg[1]+
						   ", sum"+resumeReg[2]+
						   ", avg="+resumeReg[3]+
						   ", count="+resumeReg[4]);

	}

	@Transactional
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("================ Consultas con nombres y apellidos de personas ================");
		List<String> names = personRepository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("================ Consultas con nombres y apellidos de personas en mayuscula ================");
		names = personRepository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("================ Consultas con nombres y apellidos de personas en minuscula ================");
		names = personRepository.findAllFullNameConcatLower();
		names.forEach(System.out::println);

		System.out.println("================ Consulta personalizada persona con upper y lower case ================");
		List<Object[]> regs = personRepository.findAllPersonDataListCase();
		regs.forEach(reg -> 
					 System.out.println("id="+reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2]+ ", lenguaje de programacion=" + reg[3]));
	
	}

	@Transactional
	public void personalizedQueriesDistinct() {
		System.out.println("================ Consultas con nombres de personas ================");
		List<String> names=personRepository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("================ Consultas con nombres unicos de personas ================");
		names=personRepository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("================ Consultas con lenguajes de programación unicos ================");
		List<String> languages = personRepository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("================ Consulta con total de lenguajes de programación unicos ================");
		long total = personRepository.findAllProgrammingLanguageDistinctCount();
		System.out.println("Total de lenguajes de programación:  "+total);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {

		System.out.println("========== Consulta del nombre, nombre completo y id pasado un id ==========");
		System.out.println("Ingrese el id: ");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("========== mostrando solo el nombre ==========");
		String name = personRepository.getNameById(id);
		System.out.println(name);

		System.out.println("========== mostrando solo el id ==========");
		Long idDb = personRepository.getIdById(id);
		System.out.println(idDb);

		System.out.println("========== mostrando nombre completo con concat ==========");
		String fullName = personRepository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("========== Consulta por campos personalizados por el id ==========");
		Optional<Object> optionalReg = personRepository.obtenerPersonDataById(id);
		if(optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.get();
			System.out.println("id="+personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]+ ", lenguaje de programacion=" + personReg[3]);
		}

		System.out.println("========== Consulta por campos personalizados (lista) ==========");
		List<Object[]> regs = personRepository.obtenerPersonDataList();
		regs.forEach(reg -> 
					 System.out.println("id="+reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2]+ ", lenguaje de programacion=" + reg[3]));
	}

	@Transactional
	public void delete() {

		System.out.println("LISTADO DE PERSONAS EN LA BASE DE DATOS");
		personRepository.findAll().forEach(System.out::println);
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = scanner.nextLong();
		personRepository.deleteById(id);
		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	
	}

	@Transactional
	public void delete2() {

		System.out.println("LISTADO DE PERSONAS EN LA BASE DE DATOS");
		personRepository.findAll().forEach(System.out::println);
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = scanner.nextLong();

		Optional<Persona> optionalPersona = personRepository.findById(id);

/* 		optionalPersona.ifPresentOrElse(person -> 
										personRepository.delete(person),
										() -> System.out.println("El usuario no esta presente! No existe!")); */

		optionalPersona.ifPresentOrElse(personRepository::delete,
										() -> System.out.println("El usuario no esta presente! No existe!"));
		
		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	
	}

	@Transactional
	public void update() {
		
		System.out.println("Ingrese el id de la persona: ");
		Long id = scanner.nextLong();

		Optional<Persona> optionalPersona = personRepository.findById(id);
		
		/* optionalPersona.ifPresent(persona -> { */
			if(optionalPersona.isPresent()){
				Persona persona = optionalPersona.orElseThrow();

				System.out.println(persona);
				System.out.println("Ingrese el lenguaje de programación a cambiar: ");
				String programmingLanguage = scanner.next();
				persona.setProgrammingLanguage(programmingLanguage);
				Persona personaDb = personRepository.save(persona);
				System.out.println("Objeto actualizado\n");
				System.out.println(personaDb);
			} else {
				System.out.println("El usuario no esta presente! No existe!");
			}

		scanner.close();

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
