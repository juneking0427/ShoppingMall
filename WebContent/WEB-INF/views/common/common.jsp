<%-- <%//@ include file="/jsp/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:set var="resources" value="${pageContext.request.contextPath}/resources" />
<c:set var="img" value="${pageContext.request.contextPath}/resources/img" />
<c:set var="css" value="${pageContext.request.contextPath}/resources/css" />
<c:set var="js" value="${pageContext.request.contextPath}/resources/js" />
<c:set var="font" value="${pageContext.request.contextPath}/resources/font" />
<c:set var="upload" value="${pageContext.request.contextPath}/resources/upload/" />
<c:set var="html" value="${pageContext.request.contextPath}/resources/html" />



<script type="text/javascript">
	var contextPath='<c:out value="${pageContext.request.contextPath}" />';	
</script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="../../resources/css/pm.css"> -->


 --%>