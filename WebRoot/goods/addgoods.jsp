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
	<script type="text/javascript">
		$(document).ready(function() {
		var count = 1;
			$("#submitUpdate").click(function(){
				$("#form1").attr("action","/HibernateStudy/goods/addGoods.action");
				$("#form1").submit();
			});
			$("#updateImg").click(function(){
				$("#form1").attr("action","/HibernateStudy/goods/update.action?gid=${requestScope.goods.gid}");
				$("#form1").attr("enctype","multipart/form-data");
				$("#form1").submit();
			});
			$("#addMore").click(function(){
				count++;
				$("#baoliu").append("<input type = 'file'  name ='goodsImg"+coumt+"id = 'goodsImg"+count+"/>");
			});
		})
	</script>
  </head>
  
  <body>
  
  
  	<form method = "post" name = "form1" id = "form1">
  		gname <input type="text" name = "gname"/><br />
  		gnum <input type="text" name = "gnum" /><br />
  		gprice <input type ="text" name ="gprice" /><br />
  		glocation <input type ="text" name ="glocation" /><br />
  		gtype:<select name = "gtype">
  			<c:forEach items="${requestScope.Gtypes}" var = "gtype" >
  				<option value ="${gtype.gtypeid}">${gtype.gtypename}</option>
  			</c:forEach>
  		</select><br />
  		<input type = "button" value= "添加商品" id = "submitUpdate" name = "submitUpdate"/>
  		img:<input type = "file"  name ="goodsImg1" id = "goodsImg1"/>	
  		<div id = "baoliu"></div>
  		<br /><%-- <input type ="button" value ="上传图片" id = "updateImg" name ="updateImg"/><br />
  		<c:forEach items="${ requestScope.goods.imgses}" var = "img">
  			<img height="200" width="200" src="/HibernateStudy/${img.imgpath }">
  		</c:forEach> --%>
  	 </form>
  	  	 <a href = "/HibernateStudy/goods/list.jsp">回主页</a>
  </body>
</html>
