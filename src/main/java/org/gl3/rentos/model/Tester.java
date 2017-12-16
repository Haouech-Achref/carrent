package org.gl3.rentos.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "memster", schema = "rentos")
public class Tester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "meme", table="memster")
    private String meme;

    @Column(name = "number", table = "memster")
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
