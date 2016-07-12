<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Prog.kiev.ua</title>
    </head>
    <body>
        <div align="center">
            <h1>Your photo name is: ${photo_name}</h1>

            <input type="submit" value="Delete Photo" onclick="window.location='/mvc-homework1/delete/${photo_name}';" />
            <input type="submit" value="Upload New" onclick="window.location='/mvc-homework1/';" />

            <br/><br/><img src="/mvc-homework1/photo/${photo_name}" />
        </div>
    </body>
</html>
