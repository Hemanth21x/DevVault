<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Post an Error"); %>
<jsp:include page="header.jsp" />

<div class="dv-narrow">
  <h2>Post an Error</h2>
  <p class="dv-muted">Share an error so others can help &mdash; and so the next developer finds it faster.</p>

  <c:if test="${not empty error}">
    <div class="dv-alert dv-alert-danger"><i class="fa-solid fa-circle-exclamation"></i> ${error}</div>
  </c:if>

  <div class="dv-card dv-card-pad">
    <form action="${pageContext.request.contextPath}/errors/new" method="post">
      <div class="dv-field">
        <label>Title *</label>
        <div class="dv-input"><input type="text" name="title" placeholder="e.g. ClassNotFoundException" required></div>
      </div>
      <div class="dv-field">
        <label>Error Message *</label>
        <textarea name="errorMessage" rows="3" class="dv-textarea dv-mono" placeholder="Paste the full error/stack trace" required></textarea>
      </div>
      <div class="dv-field">
        <label>Description</label>
        <textarea name="description" rows="3" class="dv-textarea" placeholder="What does this error mean?"></textarea>
      </div>
      <div class="dv-field">
        <label>Cause</label>
        <textarea name="cause" rows="3" class="dv-textarea" placeholder="What usually triggers it?"></textarea>
      </div>
      <div class="dv-field-row">
        <div class="dv-field">
          <label>Technology</label>
          <select name="technologyId" class="dv-select" required>
            <c:forEach var="t" items="${technologies}"><option value="${t.id}">${t.name}</option></c:forEach>
          </select>
        </div>
        <div class="dv-field">
          <label>Category</label>
          <select name="categoryId" class="dv-select" required>
            <c:forEach var="c" items="${categories}"><option value="${c.id}">${c.name}</option></c:forEach>
          </select>
        </div>
      </div>
      <button type="submit" class="dv-btn dv-btn-primary"><i class="fa-solid fa-plus"></i> Publish Error</button>
    </form>
  </div>
</div>

<jsp:include page="footer.jsp" />
