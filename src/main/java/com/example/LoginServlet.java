package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    	// ชื่อผู้ใช้และรหัสผ่านที่กำหนดไว้ (Hardcoded สำหรับการทดลอง)
    	private static final String USERNAME = "admin";
    	private static final String PASSWORD = "1234";

@Override
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	// รับข้อมูลจากฟอร์ม
        	String username = request.getParameter("username");
        	String password = request.getParameter("password");

        	// ตรวจสอบชื่อผู้ใช้และรหัสผ่าน
        	response.setContentType("text/html;charset=UTF-8");
        	try (PrintWriter out = response.getWriter()) {
           	 if (USERNAME.equals(username) && PASSWORD.equals(password)) {
              	// เข้าสู่ระบบสำเร็จ
                out.println("<h1>ยินดีต้อนรับ, " + username + "!</h1>");
                out.println("<a href='mainpage.html'>เข้าสู่ระบบการจัดการข้อมูลบุคลากร</a>");
            } else {
                // เข้าสู่ระบบไม่สำเร็จ
                out.println("<h1>ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง!</h1>");
                out.println("<a href='login.html'>ลองอีกครั้ง</a>");
            }
        }
    }
}
