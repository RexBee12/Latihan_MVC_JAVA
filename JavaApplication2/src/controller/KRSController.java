/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KRSDAO;
import model.KRS;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LOQ
 */
public class KRSController {

    private final KRSDAO krsDAO = new KRSDAO();

    public int create(KRS krs) {
        // Logic validasi nilai 0-100
        if (krs.getNilaiAkhir() < 0 || krs.getNilaiAkhir() > 100) return -1;
        if (krs.getMahasiswaId() <= 0) return -2;
        if (krs.getDosenId() <= 0) return -3;
        if (krs.getMataKuliahId() <= 0) return -4;
        return krsDAO.create(krs);
    }

    public List<Object[]> getKRSWithDetail() {
        return krsDAO.getKRSWithDetail();
    }

    public int delete(int id) throws SQLException {
        if (id <= 0) return -1;
        return krsDAO.delete(id);
    }
}