<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Login • TN TechSympo 2026</title><link rel="stylesheet" href="css/style.css"></head>
<body class="page-bg">
<header class="topbar"><div class="brand"><div class="brand-icon">TN</div><div class="brand-text-group"><span class="brand-main">VAR <span>CET</span></span><span class="brand-sub">Virudhunagar • Event Portal</span></div></div><nav><a href="index.jsp">Home</a><a href="events">Events</a><a href="register.jsp" class="nav-btn">Register</a></nav></header>
<main class="form-shell"><section class="form-intro"><div class="college-crest-badge"><span class="crest-dot"></span> PARTICIPANT PORTAL</div><h1>Welcome back.</h1><p>Login to explore events and manage your symposium registrations.</p></section>
<% if ("invalid".equals(request.getParameter("error"))) { %><div class="alert error">Invalid email or password.</div><% } %><% if ("registered".equals(request.getParameter("success"))) { %><div class="alert success">Account created successfully. Login now.</div><% } %>
<form action="login" method="post" class="form-card"><div class="field"><label>Email</label><input type="email" name="email" required></div><div class="field"><label>Password</label><input type="password" name="password" required></div><button class="btn primary full-btn" type="submit">Login to Portal</button><p class="form-note">New participant? <a href="register.jsp">Create an account</a></p></form></main>
<footer class="footer">TN TechSympo 2026 • Participant Login</footer></body></html>
