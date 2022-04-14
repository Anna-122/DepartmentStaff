<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Department and Employee Application</title>
    <style>
        <%@include file="/WEB-INF/jsp/style/department.css" %>
        <%@include file="/WEB-INF/jsp/style/style.css"%>
    </style>
</head>
<body>

<header class="header">
    <h1 class="h1">Department Staff</h1>
</header>

<h1 style="text-align: center">Department Management</h1>
<div>
    <table id="centre">
        <caption class="h2-tag">List of Department</caption>
        <tr>
            <th class="border name">Name</th>
            <th class="border">Actions</th>
        </tr>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td class="border"><c:out value="${department.departmentName}"/></td>
                <td class="border">
                    <a class="btn-form btn-create"
                       href="/department/form?id=${department.id}">Edit</a>
                    <form class="form form-container" method="post"
                          action="${pageContext.request.contextPath}/department/delete">
                        <a class="btn-form btn-create"
                           href="${pageContext.request.contextPath}/department/delete?id=${department.id}">Delete</a>
                    </form>
                    <a class="btn-form btn-create"
                       href="${pageContext.request.contextPath}/department/employees?id=${department.id}">Employees</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="create-block">
        <a class="btn-form btn-create create" href="/department/form">Create</a>
    </div>
</div>
</body>
</html>
