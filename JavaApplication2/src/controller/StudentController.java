/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.Student;
import dao.StudentDAO;

/**
 *
 * @author LOQ
 */
public class StudentController {

    private final StudentDAO studentDAO = new StudentDAO();

    public int create(Student student) {
        // Logic validasi di controller
        if (student.getNim() == null || student.getNim().trim().isEmpty()) {
            return -1; // NIM kosong
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return -2; // Nama kosong
        }
        if (student.getNik() == null || student.getNik().trim().isEmpty()) {
            return -3; // NIK kosong
        }
        return studentDAO.create(student);
    }

    public List<Student> getStudent() {
        return studentDAO.getStudent();
    }

    public int update(Student student, String nim) {
        if (nim == null || nim.trim().isEmpty()) return -1;
        if (student.getName() == null || student.getName().trim().isEmpty()) return -2;
        return studentDAO.update(student, nim);
    }

    public int delete(String nim) throws SQLException {
        if (nim == null || nim.trim().isEmpty()) return -1;
        return studentDAO.delete(nim);
    }

    public List<Student> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentDAO.getStudent();
        }
        return studentDAO.search(keyword);
    }
}