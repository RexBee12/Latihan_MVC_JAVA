/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import model.Lecturer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOQ
 */
public class LecturerDAO {

    private static final Logger logger = Logger.getLogger(LecturerDAO.class.getName());

    private static final String SQL_INSERT     = "INSERT INTO dosen (NIK, nama, nidn, keahlian) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE     = "UPDATE dosen SET NIK=?, nama=?, keahlian=? WHERE nidn=?";
    private static final String SQL_DELETE     = "DELETE FROM dosen WHERE nidn=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM dosen";
    private static final String SQL_SEARCH     = "SELECT * FROM dosen WHERE nama LIKE ? OR nidn LIKE ?";

    private Connection connection;

    public LecturerDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Gagal koneksi database", ex);
        }
    }

    private Lecturer mapRow(ResultSet rs) throws SQLException {
        return new Lecturer(
            rs.getInt("id"),
            rs.getString("NIK"),
            rs.getString("nama"),
            rs.getString("nidn"),
            rs.getString("keahlian")
        );
    }

    public int create(Lecturer lecturer) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, lecturer.getNik());
            stmt.setString(2, lecturer.getName());
            stmt.setString(3, lecturer.getNidn());
            stmt.setString(4, lecturer.getKeahlian());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal insert dosen: " + lecturer.getNidn(), e);
            return 0;
        }
    }

    public List<Lecturer> getLecturer() {
        List<Lecturer> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengambil data dosen", e);
        }
        return list;
    }

    public int update(Lecturer lecturer, String nidn) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, lecturer.getNik());
            stmt.setString(2, lecturer.getName());
            stmt.setString(3, lecturer.getKeahlian());
            stmt.setString(4, nidn);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal update dosen NIDN: " + nidn, e);
            return 0;
        }
    }

    public int delete(String nidn) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setString(1, nidn);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal delete dosen NIDN: " + nidn, e);
            throw e;
        }
    }

    public List<Lecturer> search(String keyword) {
        List<Lecturer> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SEARCH)) {
            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal search dosen: " + keyword, e);
        }
        return list;
    }
}