<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Department and Employee Application</title>
    <style>
        <%@include file="/WEB-INF/jsp/style/employee.css" %>
        <%@include file="/WEB-INF/jsp/style/style.css" %>
    </style>
</head>
<body>
<header class="header">
    <h1 class="h1">Department Staff</h1>
</header>

<input type="hidden" name="depId" <c:out value="${department.id}"/>/>
<h1 style="text-align: center">Employee Management</h1>
<div>
    <table id="centre">
        <caption class="h2-tag">List of Employee</caption>
        <tr class="border">
            <th class="border name">Name</th>
            <th class="border">Surname</th>
            <th class="border">Phone Number</th>
            <th class="border">Email</th>
            <th class="border">Birthday</th>
            <th class="border">Actions</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td class="border"><c:out value="${employee.employeeName}"/></td>
                <td class="border"><c:out value="${employee.employeeSurname}"/></td>
                <td class="border"><c:out value="${employee.employeePhoneNumber}"/></td>
                <td class="border"><c:out value="${employee.employeeEmail}"/></td>
                <td class="border"><c:out value="${employee.employeeBirthDate}"/></td>

                <td class="border">
                    <a class="btn-form"
                       href="${pageContext.request.contextPath}/employee/delete?id=${employee.id}&departmentId=${departmentId}">Delete</a>
                    <a class="btn-form"
                       href="${pageContext.request.contextPath}/employee/form?id=${employee.id}&departmentId=${departmentId}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="btn-block">
        <a class="employee-btn pointer"
           href="${pageContext.request.contextPath}/employee/form?departmentId=${departmentId}">Create</a>
        <a class="employee-btn pointer" type="submit" href="/">Back</a>
    </div>
</div>
</body>
</html>
