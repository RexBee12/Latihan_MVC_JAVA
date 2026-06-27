/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CourseDAO;
import model.Course;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LOQ
 */
public class CourseController {

    private final CourseDAO courseDAO = new CourseDAO();

    public int create(Course course) {
        if (course.getKode() == null || course.getKode().trim().isEmpty()) return -1;
        if (course.getNamaMk() == null || course.getNamaMk().trim().isEmpty()) return -2;
        if (course.getSks() <= 0) return -3; // SKS harus positif
        if (course.getSemester() <= 0 || course.getSemester() > 8) return -4; // Semester 1-8
        return courseDAO.create(course);
    }

    public List<Course> getCourse() {
        return courseDAO.getCourse();
    }

    public int update(Course course, String kode) {
        if (kode == null || kode.trim().isEmpty()) return -1;
        if (course.getNamaMk() == null || course.getNamaMk().trim().isEmpty()) return -2;
        if (course.getSks() <= 0) return -3;
        if (course.getSemester() <= 0 || course.getSemester() > 8) return -4;
        return courseDAO.update(course, kode);
    }

    public int delete(String kode) throws SQLException {
        if (kode == null || kode.trim().isEmpty()) return -1;
        return courseDAO.delete(kode);
    }

    public List<Course> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return courseDAO.getCourse();
        }
        return courseDAO.search(keyword);
    }
}