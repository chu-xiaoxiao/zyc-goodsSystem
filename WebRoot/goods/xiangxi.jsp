<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="/HibernateStudy/js/jquery.js"></script>
    <title>My JSP 'modify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  
  
  	<form method = "post" name = "form1" id = "form1">
  		gid:${requestScope.goods.gid }<input type="hidden" name = "gid" value = "${requestScope.goods.gid }"/><br />
  		gname ${requestScope.goods.gname }<br />
  		gnum  ${requestScope.goods.gnum }<br />
  		gprice ${requestScope.goods.gprice }<br />
  		glocation ${requestScope.goods.glocation}<br />
  		gtype: ${requestScope.goods.gtype.gtypeid} <br />
  		img:
  		<c:forEach items="${ requestScope.goods.imgses}" var = "img">
  			<img height="200" width="200" src="/HibernateStudy/${img.imgpath }">
  		</c:forEach>
  	 </form>
  	  	 <a href = "/HibernateStudy/goods/list.jsp">回主页</a>
  </body>
</html>
