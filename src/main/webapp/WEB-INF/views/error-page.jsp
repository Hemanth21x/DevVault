<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Something went wrong"); %>
<jsp:include page="header.jsp" />

<div class="dv-empty dv-empty-lg">
  <i class="fa-solid fa-triangle-exclamation"></i>
  <h2>Something went wrong</h2>
  <p class="dv-muted">${message}</p>
  <a class="dv-btn dv-btn-primary" href="${pageContext.request.contextPath}/">Back to home</a>
</div>

<jsp:include page="footer.jsp" />
