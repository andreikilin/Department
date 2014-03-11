<%@ include file="common/header.jsp" %>
<c:choose>
    <c:when test="${employeeList.size() != 0}">
        <h3 align="center">Employees</h3>
        <table border="1" align="center">
            <tr>
                <td>Name</td>
                <td>Surname</td>
                <td>Inn</td>
                <td>Email</td>
                <td>Birthday</td>
                <td>Department</td>
                <td>Action</td>
            </tr>
            <c:forEach items="${employeeList}" var="employee" >
                <tr>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.inn}</td>
                    <td>${employee.email}</td>
                    <td>${employee.birthday}</td>
                    <td>${employee.department.name}</td>
                    <td>
                        <a href="/employee/${employee.id}/edit">Edit</a>
                        <a href="/employee/${employee.id}/delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:when>
    <c:when test="${employeeList.size() == 0}">
        <h3 align="center">No employees</h3>
    </c:when>
</c:choose>

<%@ include file="common/footer.jsp" %>