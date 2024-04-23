<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*, dto.BDto, jspbord.dao.JBoardDao" %> 
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspbord.dao.DBConnect" scope="page"/>
<jsp:useBean id="bDto" class="dto.BDto" scope="page" />
<jsp:setProperty name="bDto" property="*" /> 
<%--
page improt =
String writer = request.getParameter("writer"); 
String title = request.getParameter("title");
String pass = request.getParameter("pass");
String content = request.getParameter("content");

BDto bdto = new BDto();
bdto.setWriter(writer);
bdto.setTitle(title);
bdto.setPass(pass);
bdto.setContent(content);
jsp useBean 또는 이렇게 써줄 수 있음.
--%>
<%
   String id = request.getParameter("id");
   Connection conn = db.getConnection(); 
   JBoardDao dao = new JBoardDao(conn);
   int rs = dao.updateDB(bDto);

   //response.sendRedirect("index.jsp");
%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
  alert("글을 등록했습니다.");
  location.href="./contents.jsp?id=<%=id%>";
</script>
</head>
<body>
<h1>데이터가 성공적으로 등록 되었습니다.</h1>
</body>
