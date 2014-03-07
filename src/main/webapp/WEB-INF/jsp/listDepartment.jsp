<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="GET" action="${pageContext.request.contextPath}/${departmentFormAction}">
    <table align="center">
        <c:forEach items="${departmentList}" var="department" >
        <tr>
            <td>${department.name}</td>
            <td><input type="submit" value="AddEmpl" name="addEmployee"></td>
            <td><input type="submit" value="Edit" name="editDepartment"></td>
            <td><input type="submit" value="EmplList" name="listEmployee"></td>
            <td><input type="submit" value="Delete" name="deleteDepartment"></td>
        </tr>
        </c:forEach>
    </table>
</form:form>

<%@ include file="common/header.jsp" %>