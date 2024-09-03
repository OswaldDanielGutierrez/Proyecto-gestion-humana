package com.empleado.persistence;

import com.empleado.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdCard(String idCard);

    boolean existsByIdCard(String idCard);

    @Query("SELECT e FROM Employee e WHERE e.idCard = :idCard OR e.email = :email OR e.accountNumber = :accountNumber")
    Optional<Employee> findByIdCardOrEmailOrAccountNumber(
            @Param("idCard") String idCard,
            @Param("email") String email,
            @Param("accountNumber") String accountNumber
    );


}
