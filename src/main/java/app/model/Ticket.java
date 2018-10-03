package app.model;

import lombok.*;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Column(name = "user_id")
//    private Integer user_id;
//    @Column(name = "status_id")
//    private Integer status_id;

    private String name;
    private String email;
    private String theme;
    private String body;

    private Date dateApplication;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private TicketStatus ticketStatus;

    public String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateApplication);
    }

}
