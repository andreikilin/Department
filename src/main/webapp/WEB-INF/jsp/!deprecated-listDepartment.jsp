<%@ include file="common/header.jsp" %>

<c:choose>
    <c:when test="${!departmentList.isEmpty()}" >
        <table align="center">
            <c:forEach items="${departmentList}" var="department">
                    <tr>
                        <td>${department.name}</td>
                        <td><a href="/department/${department.id}/add">     <button type="button">Add        </button></a></td>
                        <td><a href="/department/${department.id}/edit">    <button type="button">Edit       </button></a></td>
                        <td><a href="/department/${department.id}/list">    <button type="button">Employees  </button></a></td>
                        <td><a href="/department/${department.id}/delete">  <button type="button">Delete     </button></a></td>

                    </tr>
            </c:forEach>
        </table>

    </c:when>
    <c:when test="${departmentList.isEmpty()}" >
        <h3 align="center">No departments</h3>
    </c:when>
</c:choose>

<%@ include file="common/footer.jsp" %>