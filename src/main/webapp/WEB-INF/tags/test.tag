<%@attribute name="title" type="java.lang.String" %>
<%@attribute name="caption" type="java.lang.String" %>
<%@attribute name="footerJSP" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>

<h1>${caption}</h1>

<jsp:doBody/>

<c:if test="${footerJSP ne null}">
    <jsp:include page="${footerJSP}"/>
</c:if>

</body>
</html>