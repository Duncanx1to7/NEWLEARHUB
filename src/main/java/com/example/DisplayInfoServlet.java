package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/display-info")
public class DisplayInfoServlet extends HttpServlet {
    	private static final String FILE_PATH = "d:/LEARNHUB/projectnewbi/data/personal_info.txt";

@Override
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	// อ่านข้อมูลจากไฟล์
        	List<String[]> records = new ArrayList<>();
       	try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            	String line;
            	while ((line = reader.readLine()) != null) {
               records.add(line.split(",")); // แยกข้อมูลด้วยเครื่องหมาย ","
            }
        }

       	// แสดงผลในรูปแบบ HTML
        	response.setContentType("text/html;charset=UTF-8");
        	try (PrintWriter out = response.getWriter()) {
            		out.println("<!DOCTYPE html>");
           		out.println("<html lang='en'>");
            		out.println("<head>");
            		out.println("<meta charset='UTF-8'>");
            		out.println("<title>แสดงข้อมูล</title>");
            		out.println("<style>");
            		out.println("table { border-collapse: collapse; width: 100%; }");
            		out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
          		out.println("th { background-color: #f4f4f4; }");
            		out.println("</style>");
            		out.println("</head>");
            		out.println("<body>");
            		out.println("<h1>ข้อมูลส่วนบุคคล</h1>");
            		out.println("<table>");
            		out.println("<tr><th>ชื่อ</th><th>อีเมล</th><th>เบอร์โทร</th></tr>");

            	for (String[] record : records) {
                	out.println("<tr>");
             	for (String field : record) {
                    	out.println("<td>" + field + "</td>");
                }
                	out.println("</tr>");
            }
            		out.println("</table>");
            		out.println("<br><a href='index.html'>กลับไปหน้าหลัก</a>");
            		out.println("</body>");
            		out.println("</html>");
        	}
           }
         }
