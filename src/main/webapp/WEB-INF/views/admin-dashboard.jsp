<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Admin"); %>
<jsp:include page="header.jsp" />

<div class="dv-page-head"><div><h2>Admin Dashboard</h2><p class="dv-muted">Platform overview and user management.</p></div></div>

<section class="dv-stats">
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-users"></i></div>
    <div><div class="dv-stat-value">${totalUsers}</div><div class="dv-stat-label">Total Users</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-bug"></i></div>
    <div><div class="dv-stat-value">${totalErrors}</div><div class="dv-stat-label">Total Errors</div></div>
  </div>
  <div class="dv-stat">
    <div class="dv-stat-icon"><i class="fa-solid fa-lightbulb"></i></div>
    <div><div class="dv-stat-value">${totalSolutions}</div><div class="dv-stat-label">Total Solutions</div></div>
  </div>
</section>

<div class="dv-section-head"><h3>Errors by Technology</h3></div>
<div class="dv-card dv-card-pad">
  <c:forEach var="row" items="${techStats}">
    <div class="dv-bar-row">
      <span class="dv-bar-label">${row.name}</span>
      <div class="dv-bar"><div class="dv-bar-fill" style="width:${row.total * 12}%"></div></div>
      <span class="dv-bar-value">${row.total}</span>
    </div>
  </c:forEach>
</div>

<div class="dv-section-head"><h3>Users</h3></div>
<div class="dv-card">
  <table class="dv-table">
    <thead><tr><th>Name</th><th>Email</th><th>Role</th><th></th></tr></thead>
    <tbody>
      <c:forEach var="u" items="${users}">
        <tr>
          <td>${u.name}</td>
          <td class="dv-muted">${u.email}</td>
          <td>
            <c:choose>
              <c:when test="${u.roleId == 3}"><span class="dv-badge dv-badge-danger">ADMIN</span></c:when>
              <c:when test="${u.roleId == 2}"><span class="dv-badge dv-badge-alt">MODERATOR</span></c:when>
              <c:otherwise><span class="dv-badge">USER</span></c:otherwise>
            </c:choose>
          </td>
          <td>
            <form action="${pageContext.request.contextPath}/admin/users/${u.id}/role" method="post" class="dv-inline-form">
              <select name="roleId" class="dv-select dv-select-sm">
                <option value="1" ${u.roleId==1?'selected':''}>USER</option>
                <option value="2" ${u.roleId==2?'selected':''}>MODERATOR</option>
                <option value="3" ${u.roleId==3?'selected':''}>ADMIN</option>
              </select>
              <button class="dv-btn dv-btn-ghost dv-btn-sm">Update</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="footer.jsp" />
