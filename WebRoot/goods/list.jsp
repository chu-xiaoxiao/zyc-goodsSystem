<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/HibernateStudy/js/jquery.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#search").click(
								function() {
									$("#from1").attr("action",
											"/HibernateStudy/goods/GoodsList");
									$("#form1").submit();
								});
						$("#sieze").change(
								function() {
									$("#from1").attr("action",
											"/HibernateStudy/goods/GoodsList");
									$("#form1").submit();
								});
						$("#currentPage").change(
								function() {
									$("#from1").attr("action",
											"/HibernateStudy/goods/GoodsList");
									$("#form1").submit();
								});
						$("#Gtype").change(
								function() {
									$("#from1").attr("action",
											"/HibernateStudy/goods/GoodsList");
									$("#form1").submit();
								});
						$("#nextPage")
								.click(
										function() {
											var nextPage = ${requestScope.page.currentPage};
											if (nextPage >= ${requestScope.page.allPage}) {
												$("#form1")
														.attr("action",
																"/HibernateStudy/goods/GoodsList?currentPage=${requestScope.page.allPage}");
											} else {
												$("#form1")
														.attr(
																"action",
																"/HibernateStudy/goods/GoodsList?currentPage=${requestScope.page.currentPage+1}");
											}
											$("#form1").submit();
										});
						$("#lastPage")
								.click(
										function() {
											var lastPage = ${requestScope.page.currentPage};
											if (lastPage <= 1) {
												$("#form1")
														.attr("action",
																"/HibernateStudy/goods/GoodsList?currentPage=1");
											} else {
												$("#form1")
														.attr(
																"action",
																"/HibernateStudy/goods/GoodsList?currentPage=${requestScope.page.currentPage-1}");
											}
											$("#form1").submit();
										});
						$("#addGoods").click(function(){
							window.location.href="/HibernateStudy/goods/pageaddgoods";
						});
					})
</script>
</head>
<form action="/HibernateStudy/goods/GoodsList.action" method="post"
	id="form1">
	GoodsName:<input type="text" name="goodsName" id="goodsName"
		value="${requestScope.goodsName}"> GoodsGprice:<input
		type="text" name="GoodsGpriceMin" id="GoodsGpriceMin"
		value="${requestScope.goodsGpriceMin}" />～<input type="text"
		name="GoodsGpriceMax" id="GoodsGpriceMax"
		value="${requestScope.goodsGpriceMax}" />
	<hr />
	type:<select name="Gtype" id="Gtype">
		<option value=""></option>
		<c:forEach items="${requestScope.Gtypes}" var="gtype">
			<c:choose>
				<c:when test="${gtype.gtypeid==requestScope.selectGtype.gtypeid}">
					<option value="${gtype.gtypeid}" selected="selected">${gtype.gtypename}</option>
				</c:when>
				<c:otherwise>
					<option value="${gtype.gtypeid}">${gtype.gtypename}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select> 图片数量大于等于<input type="text" name="imgsCount" id="imgsCount"
		value="${requestScope.imgsCount}" />张
	<button id="search" value="Search">Search</button>
	<hr />
	<c:forEach items="${requestScope.page.lists}" var="good">
				<c:forEach items="${good.imgses}" begin="0" end="0" var="img">
				<c:set var="path" value ="/HibernateStudy${img.imgpath}"></c:set>
					<img width="100" height="100" src="${path}"/>
				</c:forEach>
				<a href="goods/xiangxi?gid=${good.gid }">${good.gname}</a>
		  		${good.gnum}
		  		${good.gprice}
		  		${good.glocation}
		  		${good.gprocedate}
		  		${good.gtype.gtypename}
		  		${goods.imgs.imgpath }
		  		<a href="/HibernateStudy/goods/modifyGoods.action?gid=${good.gid}">修改</a>
		  		<a href="/HibernateStudy/goods/deleteGoods.action?gid=${good.gid}">删除</a>
		<hr />
	</c:forEach>
	<button id="lastPage">上一页</button>
	<select name="currentPage" id="currentPage">
		<c:forEach begin="${1}" end="${ requestScope.page.allPage}" var="i">
			<c:choose>
				<c:when test="${i==requestScope.page.currentPage}">
					<option value="${i }" selected="selected">${i}	
				</c:when>
				<c:otherwise>
					<option value="${i }">${i}
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<button id="nextPage">下一页</button>
	一页
	<select name="sieze" id="sieze">
		<c:forEach begin="${1 }" end="${15}" var="i">
			<c:choose>
				<c:when test="${i==requestScope.page.sieze}">
					<option value="${i }" selected="selected">${i}
				</c:when>
				<c:otherwise>
					<option value="${i }">${i}
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	条记录
</form>
<button name= "addGoods" id="addGoods">添加商品信息</button>
</body>
</html>
