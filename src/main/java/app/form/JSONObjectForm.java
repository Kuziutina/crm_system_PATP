package app.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class JSONObjectForm implements Serializable{
    private String message;
    private List<String> errors;

    public JSONObjectForm() {
        message = "";
        errors = new ArrayList<>();
    }

    public JSONObjectForm(String message) {
        errors = new ArrayList<>();
        this.message = message;
    }
}
