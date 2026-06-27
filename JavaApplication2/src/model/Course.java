/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LOQ
 */
public class Course {

    private int    id;
    private String kode;
    private String namaMk;
    private int    sks;
    private int    semester;

    public Course(int id, String kode, String namaMk, int sks, int semester) {
        this.id       = id;
        this.kode     = kode;
        this.namaMk   = namaMk;
        this.sks      = sks;
        this.semester = semester;
    }

    public Course(String kode, String namaMk, int sks, int semester) {
        this(0, kode, namaMk, sks, semester);
    }

    @Override
    public String toString() { return this.namaMk; }

    public int    getId()        { return id; }
    public String getKode()      { return kode; }
    public String getNamaMk()    { return namaMk; }
    public String getCourseName(){ return namaMk; }
    public int    getSks()       { return sks; }
    public int    getSKS()       { return sks; }
    public int    getSemester()  { return semester; }

    public void setId(int id)        { this.id       = id; }
    public void setKode(String kode) { this.kode     = kode; }
    public void setNamaMk(String n)  { this.namaMk   = n; }
    public void setSks(int sks)      { this.sks      = sks; }
    public void setSemester(int s)   { this.semester = s; }
}