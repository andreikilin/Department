<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
    <c:when test="${!employeeList.isEmpty() && employeeList != null}" >
        <h3 align="center">You cannot delete department "${department.name}", because it not empty</h3>
        <table align="center" border="1">
            <tr>
                <td>Name</td>
                <td>Surname</td>
                <td>Inn</td>
                <td>Email</td>
                <td>Birthday</td>
            </tr>
        <c:forEach items="${employeeList}" var="employee">
            <tr>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.inn}</td>
                <td>${employee.email}</td>
                <td>${employee.birthday}</td>
            </tr>
        </c:forEach>
        </table>
    </c:when>

    <c:when test="${employeeList == null}" >
        <table align="center">
        <form:form method="POST" action="${pageContext.request.contextPath}/${departmentFormAction}" >
            <tr>
                <td>Are you sure you want to delete "${department.name}"?</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete"></td>
            </tr>
        </form:form>
        </table>
    </c:when>
</c:choose>

<%@ include file="common/footer.jsp" %>

<%--<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>--%>

<%--<a:test footerJSP="index.jsp" title="Add depamrnte" caption="HELLO MY BOSS">--%>
    <%--<h1>Departments:</h1>--%>

    <%--<table>--%>

    <%--</table>--%>
<%--</a:test>--%>