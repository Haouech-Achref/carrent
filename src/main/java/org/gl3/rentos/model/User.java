package org.gl3.rentos.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "utilisateur", schema = "rentos")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;
    @Column(name = "fname", table = "utilisateur")
    private String fname;
    @Column(name = "lname", table = "utilisateur")
    private String lname;
    @Column(name = "email", table = "utilisateur")
    private String email;
    @Column(name = "telephone", table = "utilisateur")
    private Integer telephone;
    @Column(name = "role", table = "utilisateur")
    private String role;
    @Column(name = "password", table = "utilisateur")
    private String password;

    public User() {
    }

    public User(String fname, String lname, String email, int telephone, String role, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;

    }

    public void setUser_id(int id) {
        this.user_id = id;
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

    public Integer getTelephone() {
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
