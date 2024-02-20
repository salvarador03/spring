package com.sergio.curso.springboot.jpa.springbootjpa.repositories;

import com.sergio.curso.springboot.jpa.springbootjpa.entities.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Persona, Long> {

    @Query("select p from Persona p where p.id=?1")
    Optional<Persona> findOne(Long id);

    @Query("select p from Persona p where p.name=?1")
    Optional<Persona> findOneName(String name);

    @Query("select p from Persona p where p.name like %?1%")
    Optional<Persona> findOneLikeName(String name);

    
    Optional<Persona> findByNameContaining(String name);

    public List<Persona> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Persona p WHERE p.programmingLanguage = :programmingLanguage AND p.name = :name")
    List<Persona> findByProgrammingLanguageAndName(@Param("programmingLanguage") String programmingLanguage, @Param("name") String name);
    
    @Query("SELECT p.name, p.programmingLanguage from Persona p")
    List<Object[]> obtenerPersonData();

    /*
        Metodo para obtener datos de una persona pasados su lenguaje de programación
    */ 
    @Query("SELECT p.name, p.programmingLanguage FROM Persona p WHERE p.programmingLanguage = :programmingLanguage")
    List<Object[]> obtenerPersonData(@Param("programmingLanguage") String programmingLanguage);

    /*
        Metodo para obtener datos de una persona pasados su nombre y lenguaje de programación
    */ 
    @Query("SELECT p.name, p.programmingLanguage FROM Persona p WHERE p.programmingLanguage = :programmingLanguage AND p.name = :name")
    List<Object[]> obtenerPersonData(@Param("programmingLanguage") String programmingLanguage, @Param("name") String name);


}
