<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Bookmarks"); %>
<jsp:include page="header.jsp" />

<div class="dv-page-head"><div><h2>Bookmarks</h2><p class="dv-muted">Errors you saved for later.</p></div></div>

<div class="dv-card">
  <c:forEach var="b" items="${bookmarks}">
    <div class="dv-error-row">
      <div class="dv-error-icon"><i class="fa-solid fa-bookmark"></i></div>
      <div class="dv-error-main">
        <a class="dv-error-title" href="${pageContext.request.contextPath}/errors/${b.errorId}">${b.errorTitle}</a>
      </div>
      <form action="${pageContext.request.contextPath}/bookmarks/${b.id}/delete" method="post">
        <button class="dv-icon-btn" title="Remove"><i class="fa-regular fa-trash-can"></i></button>
      </form>
    </div>
  </c:forEach>
  <c:if test="${empty bookmarks}">
    <div class="dv-empty"><i class="fa-regular fa-bookmark"></i><p>No bookmarks yet.</p></div>
  </c:if>
</div>

<jsp:include page="footer.jsp" />
