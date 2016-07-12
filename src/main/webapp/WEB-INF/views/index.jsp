<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Photos, motherfucker</title>
    <style type="text/css">
	body {
				color: #665544;
				background-color: #d4d0c6;
				background-image: url("images/backdrop.gif");
				font-family: Georgia, "Times New Roman", serif;
				text-align: center;}
	.entry {
				width: 220px;
				float: left;
				margin: 10px;
				height: 198px;
				background-repeat: no-repeat;
				background-position: bottom;}
	 figure {
				display: block;
				width: 202px;
				height: 170px;
				background-color: #e7e3d8;
				margin: 0;
				padding: 9px;
				text-align: left;}
	.figure-delete{
	
		background-color:  #000000;
	
	}
</style>
  </head>
  <body>
     <div align="center">
        <form action="/mvc-homework1/view" method="GET">
            Photo name: <input type="text" name="photo_name">
            <input type="submit"  value = "View photo"/>
        </form>

        <form action="/mvc-homework1/add_photo" enctype="multipart/form-data" method="POST">
            Photo: <input type="file" name="photo">
            Photo name : <input type = "text" name = "photo_name">
            <input type="submit" value = "Load photo"/>
        </form>
       
        <input type="submit" value="Delete choosen" onclick="window.location='/mvc-homework1/deleteChoosen';" />

	</div>

	
	<c:forEach items= "${photos}" var = "entry">
	<div class="entry">
		<figure 
				<c:set var="contains" value="false" />
				<c:forEach var="item" items="${delete_photos}">
  					<c:if test="${item eq entry.key}">
    					<c:set var="contains" value="true" />
  					</c:if>
				</c:forEach>
				<c:if test = "${contains eq 'true'}">
					
					class = "figure-delete"
				
				</c:if>
				>
		<img src="/mvc-homework1/photo/${entry.key}" alt="${entry.key}" width="200" height="140" 
					onclick = "window.location='/mvc-homework1/chooseToDelete/${entry.key}';" />${entry.key}
		</figure>
	</div>
</c:forEach>
</body>
</html>
