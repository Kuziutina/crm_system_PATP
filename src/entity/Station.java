package entity;

import javax.persistence.*;

@Entity
@Table(name = "station")
public class Station {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_id_seq")
    @SequenceGenerator(name = "station_id_seq", sequenceName = "station_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
