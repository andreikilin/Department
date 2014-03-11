<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
    <c:when test="${!departmentList.isEmpty()}" >
        <table align="center">
            <c:forEach items="${departmentList}" var="department">
                <form:form method="POST" action="${pageContext.request.contextPath}/department/${department.id}/action">

                    <tr>
                        <td>${department.name}</td>

                        <a>
                            <button/>
                        </a>

                        <td><input type="submit" value="Add" name="departmentAction"></td>
                        <td><input type="submit" value="Edit" name="departmentAction"></td>
                        <td><input type="submit" value="Employees" name="departmentAction"></td>
                        <td><input type="submit" value="Delete" name="departmentAction"></td>
                    </tr>
                </form:form>
            </c:forEach>
        </table>

    </c:when>
    <c:when test="${departmentList.isEmpty()}" >
        <h3 align="center">No departments</h3>
    </c:when>
</c:choose>


<%@ include file="common/footer.jsp" %>