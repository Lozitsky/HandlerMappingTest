package com.kirilo.springMVC.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDTO {
/*    @NotEmpty
    private String email;*/

    @NotEmpty
    @NotBlank
    @Size(min = 6, max = 30)
    private String name;

    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private boolean admin;

    public LoginDTO() {
    }

/*    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

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
