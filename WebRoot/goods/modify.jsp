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
			var count = 1;
		function test1(id){
			var xmlhttp;
			$("#form1").attr("enctype","multipart/form-data");
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		 	 }
		 	 xmlhttp.open("POST","/HibernateStudy/img/deleteImg.action?imgId="+id,true);
			 xmlhttp.send();
		}
		$(document).ready(function() {
	
			/* $("#updateImg").click(function(){
				$.ajax({
				    url : "/HibernateStudy/goods/update.action?gid=${requestScope.goods.gid}",  
				    type: 'POST',
				    cache: false,
				    data: new FormData($('#form1')[0]),
				    processData: false,
				    contentType: false
				}).done(function(res) {}).fail(function(res) {}); 
			});  */
			$("#submitUpdate").click(function(){
				$("#form2").attr("action","/HibernateStudy/goods/modifyAction.action");
				$("#form2").submit();
			});
			$("#updateImg").click(function(){
				$("#form1").attr("action","/HibernateStudy/goods/update.action?gid=${requestScope.goods.gid}");
				$("#form1").attr("enctype","multipart/form-data");
				$("#form1").submit();
			});
			$("#addMore").click(function (){
				count++;
				$("#baoliu").append("<input type = 'file'  name ='goodsImg"+count+"id = 'goodsImg"+count+"/><br />");
			});
		})
	</script>
  </head>
  
  <body>
  
 	<form method = "post" name = "form2" id = "form2" >
  		gid:${requestScope.goods.gid }<input type="hidden" name = "gid" value = "${requestScope.goods.gid }"/><br />
  		gname <input type="text" name = "gname" value="${requestScope.goods.gname }"/><br />
  		gnum <input type="text" name = "gnum" value="${requestScope.goods.gnum }"/><br />
  		gprice <input type ="text" name ="gprice" value = "${requestScope.goods.gprice }"/><br />
  		glocation <input type ="text" name ="glocation" value="${requestScope.goods.glocation}"/><br />
  		gtype:<select name = "gtype">
  			<c:forEach items="${requestScope.Gtypes}" var = "gtype" >
  				<c:choose>
  					<c:when test="${gtype.gtypeid==requestScope.selectGtype.gtypeid}">
	  					<option value ="${gtype.gtypeid}" selected="selected">${gtype.gtypename}</option>
	  				</c:when>
		  			<c:otherwise>
		  				<option value ="${gtype.gtypeid}">${gtype.gtypename}</option>
		  			</c:otherwise> 
	  			</c:choose>
  			</c:forEach>
  		</select><br />
  	</form>
   	<form method = "post" name = "form1" id = "form1" enctype="multipart/form-data">
  		<input type="hidden" name = "gid" value = "${requestScope.goods.gid }"/><br />
  		img:<input type = "button" value = "增加图片" id = "addMore" name = "addMore" onclick ="addDiv()"/><br /><div id = "serverResponse"></div><input type = "file"  name ="goodsImg1" id = "goodsImg1"/>  <div id="baoliu"></div>
  		<input type ="button" value ="上传图片" id = "updateImg" name ="updateImg"/>
  		<c:forEach items="${ requestScope.goods.imgses}" var = "img">
  			<img height="200" width="200" src="/HibernateStudy/${img.imgpath }">
  			<button name = "deleteImg" ID = "deleteImg" value = "${img.imgid }" onclick = "test1	(${img.imgid })">删除</button>
  		 </c:forEach><br />
  		<input type = "button" value= "提交修改" id = "submitUpdate" name = "submitUpdate"/>
  	 </form>
  	 <a href = "/HibernateStudy/goods/list.jsp">回主页</a>
  </body>
</html>
