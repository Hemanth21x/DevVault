<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<% request.setAttribute("pageTitle", "Error Details"); %>
<jsp:include page="header.jsp" />

<a class="dv-back" href="${pageContext.request.contextPath}/errors">
  <i class="fa-solid fa-arrow-left"></i> Back to errors
</a>

<div class="dv-card dv-card-pad">
  <div class="dv-detail-head">
    <h2>${err.title}</h2>
    <c:if test="${not empty sessionScope.loggedInUser}">
      <button class="dv-btn dv-btn-ghost dv-bookmark" data-error="${err.id}">
        <i class="fa-regular fa-bookmark"></i> Bookmark
      </button>
    </c:if>
  </div>

  <div class="dv-badges">
    <c:if test="${not empty err.technologyName}"><span class="dv-badge">${err.technologyName}</span></c:if>
    <c:if test="${not empty err.categoryName}"><span class="dv-badge dv-badge-alt">${err.categoryName}</span></c:if>
    <span class="dv-muted">posted by ${err.postedByName}</span>
  </div>

  <h4 class="dv-sub">Error Message</h4>
  <pre class="dv-terminal">${err.errorMessage}</pre>

  <c:if test="${not empty err.description}">
    <h4 class="dv-sub">What is it?</h4>
    <p>${err.description}</p>
  </c:if>

  <c:if test="${not empty err.cause}">
    <h4 class="dv-sub">Common Causes</h4>
    <p>${err.cause}</p>
  </c:if>
</div>

<div class="dv-section-head">
  <h3>Solutions (${fn:length(solutions)})</h3>
</div>

<c:forEach var="s" items="${solutions}">
  <div class="dv-card dv-card-pad dv-solution ${s.accepted ? 'dv-solution-accepted' : ''}">
    <div class="dv-solution-head">
      <div class="dv-vote">
        <button class="dv-vote-btn" data-solution="${s.id}" data-type="UP"><i class="fa-solid fa-caret-up"></i></button>
        <span class="dv-vote-score" id="score-${s.id}">${s.score}</span>
        <button class="dv-vote-btn" data-solution="${s.id}" data-type="DOWN"><i class="fa-solid fa-caret-down"></i></button>
      </div>
      <div class="dv-solution-body">
        <c:if test="${s.accepted}">
          <span class="dv-badge dv-badge-success"><i class="fa-solid fa-check"></i> Accepted</span>
        </c:if>
        <p>${s.solutionText}</p>
        <c:if test="${not empty s.codeExample}">
          <pre class="dv-terminal dv-terminal-code">${s.codeExample}</pre>
        </c:if>
        <div class="dv-muted">by ${s.postedByName}</div>

        <c:if test="${sessionScope.userId == err.postedBy and not s.accepted}">
          <form action="${pageContext.request.contextPath}/errors/${err.id}/solutions/${s.id}/accept" method="post">
            <button class="dv-btn dv-btn-ghost dv-btn-sm"><i class="fa-solid fa-check"></i> Mark as accepted</button>
          </form>
        </c:if>
      </div>
    </div>
  </div>
</c:forEach>

<c:if test="${empty solutions}">
  <div class="dv-card dv-empty"><i class="fa-regular fa-lightbulb"></i><p>No solutions yet. Be the first!</p></div>
</c:if>

<c:choose>
  <c:when test="${not empty sessionScope.loggedInUser}">
    <div class="dv-card dv-card-pad">
      <h4 class="dv-sub">Your Solution</h4>
      <form action="${pageContext.request.contextPath}/errors/${err.id}/solutions" method="post">
        <div class="dv-field">
          <label>Explanation</label>
          <textarea name="solutionText" rows="4" class="dv-textarea" placeholder="Explain how to fix this..." required></textarea>
        </div>
        <div class="dv-field">
          <label>Code Example (optional)</label>
          <textarea name="codeExample" rows="4" class="dv-textarea dv-mono" placeholder="// code here"></textarea>
        </div>
        <button type="submit" class="dv-btn dv-btn-primary"><i class="fa-solid fa-paper-plane"></i> Submit Solution</button>
      </form>
    </div>
  </c:when>
  <c:otherwise>
    <div class="dv-card dv-empty">
      <p><a href="${pageContext.request.contextPath}/login">Log in</a> to post a solution.</p>
    </div>
  </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp" />
