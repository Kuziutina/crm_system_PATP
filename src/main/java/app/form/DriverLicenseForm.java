package app.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class DriverLicenseForm {
    private String category;
    private Date dateApplication;
    private String lastName;
    private String name;
    private String fatherName;
    private String info;
    private String dateApplicationString;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void setDateApplication(String dateApplication) {
        try {
            this.dateApplication = dateFormat.parse(dateApplication);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDateApplicationString() {
        return dateFormat.format(dateApplication);
    }
}
