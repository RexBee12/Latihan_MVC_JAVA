/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LOQ
 */
public class Lecturer extends Person {

    private int    id;
    private String nidn;
    private String keahlian;

    public Lecturer(int id, String nik, String name, String nidn, String keahlian) {
        super(nik, name);  // nik sudah di Person
        this.id       = id;
        this.nidn     = nidn;
        this.keahlian = keahlian;
    }

    public Lecturer(String nik, String name, String nidn, String keahlian) {
        this(0, nik, name, nidn, keahlian);
    }

    @Override
    public String toString() { return this.name; }

    public int    getId()       { return id; }
    public String getNidn()     { return nidn; }
    public String getNIDN()     { return nidn; }
    public String getKeahlian() { return keahlian; }

    public void setId(int id)         { this.id       = id; }
    public void setNidn(String nidn)  { this.nidn     = nidn; }
    public void setKeahlian(String k) { this.keahlian = k; }
}