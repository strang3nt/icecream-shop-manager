////////////////////////////////////////////////////////////////////
// [ALESSANDRO] [FLORI] [1186916]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;

public class User {
    String completeName;
    LocalDate birthday;

    public User(String completeName, LocalDate birthday) {
        this.completeName = completeName;
        this.birthday = birthday;
    }

    public User() {

    }

    public String getCompleteName() {
        return this.completeName;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }
    
}
