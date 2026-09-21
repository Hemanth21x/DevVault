<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Login"); %>
<jsp:include page="header.jsp" />

<div class="dv-auth-split">
  <div class="dv-auth-pitch">
    <span class="dv-eyebrow"><i class="fa-solid fa-code-branch"></i> WELCOME BACK</span>
    <h1>Debug faster.<br>Together with the <span class="dv-gradient">community.</span></h1>
    <p class="dv-lead">Log back in to track your errors, review solutions, and keep building.</p>

    <div class="dv-feature">
      <div class="dv-feature-icon"><i class="fa-solid fa-bolt"></i></div>
      <div><h6>Fast answers</h6><p>Jump back into solutions you bookmarked</p></div>
    </div>
    <div class="dv-feature">
      <div class="dv-feature-icon"><i class="fa-solid fa-book"></i></div>
      <div><h6>Your debug journal</h6><p>Pick up your ongoing debugging notes</p></div>
    </div>
    <div class="dv-feature">
      <div class="dv-feature-icon"><i class="fa-solid fa-users"></i></div>
      <div><h6>Build community</h6><p>Connect, collaborate and grow together</p></div>
    </div>
  </div>

  <div class="dv-auth-card">
    <div class="dv-auth-icon"><i class="fa-solid fa-lock"></i></div>
    <h2>Welcome Back</h2>
    <p class="dv-auth-sub">Log in to your DevVault account</p>

    <c:if test="${not empty error}">
      <div class="dv-alert dv-alert-danger"><i class="fa-solid fa-circle-exclamation"></i> ${error}</div>
    </c:if>
    <c:if test="${param.registered == 'true'}">
      <div class="dv-alert dv-alert-success"><i class="fa-solid fa-circle-check"></i> Account created. Please log in.</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
      <div class="dv-field">
        <label>Email Address</label>
        <div class="dv-input">
          <i class="fa-regular fa-envelope"></i>
          <input type="email" name="email" placeholder="Enter your email" required>
        </div>
      </div>
      <div class="dv-field">
        <label>Password</label>
        <div class="dv-input">
          <i class="fa-solid fa-lock"></i>
          <input type="password" name="password" id="pw" placeholder="Enter your password" required>
          <i class="fa-regular fa-eye dv-eye" data-target="pw"></i>
        </div>
      </div>
      <button type="submit" class="dv-btn dv-btn-primary dv-btn-block">
        <i class="fa-solid fa-right-to-bracket"></i> Login
      </button>
    </form>

    <p class="dv-auth-foot">Don't have an account?
      <a href="${pageContext.request.contextPath}/register">Register here</a></p>
  </div>
</div>

<jsp:include page="footer.jsp" />
