package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/save-info")
public class PersonalInfoServlet extends HttpServlet {
   	private static final String FILE_PATH = "d:/LEARNHUB/projectnewbi/data/personal_info.txt";

@Override
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	// รับข้อมูลจากฟอร์ม
        	String name = request.getParameter("name");
        	String email = request.getParameter("email");
        	String phone = request.getParameter("phone");

        	// เขียนข้อมูลลงไฟล์
       	 try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            		writer.write(name + "," + email + "," + phone);
           		writer.newLine();
        }

        	// แสดงผลตอบกลับ
        	response.setContentType("text/html;charset=UTF-8");
        	try (PrintWriter out = response.getWriter()) {
            		out.println("<h1>ข้อมูลถูกบันทึกเรียบร้อยแล้ว</h1>");
            		out.println("<a href=\"addpersonalinfo.html\">กลับไปหน้าเพิ่มข้อมูล</a>");
        }
    }
}
