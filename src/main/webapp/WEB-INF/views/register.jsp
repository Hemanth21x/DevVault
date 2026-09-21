<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% request.setAttribute("pageTitle", "Register"); %>
<jsp:include page="header.jsp" />

<div class="dv-auth-split">
  <div class="dv-auth-pitch">
    <span class="dv-eyebrow"><i class="fa-solid fa-code-branch"></i> JOIN DEVELOPER COMMUNITY</span>
    <h1>Share. Solve.<br>Grow <span class="dv-gradient">Together.</span></h1>
    <p class="dv-lead">DevVault is a community-driven platform where developers share errors, discover solutions, and grow together.</p>

    <div class="dv-feature">
      <div class="dv-feature-icon"><i class="fa-solid fa-code"></i></div>
      <div><h6>Share errors</h6><p>Post errors and get help from experienced developers</p></div>
    </div>
    <div class="dv-feature">
      <div class="dv-feature-icon"><i class="fa-solid fa-lightbulb"></i></div>
      <div><h6>Find solutions</h6><p>Discover solutions from the community</p></div>
    </div>
    <div class="dv-feature">
      <div class="dv-feature-icon"><i class="fa-solid fa-users"></i></div>
      <div><h6>Build community</h6><p>Connect, collaborate and grow together</p></div>
    </div>
  </div>

  <div class="dv-auth-card">
    <div class="dv-auth-icon"><i class="fa-solid fa-user-plus"></i></div>
    <h2>Create Your Account</h2>
    <p class="dv-auth-sub">Start your journey with DevVault</p>

    <c:if test="${not empty error}">
      <div class="dv-alert dv-alert-danger"><i class="fa-solid fa-circle-exclamation"></i> ${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">
      <div class="dv-field">
        <label>Full Name</label>
        <div class="dv-input">
          <i class="fa-regular fa-user"></i>
          <input type="text" name="name" placeholder="Enter your full name" required>
        </div>
      </div>
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
          <input type="password" name="password" id="pw1" placeholder="Enter your password" required minlength="6">
          <i class="fa-regular fa-eye dv-eye" data-target="pw1"></i>
        </div>
      </div>
      <div class="dv-field">
        <label>Confirm Password</label>
        <div class="dv-input">
          <i class="fa-solid fa-lock"></i>
          <input type="password" name="confirmPassword" id="pw2" placeholder="Confirm your password" required>
          <i class="fa-regular fa-eye dv-eye" data-target="pw2"></i>
        </div>
      </div>
      <button type="submit" class="dv-btn dv-btn-primary dv-btn-block">
        <i class="fa-solid fa-user-plus"></i> Create Account
      </button>
    </form>

    <p class="dv-auth-foot">Already have an account?
      <a href="${pageContext.request.contextPath}/login">Login here</a></p>
  </div>
</div>

<jsp:include page="footer.jsp" />
