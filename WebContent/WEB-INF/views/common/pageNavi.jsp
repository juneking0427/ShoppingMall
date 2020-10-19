<%@ page session='false' pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%  
  String self = request.getAttribute("javax.servlet.forward.request_uri").toString().trim().replace("\n", "");
  String url =   "<form id=\"listForm\" action=\"" + self + "\" method=\"post\">";
%>
<div class="paging">
  <c:if test="${totalRecord > 0}">
    <a class="first" href="javascript:goList('1')"><img src="./../resources/img/btn_first.gif" width="31" height="31" alt="처음페이지" /></a>
      <c:if test="${prevLink > 0 }">
        <a class="prev" href="javascript:goList('${prevLink }')"><img src="./../resources/img/btn_prev.gif" width="31" height="31" alt="이전" /></a>
      </c:if>
      <c:forEach var="i" items="${pageLinks }" varStatus="stat">
        <c:choose>
          <c:when test="${page_no eq i}">
            <strong><span>${i}</span></strong>
          </c:when>
          <c:otherwise>
            <a href="javascript:goList('${i}')"><span>${i}</span></a>
          </c:otherwise>
          </c:choose>
      </c:forEach>
      <c:if test="${nextLink > 0 }">
        <a class="next" href="javascript:goList('${nextLink}')"><img src="./../resources/img/btn_next.gif" width="31" height="31" alt="다음" /></a>
      </c:if>
        <a class="last" href="javascript:goList('${endPage}')"><img src="./../resources/img/btn_last.gif" width="31" height="31" alt="마지막페이지" /></a>
  </c:if>
</div> 
<div id="form-group">
  <form id="listForm" action="${self }" method="post">
    <p>
      <input type="hidden" name="page_no"  />
<%--       <input type="hidden" name="SEARCH_CONTS_TITLE" value="${SEARCH_CONTS_TITLE }" /> --%>
    </p>
  </form>
</div>
