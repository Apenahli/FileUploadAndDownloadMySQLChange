<%@taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<h1>Hello</h1>

	<spr:form action="${pageContext.request.contextPath}/uploadFile"
		method="post" enctype="multipart/form-data">

		<input id="file" type="file" name="file" class="file-input" required />

		<button type="submit" class="btn btn-primary">Submit</button>

	</spr:form>

	<spr:form
		action="${pageContext.request.contextPath}/uploadMultipleFiles"
		method="post" enctype="multipart/form-data">

<input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />

		<button type="submit" class="btn btn-primary">Submit</button>

	</spr:form>

	<c:forEach var="employee" items="${employees}">

		<a
			href="${pageContext.request.contextPath}/downloadFile/${employee.id}">${employee.fileName}</a>
		<br>


	</c:forEach>


</body>
</html>