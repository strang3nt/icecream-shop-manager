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

    @Override
    public boolean equals(Object o) {
        User u = (User) o;
        return o instanceof User 
            && u.completeName == this.completeName
            && u.birthday.equals(this.birthday);
    }

    // public String getCompleteName() {
    //     return this.completeName;
    // }

    // public LocalDate getBirthday() {
    //     return this.birthday;
    // }
    
    public boolean isAdult() {
        LocalDate now = LocalDate.now();

        return birthday.plusYears(18).equals(now)
            || birthday.plusYears(18).isBefore(now);
        
  
    } 
}
