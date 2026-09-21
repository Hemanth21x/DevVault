<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<% request.setAttribute("pageTitle", "Errors"); %>
<jsp:include page="header.jsp" />

<div class="dv-page-head">
  <div>
    <h2>Errors</h2>
    <p class="dv-muted">${fn:length(errors)} result(s)</p>
  </div>
  <c:if test="${not empty sessionScope.loggedInUser}">
    <a class="dv-btn dv-btn-primary" href="${pageContext.request.contextPath}/errors/new">
      <i class="fa-solid fa-plus"></i> Post an Error
    </a>
  </c:if>
</div>

<form class="dv-filters" action="${pageContext.request.contextPath}/errors" method="get">
  <div class="dv-input">
    <i class="fa-solid fa-magnifying-glass"></i>
    <input type="text" name="q" placeholder="Search..." value="${q}">
  </div>
  <select name="tech" class="dv-select">
    <option value="0">All technologies</option>
    <c:forEach var="t" items="${technologies}">
      <option value="${t.id}" ${selectedTech == t.id ? 'selected' : ''}>${t.name}</option>
    </c:forEach>
  </select>
  <select name="cat" class="dv-select">
    <option value="0">All categories</option>
    <c:forEach var="c" items="${categories}">
      <option value="${c.id}" ${selectedCat == c.id ? 'selected' : ''}>${c.name}</option>
    </c:forEach>
  </select>
  <button type="submit" class="dv-btn dv-btn-primary">Filter</button>
</form>

<div class="dv-card">
  <c:forEach var="e" items="${errors}">
    <a class="dv-error-row" href="${pageContext.request.contextPath}/errors/${e.id}">
      <div class="dv-error-icon"><i class="fa-solid fa-triangle-exclamation"></i></div>
      <div class="dv-error-main">
        <div class="dv-error-title">${e.title}</div>
        <div class="dv-error-snippet">${e.errorMessage}</div>
        <div class="dv-badges">
          <c:if test="${not empty e.technologyName}"><span class="dv-badge">${e.technologyName}</span></c:if>
          <c:if test="${not empty e.categoryName}"><span class="dv-badge dv-badge-alt">${e.categoryName}</span></c:if>
        </div>
      </div>
      <div class="dv-error-meta">
        <span><i class="fa-regular fa-comment"></i> ${e.solutionCount}</span>
        <span class="dv-muted">${e.postedByName}</span>
      </div>
    </a>
  </c:forEach>
  <c:if test="${empty errors}">
    <div class="dv-empty"><i class="fa-regular fa-face-frown"></i><p>No errors matched your search.</p></div>
  </c:if>
</div>

<jsp:include page="footer.jsp" />
