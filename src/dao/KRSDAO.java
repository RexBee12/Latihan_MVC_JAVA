/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import model.KRS;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOQ
 */
public class KRSDAO {

    private static final Logger logger = Logger.getLogger(KRSDAO.class.getName());

    private static final String SQL_INSERT =
        "INSERT INTO krs (mahasiswa_id, dosen_id, mata_kuliah_id, " +
        "nilai_akhir, semester, grade) VALUES (?,?,?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM krs WHERE id=?";

    private static final String SQL_SELECT_ALL =
        "SELECT k.*, m.nama AS nama_mhs, m.nim, mk.nama_mk, mk.sks, d.nama AS nama_dosen " +
        "FROM krs k " +
        "JOIN mahasiswa m ON k.mahasiswa_id = m.id " +
        "JOIN mata_kuliah mk ON k.mata_kuliah_id = mk.id " +
        "JOIN dosen d ON k.dosen_id = d.id";

    private Connection connection;

    public KRSDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Gagal koneksi database", ex);
        }
    }

    public int create(KRS krs) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setInt(1, krs.getMahasiswaId());
            stmt.setInt(2, krs.getDosenId());
            stmt.setInt(3, krs.getMataKuliahId());
            stmt.setDouble(4, krs.getNilaiAkhir());
            stmt.setInt(5, krs.getSemester());
            stmt.setString(6, krs.getGrade());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal insert KRS", e);
            return 0;
        }
    }

    public List<Object[]> getKRSWithDetail() {
        List<Object[]> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("nim"),
                    rs.getString("nama_mhs"),
                    rs.getString("nama_mk"),
                    rs.getInt("sks"),
                    rs.getDouble("nilai_akhir"),
                    rs.getString("nama_dosen")
                });
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengambil data KRS", e);
        }
        return list;
    }

    public int delete(int id) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal delete KRS id: " + id, e);
            throw e;
        }
    }
}