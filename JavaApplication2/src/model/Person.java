/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LOQ
 */
public abstract class Person {

    protected String nik;
    protected String name;

    protected Person(String nik, String name) {
        this.nik  = nik;
        this.name = name;
    }

    public String getNik()  { return nik; }
    public String getName() { return name; }

    public void setNik(String nik)   { this.nik  = nik; }
    public void setName(String name) { this.name = name; }
}