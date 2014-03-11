<%@ include file="common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/${employeeFormAction}" commandName="editEmployee">
  <table>
        <tr>
            <td>First Name: </td>
            <td><form:input path="firstName" /></td>
            <td><span class="error"><form:errors path="firstName" /></span></td>
            <form:hidden path="employeeId" />
        </tr>
        <tr>
            <td>Last Name: </td>
            <td><form:input path="lastName" /></td>
            <td><span class="error"><form:errors path="lastName" /></span></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><form:input path="email" /></td>
            <td><span class="error"><form:errors path="email" /></span></td>
        </tr>
        <tr>
            <td>Identifical number: </td>
            <td><form:input path="inn" /></td>
            <td><span class="error"><form:errors path="inn" /></span></td>
        </tr>
        <tr>
            <td>Birthday: </td>
            <td>
                <form:select path="day">
                    <form:option value="0" label="--" />
                    <form:options items="${dayList}" />
                </form:select>
            </td>
            <td>
                <form:select path="month">
                    <form:option value="0" label="--" />
                    <form:options items="${monthMap}"/>
                </form:select>
            </td>
            <td>
                <form:select path="year">
                    <form:option value="0" label="--" />
                    <form:options items="${yearList}" />
                </form:select>
            </td>
            <td><span class="error"><form:errors path="birthday" /></span></td>
        </tr>
        <tr>
            <td>Department:</td>
            <td>
                <form:select size="1" path="department" >
                    <form:option value="0" label="Select" />
                    <form:options items="${departmentList}" itemLabel="name" itemValue="id"/>
                </form:select>
            </td>
            <td><span class="error"><form:errors path="department" /></span></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>

</form:form>

<%@ include file="common/footer.jsp" %>