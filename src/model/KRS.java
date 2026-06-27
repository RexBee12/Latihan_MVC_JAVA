/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Lecturer;

/**
 *
 * @author LOQ
 */
public class KRS {

    private int    id;
    private int    mahasiswaId;
    private int    dosenId;
    private int    mataKuliahId;
    private double nilaiAkhir;
    private int    semester;
    private String grade;

    private static final double MIN_SCORE_A = 85;
    private static final double MIN_SCORE_B = 75;
    private static final double MIN_SCORE_C = 60;

    // Constructor utama — terima 3 nilai, hitung sendiri
    public KRS(int mahasiswaId, int dosenId, int mataKuliahId,
               double nilaiSikap, double nilaiUts, double nilaiUas, int semester) {
        this.mahasiswaId  = mahasiswaId;
        this.dosenId      = dosenId;
        this.mataKuliahId = mataKuliahId;
        this.semester     = semester;
        this.nilaiAkhir   = hitungNilaiAkhir(nilaiSikap, nilaiUts, nilaiUas);
        this.grade        = hitungGrade(this.nilaiAkhir);
    }

    // Constructor lama agar InputNilai.java tetap kompatibel
    public KRS(Course course, double nilai) {
        this.mataKuliahId = course.getId();
        this.nilaiAkhir   = nilai;
        this.grade        = hitungGrade(nilai);
    }

    private double hitungNilaiAkhir(double sikap, double uts, double uas) {
        return (sikap * 0.3) + (uts * 0.35) + (uas * 0.35);
    }

    private String hitungGrade(double nilai) {
        if      (nilai >= MIN_SCORE_A) return "A";
        else if (nilai >= MIN_SCORE_B) return "B";
        else if (nilai >= MIN_SCORE_C) return "C";
        else                           return "D";
    }

    public int    getId()           { return id; }
    public int    getMahasiswaId()  { return mahasiswaId; }
    public int    getDosenId()      { return dosenId; }
    public int    getMataKuliahId() { return mataKuliahId; }
    public double getNilaiAkhir()   { return nilaiAkhir; }
    public double getScore()        { return nilaiAkhir; }
    public double getNilai()        { return nilaiAkhir; }
    public int    getSemester()     { return semester; }
    public String getGrade()        { return grade; }

    public void setId(int id)       { this.id = id; }
}
