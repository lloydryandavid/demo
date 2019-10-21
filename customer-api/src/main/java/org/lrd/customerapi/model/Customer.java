package org.lrd.customerapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public void setId(Long id) {
        this.id=id;
    }

    public Long getId(){return id;}

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return lastName;}

    public void setLastName(String lastName){this.lastName = lastName;}

}
