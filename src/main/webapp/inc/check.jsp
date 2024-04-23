<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jspbord.dao.MembersDao" %>    
<jsp:useBean id="db" class="jspbord.dao.DBConnect" scope="page" />

<% request.setCharacterEncoding("utf-8"); 
   String mode = request.getParameter("mode");
   Connection conn = db.getConnection();
   MembersDao dao = new MembersDao(conn);
   
   String uname, column;
   if(mode.equals("id")){
	   uname = request.getParameter("userid");
	   column = "userid";
   }else{
	   uname = request.getParameter("useremail");
	   column = "useremail";
   }
 
   boolean rs = dao.findUser(column, uname);
   out.print(rs); 
%>