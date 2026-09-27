<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String loggedInUser = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TN TechSympo 2026 • Online Event Registration</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="topbar">
    <div class="brand">
        <div class="brand-icon">TN</div>
        <div class="brand-text-group">
            <span class="brand-main">VAR <span>CET</span></span>
            <span class="brand-sub">Virudhunagar • Event Portal</span>
        </div>
    </div>
    <nav>
        <a href="index.jsp" class="active">Home</a>
        <a href="events">Events</a>
        <% if (loggedInUser != null) { %>
            <a href="dashboard.jsp">Dashboard</a>
            <a href="myRegistrations">My Registrations</a>
            <a href="logout" class="nav-btn">Logout (<%= loggedInUser.split(" ")[0] %>)</a>
        <% } else { %>
            <a href="login.jsp" class="nav-btn">Login</a>
        <% } %>
    </nav>
</header>
<main>
<section class="hero-center-wrapper">
    <div class="watermark-bg">TN TECHSYMP<span class="watermark-sub">2026</span></div>
    <div class="college-crest-badge"><span class="crest-dot"></span> STATE-LEVEL INTER-COLLEGE TECHNICAL SYMPOSIUM</div>
    <h1 class="hero-title">Online Event<br><span>Registration</span></h1>
    <p class="hero-copy">Discover technical challenges, register in seconds and manage your symposium participation from one dynamic portal.</p>
    <div class="hero-actions">
        <a href="events" class="btn primary">Explore Events →</a>
        <a href="register.jsp" class="btn outline">Create Account</a>
    </div>
    <div class="hero-stats">
        <div><strong>05</strong><span>Active Events</span></div>
        <div><strong>350+</strong><span>Seats</span></div>
        <div><strong>TN</strong><span>Open to Colleges</span></div>
    </div>
</section>
<section class="feature-strip">
    <article><span>01</span><h3>Paper Presentation</h3><p>Present your original technical research and ideas.</p></article>
    <article><span>02</span><h3>Code Surge</h3><p>Take on timed coding and problem-solving challenges.</p></article>
    <article><span>03</span><h3>AI Arena</h3><p>Test your knowledge across AI and data science.</p></article>
</section>
</main>
<footer class="footer">VAR College of Engineering and Technology, Virudhunagar • Online Event Registration System</footer>
</body>
</html>
