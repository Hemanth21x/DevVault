<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${empty pageTitle ? 'DevVault' : pageTitle} | DevVault</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/devvault.css">
</head>
<body>

<nav class="dv-navbar">
  <div class="dv-nav-inner">
    <a class="dv-brand" href="${pageContext.request.contextPath}/">
      <span class="dv-brand-mark">&lt;/&gt;</span> Dev<span class="dv-brand-accent">Vault</span>
    </a>

    <div class="dv-nav-links">
      <a href="${pageContext.request.contextPath}/">Home</a>
      <a href="${pageContext.request.contextPath}/errors">Errors</a>
      <c:if test="${not empty sessionScope.loggedInUser}">
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/journal">Journal</a>
        <a href="${pageContext.request.contextPath}/bookmarks">Bookmarks</a>
        <c:if test="${sessionScope.userRoleId == 3}">
          <a href="${pageContext.request.contextPath}/admin">Admin</a>
        </c:if>
      </c:if>
    </div>

    <div class="dv-nav-actions">
      <form class="dv-search" action="${pageContext.request.contextPath}/errors" method="get">
        <i class="fa-solid fa-magnifying-glass"></i>
        <input type="text" name="q" placeholder="Search errors..." value="${q}">
      </form>

      <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
          <a class="dv-bell" href="${pageContext.request.contextPath}/notifications">
            <i class="fa-regular fa-bell"></i>
          </a>
          <div class="dv-avatar" title="${sessionScope.loggedInUser.name}">
            ${fn:toUpperCase(fn:substring(sessionScope.loggedInUser.name,0,1))}
          </div>
          <a class="dv-btn dv-btn-ghost" href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:when>
        <c:otherwise>
          <a class="dv-btn dv-btn-ghost" href="${pageContext.request.contextPath}/login">Login</a>
          <a class="dv-btn dv-btn-primary" href="${pageContext.request.contextPath}/register">Sign up</a>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</nav>

<main class="dv-main">
