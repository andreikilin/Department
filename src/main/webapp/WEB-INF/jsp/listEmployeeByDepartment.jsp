<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
    <c:when test="${employeeList.size() != 0}">
        <h3 align="center">Employees of department "${department.name}"</h3>
        <form:form method="POST" action="${pageContext.request.contextPath}/${departmentFormAction}" commandName="toDepartmentForm">
        <table border="1" align="center">
            <tr>
                <td></td>
                <td>Name</td>
                <td>Surname</td>
                <td>Inn</td>
                <td>Email</td>
                <td>Birthday</td>
            </tr>
            <c:forEach items="${employeeList}" var="employee" >
                <tr>
                    <td><form:checkbox path="employeeId" value="${employee.id}" /></td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.inn}</td>
                    <td>${employee.email}</td>
                    <td>${employee.birthday}</td>
                </tr>
            </c:forEach>
            <tr>
                <td><form:hidden path="departmentId"/></td>
                <td><input type="submit" value="Delete"></td>
            </tr>
        </table>
        </form:form>
    </c:when>
    <c:when test="${employeeList.size() == 0}">
        <h3 align="center">No employees</h3>
    </c:when>
</c:choose>

<%@ include file="common/footer.jsp" %>