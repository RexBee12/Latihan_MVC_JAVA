/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOQ
 */
public class StudentDAO {

    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    private static final String SQL_INSERT =
        "INSERT INTO mahasiswa (NIK, nama, nim, program_studi) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE =
        "UPDATE mahasiswa SET NIK=?, nama=?, program_studi=? WHERE nim=?";
    private static final String SQL_DELETE =
        "DELETE FROM mahasiswa WHERE nim=?";
    private static final String SQL_SELECT_ALL =
        "SELECT * FROM mahasiswa";
    private static final String SQL_SEARCH =
        "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ?";

    private Connection connection;

    public StudentDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Gagal membuat koneksi database", ex);
        }
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
            rs.getString("NIK"),
            rs.getString("nama"),
            rs.getString("nim"),
            rs.getString("program_studi")
        );
    }

    public int create(Student student) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, student.getNik());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getNim());
            stmt.setString(4, student.getStudyProgram());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal insert mahasiswa: " + student.getNim(), e);
            return 0;
        }
    }

    public List<Student> getStudent() {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) students.add(mapRow(rs));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengambil data mahasiswa", e);
        }
        return students;
    }

    public int update(Student student, String nim) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, student.getNik());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getStudyProgram());
            stmt.setString(4, nim);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal update mahasiswa NIM: " + nim, e);
            return 0;
        }
    }

    public int delete(String nim) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setString(1, nim);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal delete mahasiswa NIM: " + nim, e);
            throw e;
        }
    }

    public List<Student> search(String keyword) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SEARCH)) {
            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) students.add(mapRow(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal search mahasiswa: " + keyword, e);
        }
        return students;
    }
}