<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Debug Journal"); %>
<jsp:include page="header.jsp" />

<div class="dv-page-head">
  <div>
    <h2>Debug Journal</h2>
    <p class="dv-muted">Your private log of problems solved and lessons learned.</p>
  </div>
  <button class="dv-btn dv-btn-primary" onclick="document.getElementById('jform').classList.toggle('dv-hidden')">
    <i class="fa-solid fa-plus"></i> New Entry
  </button>
</div>

<div class="dv-card dv-card-pad dv-hidden" id="jform">
  <form action="${pageContext.request.contextPath}/journal/new" method="post">
    <div class="dv-field-row">
      <div class="dv-field">
        <label>Project</label>
        <div class="dv-input"><input type="text" name="projectName" placeholder="e.g. DevVault"></div>
      </div>
      <div class="dv-field">
        <label>Time taken (minutes)</label>
        <div class="dv-input"><input type="number" name="timeTakenMinutes" value="0" min="0"></div>
      </div>
    </div>
    <div class="dv-field">
      <label>Problem *</label>
      <textarea name="problem" rows="2" class="dv-textarea" required></textarea>
    </div>
    <div class="dv-field">
      <label>Root Cause</label>
      <textarea name="rootCause" rows="2" class="dv-textarea"></textarea>
    </div>
    <div class="dv-field">
      <label>Solution</label>
      <textarea name="solution" rows="2" class="dv-textarea"></textarea>
    </div>
    <button type="submit" class="dv-btn dv-btn-primary">Save Entry</button>
  </form>
</div>

<c:forEach var="j" items="${journals}">
  <div class="dv-card dv-card-pad dv-journal">
    <div class="dv-journal-head">
      <div>
        <span class="dv-badge">${j.projectName}</span>
        <span class="dv-muted"><i class="fa-regular fa-clock"></i> ${j.timeTakenMinutes} min</span>
      </div>
      <form action="${pageContext.request.contextPath}/journal/${j.id}/delete" method="post">
        <button class="dv-icon-btn" title="Delete"><i class="fa-regular fa-trash-can"></i></button>
      </form>
    </div>
    <h4 class="dv-sub">Problem</h4>
    <p>${j.problem}</p>
    <c:if test="${not empty j.rootCause}">
      <h4 class="dv-sub">Root Cause</h4><p>${j.rootCause}</p>
    </c:if>
    <c:if test="${not empty j.solution}">
      <h4 class="dv-sub">Solution</h4><p>${j.solution}</p>
    </c:if>
  </div>
</c:forEach>

<c:if test="${empty journals}">
  <div class="dv-card dv-empty"><i class="fa-regular fa-book"></i><p>No journal entries yet.</p></div>
</c:if>

<jsp:include page="footer.jsp" />
