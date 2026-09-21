<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Home"); %>
<jsp:include page="header.jsp" />

<section class="dv-hero">
  <span class="dv-eyebrow"><i class="fa-solid fa-bug"></i> DEVELOPER ERROR INTELLIGENCE</span>
  <h1>Find &amp; Fix<br><span class="dv-gradient">Developer Errors</span></h1>
  <p class="dv-lead">Search real errors, real fixes, from real developers.</p>

  <form class="dv-hero-search" action="${pageContext.request.contextPath}/errors" method="get">
    <i class="fa-solid fa-magnifying-glass"></i>
    <input type="text" name="q" placeholder="Search errors, exceptions, solutions...">
    <button type="submit" class="dv-btn dv-btn-primary">Search</button>
  </form>

  <div class="dv-chip-row">
    <c:forEach var="t" items="${technologies}" begin="0" end="6">
      <a class="dv-chip" href="${pageContext.request.contextPath}/errors?tech=${t.id}">${t.name}</a>
    </c:forEach>
  </div>
</section>

<section class="dv-stats">
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-bug"></i></div>
    <div><div class="dv-stat-value">${totalErrors}</div><div class="dv-stat-label">Errors</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-lightbulb"></i></div>
    <div><div class="dv-stat-value">${totalSolutions}</div><div class="dv-stat-label">Solutions</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-users"></i></div>
    <div><div class="dv-stat-value">${totalUsers}</div><div class="dv-stat-label">Developers</div></div>
  </div>
</section>

<section>
  <div class="dv-section-head">
    <h3>Recent Errors</h3>
    <a href="${pageContext.request.contextPath}/errors">View all <i class="fa-solid fa-arrow-right"></i></a>
  </div>

  <div class="dv-card">
    <c:forEach var="e" items="${recentErrors}">
      <a class="dv-error-row" href="${pageContext.request.contextPath}/errors/${e.id}">
        <div class="dv-error-icon"><i class="fa-solid fa-triangle-exclamation"></i></div>
        <div class="dv-error-main">
          <div class="dv-error-title">${e.title}</div>
          <div class="dv-badges">
            <c:if test="${not empty e.technologyName}"><span class="dv-badge">${e.technologyName}</span></c:if>
            <c:if test="${not empty e.categoryName}"><span class="dv-badge dv-badge-alt">${e.categoryName}</span></c:if>
          </div>
        </div>
        <div class="dv-error-meta">
          <span><i class="fa-regular fa-comment"></i> ${e.solutionCount}</span>
        </div>
      </a>
    </c:forEach>
    <c:if test="${empty recentErrors}">
      <div class="dv-empty"><i class="fa-regular fa-folder-open"></i><p>No errors posted yet.</p></div>
    </c:if>
  </div>
</section>

<jsp:include page="footer.jsp" />
