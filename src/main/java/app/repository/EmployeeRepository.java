package app.repository;

import app.model.Employee;
import app.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    @Query("select * from employee join position on position.id = employee.position_id where position.name = :position")
//    Employee findByPosition(@Param("position") String position);

    List<Employee> findByPosition(Position position);
}
