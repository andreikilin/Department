<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/${departmentFormAction}" commandName="editDepartment">
    <table>
        <tr>
            <td>Department Name: </td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>

</form:form>

<%@ include file="common/footer.jsp" %>