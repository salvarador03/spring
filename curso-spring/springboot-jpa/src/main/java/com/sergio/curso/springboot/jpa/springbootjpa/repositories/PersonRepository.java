package com.sergio.curso.springboot.jpa.springbootjpa.repositories;

import com.sergio.curso.springboot.jpa.springbootjpa.dto.PersonaDto;
import com.sergio.curso.springboot.jpa.springbootjpa.entities.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Persona, Long> {

    // @Query("SELECT p FROM Persona p WHERE p.id not in ?1") // Todo lo distinto a la lista pasada
    @Query("SELECT p FROM Persona p WHERE p.id in ?1")
    public List<Persona> getPeopleByIds(List<Long> ids);

    @Query("SELECT p.name, LENGTH(p.name) FROM Persona p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p2.name)) FROM Persona p2)")
    public List<Object[]> getShorterName();    

    @Query("SELECT p from Persona p WHERE p.id=(SELECT MAX(p.id) FROM Persona p)")
    public Optional<Persona> getLastRegistration();

    @Query("SELECT MIN(p.id) AS minId, MAX(p.id) AS maxId, SUM(p.id) AS sumId, AVG(LENGTH(p.name)) AS avgNameLength, COUNT(p.id) AS countId FROM Persona p")
    public Object getResumeAggregation();    

    @Query("select min(length(p.name)) from Persona p")
    Integer getMinLengthName();

    @Query("select max(length(p.name)) from Persona p")
    Integer getMaxLengthName();

    @Query("select p.name, length(p.name) from Persona p")
    List<Object[]> getPersonNameLength();

    @Query("select count(p) from Persona p")
    Long totalPerson();

    @Query("select min(p.id) from Persona p")
    Long getMinId();

    @Query("select max(p.id) from Persona p")
    Long getMaxId();

    List<Persona> findAllByOrderByNameAscLastNameDesc();
    
    @Query("select p from Persona p order by p.name, p.lastName desc") // esta por defecto en ascendente
    List<Persona> getAllOrdered();

    List<Persona> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

    List<Persona> findByNameBetweenOrderByNameDescLastNameAsc(String name1, String name2);

    @Query("select p from Persona p where p.id between ?1 and ?2 order by p.name desc")
    List<Persona> findAllBetweenId(Integer id1, Integer id2);

    @Query("select p from Persona p where p.name between ?1 and ?2 order by p.name asc, p.lastName desc")
    List<Persona> findAllBetweenName(String c1, String c2);

    @Query("SELECT p.id, upper(p.name), lower(p.lastName), upper(p.programmingLanguage) from Persona p")
    List<Object[]> findAllPersonDataListCase();

    @Query("select upper(p.name || ' ' || p.lastName) from Persona p")
    List<String> findAllFullNameConcatUpper();

    // @Query("select lower(p.name || ' || p.lastName) from Persona p")
    @Query("SELECT lower(concat(p.name, ' ', p.lastName)) FROM Persona p")
    List<String> findAllFullNameConcatLower();

    // @Query("select concat(p.name, ' ',p.lastName) from Persona p")
    @Query("select p.name || ' ' || p.lastName from Persona p")
    List<String> findAllFullNameConcat();

    @Query("select count(distinct(p.programmingLanguage)) from Persona p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("select distinct(p.programmingLanguage) from Persona p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select p.name from Persona p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Persona p")
    List<String> findAllNamesDistinct();

    @Query("select new com.sergio.curso.springboot.jpa.springbootjpa.dto.PersonaDto(p.name, p.lastName) from Persona p")
    List<PersonaDto> findAllPersonDto(); 

    @Query("select new Persona(p.name, p.lastName) from Persona p")
    List<Persona> findAllObjectPersonPersonalized(); 

    @Query("select p.name from Persona p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Persona p where p.id=?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ',p.lastName) as fullname from Persona p where p.id=?1")
    String getFullNameById(Long id);

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
    
    @Query("SELECT p, p.programmingLanguage from Persona p")
    List<Object[]> findAllMixPerson();

    @Query("SELECT p.id, p.name, p.lastName, p.programmingLanguage from Persona p")
    List<Object[]> obtenerPersonDataList();

    @Query("SELECT p.id, p.name, p.lastName, p.programmingLanguage from Persona p where p.id=?1")
    Optional<Object> obtenerPersonDataById(Long id);

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
