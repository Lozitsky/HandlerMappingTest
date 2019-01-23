package com.kirilo.springMVC.models;

import javax.xml.bind.annotation.XmlRootElement;

//use for converting to xml in the restful service
@XmlRootElement
public class User {
    /*@Size(min = 6, message = "Ім'я повинно містити більше 6 символів")
    @NotNull*/
    private String name;
/*    @Size(min = 8, max = 20, message = "Пароль повиненн бути від 8 до 20 символів")
    @NotNull*/
    private String password;
    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
