package com.overtimeWork.persistance;

import com.overtimeWork.entities.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Long> {


    //Busca las horas extras de un empleado por su idCard
    @Query("SELECT o FROM Overtime o WHERE FUNCTION('MONTH', o.date) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', o.date) = FUNCTION('YEAR', CURRENT_DATE) AND o.employeeIdCard = :employeeIdCard")
    List<Overtime> findAllInCurrentMonth(String employeeIdCard);

    Overtime findByEmployeeIdCard(String employeeIdCard);


}
