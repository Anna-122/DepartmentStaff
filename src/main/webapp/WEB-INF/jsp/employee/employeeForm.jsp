<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        <%@include file="/WEB-INF/jsp/style/employee.css" %>
        <%@include file="/WEB-INF/jsp/style/style.css"%>
    </style>
    <%@include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body class="bg-light bg-gradient">
<header class="header">
    <h1 class="h1">Department Staff</h1>
</header>

<div style="width: 100%">
    <form class="row g-3 form-shadow"
          action="${pageContext.request.contextPath}/employee/save" method="post">
        <div class="head">
            <h2>${not empty employee && not empty employee.id?"Edit": "Add"} Employee</h2>
        </div>
        <input type="hidden" name="id" value="<c:out value="${employee.id}"/>"/>
        <div class="col-md-6">
            <label for="inputName" class="form-label">Name</label>
            <input type="text" class="form-control" id="inputName" name="employeeName" size="45"
                   placeholder="Your name..."
                   value="<c:out value="${employee.employeeName}"/>"/>
            <p class="error"><c:out value="${errors['employeeName']}"/></p>
        </div>
        <div class="col-md-6">
            <label for="inputSurname" class="form-label">Surname</label>
            <input type="text" name="employeeSurname" size="45" class="form-control" id="inputSurname"
                   placeholder="Your surname..."
                   value="<c:out value="${employee.employeeSurname}"/>">
            <p class="error"><c:out value="${errors['employeeSurname']}"/></p>
        </div>

        <div class="col-12">
            <label for="employeePhoneNumber" class="form-label">Phone number</label>
            <input type="text" name="employeePhoneNumber" class="form-control" id="employeePhoneNumber"
                   placeholder="+111111111111"
                   value="<c:out value="${employee.employeePhoneNumber}"/>">
            <p class="error"><c:out value="${errors['employeePhoneNumber']}"/></p>
        </div>

        <div class="col-12">
            <label for="employeeEmail" class="form-label">Email</label>
            <input type="email" name="employeeEmail" class="form-control" id="employeeEmail"
                   placeholder="test@gmail.com"
                   value="<c:out value="${employee.employeeEmail}"/>">
            <p class="error"><c:out value="${errors['employeeEmail']}"/></p>
        </div>

        <div class="col-md-6">
            <label for="employeeBirthDate" class="form-label">Birthdate</label>
            <input type="date" name="employeeBirthDate" class="form-control" id="employeeBirthDate"
                   value="<c:out value="${employee.employeeBirthDate}"/>">
            <p class="error"><c:out value="${errors['employeeBirthDate']}"/></p>
        </div>

        <div class="col-md-6">
            <label for="inputDepartment" class="form-label">Department</label>
            <select id="inputDepartment" name="departments" class="form-select">
                <c:forEach items="${departments}" var="department">
                    <option name="option" value="${department.id}" ${department.id == departmentId ? 'selected' : ''}>
                        <c:out value="${department.departmentName}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div>
            <a class="btn-form btn-left pointer" type="submit" href="/">Back</a>
            <input class="editOrAdd btn-left pointer" type="submit" name="employeeAddOrEdit"
                   value="${not empty employee && not empty employee.id?"Edit": "Add"}"/>
        </div>
    </form>
</div>
</body>
</html>
