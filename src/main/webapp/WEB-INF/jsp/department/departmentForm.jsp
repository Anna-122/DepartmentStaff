<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        <%@include file="/WEB-INF/jsp/style/department.css" %>
        <%@include file="/WEB-INF/jsp/style/style.css" %>
    </style>
    <%@include file="/WEB-INF/jsp/header.jsp" %>
</head>
<body>
<header class="header">
    <h1 class="h1">Department Staff</h1>
</header>
<div style="width: 100%">
    <form class="row g-3 form-shadow"
          action="${pageContext.request.contextPath}/department/save" method="post">
        <div class="head">
            <h2>${not empty department && not empty department.id?"Edit": "Add"} Department</h2>
        </div>
        <input type="hidden" name="id" value="<c:out value="${department.id}"/>"/>
        <div class="col-md-6">
            <label for="departmentName" class="form-label"></label>
            <input type="text" class="form-control" id="departmentName" name="departmentName" size="45"
                   placeholder="Department name..."
                   value="<c:out value="${department.departmentName}"/>"/>
            <p class="error"><c:out value="${errors['departmentName']}"/></p>
        </div>
        <div>
            <a class="btn-form btn-left pointer" type="submit" href="/">Back</a>
            <input class="editOrAdd btn-left pointer" type="submit" name="departmentAddOrEdit"
                   value="${not empty department && not empty department.id?"Edit" : "Add"}"/>
        </div>
    </form>
</div>
</body>
</html>