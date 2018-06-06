package app.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForm {
    private String lastName;

    private String name;

    private String fatherName;

    private Integer experience;

    private Integer salary;

    private String routeName;

    private String positionName;

}
