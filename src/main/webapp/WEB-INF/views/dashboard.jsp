<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<% request.setAttribute("pageTitle", "Dashboard"); %>
<jsp:include page="header.jsp" />

<div class="dv-page-head">
  <div>
    <h2>Welcome back, ${sessionScope.loggedInUser.name}</h2>
    <p class="dv-muted">${sessionScope.loggedInUser.email}</p>
  </div>
  <a class="dv-btn dv-btn-primary" href="${pageContext.request.contextPath}/errors/new">
    <i class="fa-solid fa-plus"></i> Post an Error
  </a>
</div>

<section class="dv-stats">
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-bug"></i></div>
    <div><div class="dv-stat-value">${fn:length(myErrors)}</div><div class="dv-stat-label">My Errors</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-lightbulb"></i></div>
    <div><div class="dv-stat-value">${fn:length(mySolutions)}</div><div class="dv-stat-label">My Solutions</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-regular fa-bookmark"></i></div>
    <div><div class="dv-stat-value">${myBookmarkCount}</div><div class="dv-stat-label">Bookmarks</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-book"></i></div>
    <div><div class="dv-stat-value">${fn:length(myJournals)}</div><div class="dv-stat-label">Journal Entries</div></div>
  </div>
</section>

<div class="dv-grid-2">
  <div>
    <div class="dv-section-head"><h3>My Errors</h3></div>
    <div class="dv-card">
      <c:forEach var="e" items="${myErrors}">
        <a class="dv-error-row" href="${pageContext.request.contextPath}/errors/${e.id}">
          <div class="dv-error-icon"><i class="fa-solid fa-triangle-exclamation"></i></div>
          <div class="dv-error-main">
            <div class="dv-error-title">${e.title}</div>
            <div class="dv-badges"><span class="dv-badge">${e.technologyName}</span></div>
          </div>
          <div class="dv-error-meta"><span><i class="fa-regular fa-comment"></i> ${e.solutionCount}</span></div>
        </a>
      </c:forEach>
      <c:if test="${empty myErrors}">
        <div class="dv-empty"><i class="fa-regular fa-folder-open"></i><p>You haven't posted any errors yet.</p></div>
      </c:if>
    </div>
  </div>

  <div>
    <div class="dv-section-head"><h3>My Solutions</h3></div>
    <div class="dv-card">
      <c:forEach var="s" items="${mySolutions}">
        <a class="dv-error-row" href="${pageContext.request.contextPath}/errors/${s.errorId}">
          <div class="dv-error-icon dv-icon-success"><i class="fa-solid fa-lightbulb"></i></div>
          <div class="dv-error-main">
            <div class="dv-error-snippet">${s.solutionText}</div>
            <c:if test="${s.accepted}"><span class="dv-badge dv-badge-success">Accepted</span></c:if>
          </div>
          <div class="dv-error-meta"><span>${s.score} pts</span></div>
        </a>
      </c:forEach>
      <c:if test="${empty mySolutions}">
        <div class="dv-empty"><i class="fa-regular fa-lightbulb"></i><p>No solutions submitted yet.</p></div>
      </c:if>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />
