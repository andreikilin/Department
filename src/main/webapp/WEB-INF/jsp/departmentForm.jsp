<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/${departmentFormAction}" commandName="editDepartment">
    <table id="myTable">
        <tr>
            <td>Department Name: </td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>
        <tr>
            <td><input type="button" value="Save" onclick="addContact()"></td>
        </tr>

        <script type="text/javascript">
            function addContact(){
                var myTable = document.getElementById("myTable");
                var newTR = document.createElement("tr");
                var newName = document.createElement("td");
                newName.innerHTML = document.forms[0].name.value;
                newTR.appendChild(newName);
                myTable.appendChild(newTR);
                document.forms[0].reset()
            }
        </script>
    </table>

</form:form>

<%@ include file="common/footer.jsp" %>