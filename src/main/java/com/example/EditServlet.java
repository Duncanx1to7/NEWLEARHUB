package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final String FILE_PATH = "d:/LEARNHUB/projectnewbi/data/personal_info.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");

        String[] recordToEdit = null;

        // ค้นหาข้อมูลที่จะแก้ไข
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].equals(id)) {
                    recordToEdit = record;
                    break;
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {
            if (recordToEdit == null) {
                out.println("<p>ไม่พบข้อมูล</p>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head><meta charset='UTF-8'><title>แก้ไขข้อมูล</title></head>");
                out.println("<body>");
                out.println("<h1>แก้ไขข้อมูล</h1>");
                out.println("<form action='edit' method='post'>");
                out.println("<input type='hidden' name='oldName' value='" + recordToEdit[0] + "'>");
                out.println("<label>ชื่อ: <input type='text' name='name' value='" + recordToEdit[0] + "' required></label><br>");
                out.println("<label>อีเมล: <input type='email' name='email' value='" + recordToEdit[1] + "' required></label><br>");
                out.println("<label>เบอร์โทร: <input type='text' name='phone' value='" + recordToEdit[2] + "' required></label><br>");
                out.println("<button type='submit'>บันทึก</button>");
                out.println("</form>");
                out.println("<br><a href='search.html'>กลับไปค้นหา</a>");
                out.println("</body></html>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldName = request.getParameter("oldName");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        List<String[]> records = new ArrayList<>();

        // อ่านไฟล์และอัปเดตข้อมูล
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].equals(oldName)) {
                    record[0] = name;
                    record[1] = email;
                    record[2] = phone;
                }
                records.add(record);
            }
        }

        // เขียนข้อมูลกลับไปที่ไฟล์
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        }

        response.sendRedirect("search.html");
    }
}
