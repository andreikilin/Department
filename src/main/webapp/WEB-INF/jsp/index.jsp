<%@ include file="common/header.jsp" %>

<h2>Department management</h2>

<c:forEach items="${urlMap}" var="url" >
    <a href="${url.key}">- ${url.value}</a><br>
</c:forEach>

<%@ include file="common/header.jsp" %>