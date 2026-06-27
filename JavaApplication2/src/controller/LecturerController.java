/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LecturerDAO;
import model.Lecturer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LOQ
 */
public class LecturerController {

    private final LecturerDAO lecturerDAO = new LecturerDAO();

    public int create(Lecturer lecturer) {
        if (lecturer.getNidn() == null || lecturer.getNidn().trim().isEmpty()) return -1;
        if (lecturer.getName() == null || lecturer.getName().trim().isEmpty()) return -2;
        if (lecturer.getNik() == null || lecturer.getNik().trim().isEmpty()) return -3;
        return lecturerDAO.create(lecturer);
    }

    public List<Lecturer> getLecturer() {
        return lecturerDAO.getLecturer();
    }

    public int update(Lecturer lecturer, String nidn) {
        if (nidn == null || nidn.trim().isEmpty()) return -1;
        if (lecturer.getName() == null || lecturer.getName().trim().isEmpty()) return -2;
        return lecturerDAO.update(lecturer, nidn);
    }

    public int delete(String nidn) throws SQLException {
        if (nidn == null || nidn.trim().isEmpty()) return -1;
        return lecturerDAO.delete(nidn);
    }

    public List<Lecturer> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return lecturerDAO.getLecturer();
        }
        return lecturerDAO.search(keyword);
    }
}