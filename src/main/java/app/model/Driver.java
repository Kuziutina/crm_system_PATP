package app.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@DiscriminatorValue("водитель")
@SecondaryTable(name = "", pkJoinColumns = {@PrimaryKeyJoinColumn(name="id", referencedColumnName = "id")})
public class Driver extends Employee {

}
