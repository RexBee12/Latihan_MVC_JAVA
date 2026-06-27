/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOQ
 */
public class Student extends Person {

    private String       nim;
    private String       studyProgram;
    private int          jumlahSks;
    private List<KRS>    krsList;

    public Student(String idCard, String name, String nim, String studyProgram) {
        super(idCard, name);
        this.nim          = nim;
        this.studyProgram = studyProgram;
        this.jumlahSks    = 0;
        this.krsList      = new ArrayList<>();
    }

    public Student(String idCard, String name, String nim, String studyProgram, int jumlahSks) {
        super(idCard, name);
        this.nim          = nim;
        this.studyProgram = studyProgram;
        this.jumlahSks    = jumlahSks;
        this.krsList      = new ArrayList<>();
    }

    @Override
    public String toString() { return this.name; }

    public String    getNim()           { return nim; }
    public String    getNik()           { return nik; }
    public String getCardID()           { return nik; }
    public String    getNIM()           { return nim; }
    public String    getStudyProgram()  { return studyProgram; }
    public String    getStudyProgress() { return studyProgram; }
    public int       getJumlahSks()    { return jumlahSks; }
    public List<KRS> getKrsList()      { return krsList; }

    public void setNim(String nim)              { this.nim          = nim; }
    public void setStudyProgram(String program) { this.studyProgram = program; }
    public void setJumlahSks(int sks)           { this.jumlahSks    = sks; }

    public void addKRS(KRS krs) { krsList.add(krs); }
}