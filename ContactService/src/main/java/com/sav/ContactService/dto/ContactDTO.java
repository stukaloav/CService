package com.sav.ContactService.dto;

import com.sav.ContactService.util.JsonDateDeserial;
import com.sav.ContactService.util.JsonDateSerial;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.Set;

public class ContactDTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public ContactDTO(long id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @JsonSerialize(using=JsonDateSerial.class, as = Date.class)
    public Date getBirthDate() {
        return birthDate;
    }

    @JsonDeserialize(using= JsonDateDeserial.class, as = Date.class)
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
