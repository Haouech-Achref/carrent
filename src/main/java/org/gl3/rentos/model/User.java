package org.gl3.rentos.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "rentos")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "fname", table = "user")
    private String fname;
    @Column(name = "lname", table = "user")
    private String lname;
    @Column(name = "email", table = "user")
    private String email;
    @Column(name = "telephone", table = "user")
    private int telephone;
    @Column(name = "role", table = "user")
    private String role;
    @Column(name = "password", table = "user")
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
