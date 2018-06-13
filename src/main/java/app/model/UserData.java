package app.model;


import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    private String name;

    @Column(name = "father_name")
    private String fatherName;

    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public String getLastName() {
        return lastName == null? "" : lastName;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getFatherName() {
        return fatherName == null ? "" : fatherName;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }
}
