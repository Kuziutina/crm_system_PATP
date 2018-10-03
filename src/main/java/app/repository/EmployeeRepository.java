package app.repository;

import app.form.EmployeeForm;
import app.model.Employee;
import app.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    @Query("select * from employee join position on position.id = employee.position_id where position.name = :position")
//    Employee findByPosition(@Param("position") String position);

    List<Employee> findByPosition(Position position);

    @Modifying
    @Transactional
    @Query("UPDATE Employee SET lastName = :#{#changedEmployee.lastName}, name = :#{#changedEmployee.name}, fatherName = :#{#changedEmployee.fatherName}, " +
            " salary = :#{#changedEmployee.salary}, experience = :#{#changedEmployee.experience}, position = :#{#changedEmployee.position}, route = :#{#changedEmployee.route} WHERE id = :id")
    void update(@Param("id") long id, @Param("changedEmployee") Employee changedEmployee);

    Employee getOneByLastNameAndNameAndFatherName(String lastName, String name, String fatherName);
}
