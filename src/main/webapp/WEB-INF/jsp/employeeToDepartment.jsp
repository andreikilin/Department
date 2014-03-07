<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
    <c:when test="${noDepartmentEmployeeList != null}">
        <h2 align="center">${departmentName}</h2>
        <form:form method="POST" action="${pageContext.request.contextPath}/${departmentFormAction}" commandName="toDepartmentForm">
            <table align="center" border="1">
                <tr>
                    <td></td>
                    <td>Name</td>
                    <td>Surname</td>
                    <td>Inn</td>
                    <td>Email</td>
                    <td>Birthday</td>
                </tr>
                <c:forEach items="${noDepartmentEmployeeList}" var="employee" >
                    <tr>
                        <td><form:checkbox path="employeeId" value="${employee.id}" name=""/></td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.inn}</td>
                        <td>${employee.email}</td>
                        <td>${employee.birthday}</td>
                    </tr>
                <%--<form:select size="1" path="employeeId" >--%>
                    <%--<form:option value="0" label="Select" />--%>
                    <%--<form:options items="${noDepartmentEmployeeList}" itemLabel="name" itemValue="id"/>--%>
                <%--</form:select>--%>

            </c:forEach>
            <form:hidden path="departmentId"/>
            <tr>
                <td></td>
                <td><input type="submit" value="Add"></td>
            </tr>
        </table>
        </form:form>
    </c:when>
    <c:when test="${noDepartmentEmployeeList == null}">
        <h2>No free employee</h2>
    </c:when>
</c:choose>



<%@ include file="common/header.jsp" %>