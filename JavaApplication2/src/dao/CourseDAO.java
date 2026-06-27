/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOQ
 */
public class CourseDAO {

    private static final Logger logger = Logger.getLogger(CourseDAO.class.getName());

    private static final String SQL_INSERT     = "INSERT INTO mata_kuliah (kode, nama_mk, sks, semester) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE     = "UPDATE mata_kuliah SET nama_mk=?, sks=?, semester=? WHERE kode=?";
    private static final String SQL_DELETE     = "DELETE FROM mata_kuliah WHERE kode=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM mata_kuliah";
    private static final String SQL_SEARCH     = "SELECT * FROM mata_kuliah WHERE nama_mk LIKE ? OR kode LIKE ?";

    private Connection connection;

    public CourseDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Gagal koneksi database", ex);
        }
    }

    private Course mapRow(ResultSet rs) throws SQLException {
        return new Course(
            rs.getInt("id"),
            rs.getString("kode"),
            rs.getString("nama_mk"),
            rs.getInt("sks"),
            rs.getInt("semester")
        );
    }

    public int create(Course course) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, course.getKode());
            stmt.setString(2, course.getNamaMk());
            stmt.setInt(3, course.getSks());
            stmt.setInt(4, course.getSemester());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal insert mata kuliah: " + course.getKode(), e);
            return 0;
        }
    }

    public List<Course> getCourse() {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengambil data mata kuliah", e);
        }
        return list;
    }

    public int update(Course course, String kode) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, course.getNamaMk());
            stmt.setInt(2, course.getSks());
            stmt.setInt(3, course.getSemester());
            stmt.setString(4, kode);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal update mata kuliah kode: " + kode, e);
            return 0;
        }
    }

    public int delete(String kode) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setString(1, kode);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal delete mata kuliah kode: " + kode, e);
            throw e;
        }
    }

    public List<Course> search(String keyword) {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SEARCH)) {
            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal search mata kuliah: " + keyword, e);
        }
        return list;
    }
}