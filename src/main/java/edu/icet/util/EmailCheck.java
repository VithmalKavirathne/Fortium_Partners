package edu.icet.util;

import lombok.Data;

@Data
public class EmailCheck {
    public  boolean isValid(String email) {
        //String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}