package com.clean.code.springboot.repository;

import com.clean.code.springboot.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByName(String name);

    @Query(value = "select * from employee e  where e.name = :name", nativeQuery = true)
    List<Employee> findByNameQuery(@Param("name") String name);

    List<Employee> findAllByNameLike(String name);
    List<Employee> findAllByNameStartingWith(String name);
    List<Employee> findAllByNameEndingWith(String name);

    @Query("select e from Employee e where e.name like concat('%', :name, '%') ")
    List<Employee> findAllByLike(@Param("name") String name);

    @Query(value = "select * from Employee e where e.name like %:name%", nativeQuery = true)
    List<Employee> findAllNativeLike(@Param("name") String name);

}
