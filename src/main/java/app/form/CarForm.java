package app.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CarForm {
    private String number;
    private String routeName;
    private String carStatusName;
    private Date lastCheckUp;
    private String lastCheckUpString;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");

    public void setLastCheckUp(String lastCheckUp) {
        lastCheckUpString = lastCheckUp;
        System.out.println("i try insert " + lastCheckUp);
        try {
            this.lastCheckUp = dateFormat.parse(lastCheckUp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
