package com.CIS3368.Homework4.Main.Models;
import javax.persistence.*;

@Entity
@Table(name = "homework4")
public class ChuckJokesClass {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "Result")
    private String result;


    //default constructor
    public ChuckJokesClass()
    {

    }

    public ChuckJokesClass(String id, String result)
    {
        this.id = id;
        this.result = result;
    }

    //getter annd setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
