<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table align="center">
    <form:form method="POST" action="${pageContext.request.contextPath}/${employeeFormAction}" >
        <tr>
            <td>Are you sure you want to delete "${employee.firstName} ${employee.lastName} ${employee.email} ${employee.inn}"?</td>
        </tr>
        <tr>
            <td><input type="submit" value="Delete"></td>
        </tr>
    </form:form>
</table>

<%@ include file="common/footer.jsp" %>