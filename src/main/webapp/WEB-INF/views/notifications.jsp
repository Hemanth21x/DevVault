<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Notifications"); %>
<jsp:include page="header.jsp" />

<div class="dv-page-head"><div><h2>Notifications</h2></div></div>

<div class="dv-card">
  <c:forEach var="n" items="${notifications}">
    <div class="dv-error-row ${n.read ? '' : 'dv-unread'}">
      <div class="dv-error-icon"><i class="fa-regular fa-bell"></i></div>
      <div class="dv-error-main"><div class="dv-error-snippet">${n.message}</div></div>
    </div>
  </c:forEach>
  <c:if test="${empty notifications}">
    <div class="dv-empty"><i class="fa-regular fa-bell-slash"></i><p>Nothing here yet.</p></div>
  </c:if>
</div>

<jsp:include page="footer.jsp" />
