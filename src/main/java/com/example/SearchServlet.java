package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final String FILE_PATH = "d:/LEARNHUB/projectnewbi/data/personal_info.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.setContentType("text/html;charset=UTF-8");

        List<String[]> matchedRecords = new ArrayList<>();

        // อ่านไฟล์และค้นหา
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].toLowerCase().contains(query.toLowerCase())) {
                    matchedRecords.add(record);
                }
            }
        }

        // เขียน HTML ผลลัพธ์การค้นหา
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>ผลลัพธ์การค้นหา</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ผลลัพธ์การค้นหา</h1>");

            if (matchedRecords.isEmpty()) {
                out.println("<p>ไม่พบข้อมูลที่ตรงกับคำค้นหา</p>");
            } else {
                out.println("<table border='1'>");
                out.println("<thead>");
                out.println("<tr><th>ชื่อ</th><th>อีเมล</th><th>เบอร์โทร</th><th>การดำเนินการ</th></tr>");
                out.println("</thead>");
                out.println("<tbody>");
                for (String[] record : matchedRecords) {
                    out.println("<tr>");
                    out.println("<td>" + record[0] + "</td>");
                    out.println("<td>" + record[1] + "</td>");
                    out.println("<td>" + record[2] + "</td>");
                    out.println("<td>");
                    out.println("<a href='edit?id=" + record[0] + "'>แก้ไข</a> | ");
                    out.println("<a href='delete?id=" + record[0] + "'>ลบ</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
            }

            out.println("<br><a href='search.html'>ค้นหาใหม่</a>");
            out.println("<br><a href='index.html'>กลับหน้าหลัก</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
