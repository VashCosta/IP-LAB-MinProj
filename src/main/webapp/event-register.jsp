<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.tnsymposium.Event" %>
<%
    Event event = (Event) request.getAttribute("event");
    if (event == null) { response.sendRedirect("events"); return; }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register • <%= event.getEventName() %></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body class="page-bg">
<header class="topbar">
    <div class="brand"><div class="brand-icon">TN</div><div class="brand-text-group"><span class="brand-main">VAR <span>CET</span></span><span class="brand-sub">Virudhunagar • Event Portal</span></div></div>
    <nav><a href="dashboard.jsp">Dashboard</a><a href="events">Events</a><a href="myRegistrations">My Registrations</a><a href="logout" class="nav-btn">Logout</a></nav>
</header>
<main class="registration-shell">
<section class="selected-event">
    <div class="college-crest-badge"><span class="crest-dot"></span> VAR CET • VIRUDHUNAGAR</div>
    <span class="badge"><%= event.getCategory() %></span>
    <h1><%= event.getEventName() %></h1>
    <p><%= event.getDescription() %></p>
    <div class="selected-meta">
        <span>Date: <b><%= event.getEventDate() %></b></span>
        <span>Time: <b><%= event.getStartTime() %></b></span>
        <span>Venue: <b><%= event.getVenue() %>, <%= event.getCity() %></b></span>
        <span>Fee: <b><%= event.getFee() == 0 ? "FREE" : ("₹" + String.format("%.0f", event.getFee())) %></b></span>
    </div>
    <div class="seat-meter"><div style="width:<%= Math.min(100, event.getAvailableSeats()) %>%;"></div></div>
    <small>🔥 <%= event.getAvailableSeats() %> seats currently available</small>
</section>
<form action="submit-registration" method="post" class="form-card registration-card">
    <input type="hidden" name="eventId" value="<%= event.getId() %>">
    <div class="eyebrow">ONLINE EVENT REGISTRATION</div>
    <h2>Confirm Event Pass</h2>
    <div class="form-grid two">
        <div class="field"><label>Verified Participant Name</label><input value="<%= session.getAttribute("userName") != null ? session.getAttribute("userName") : "" %>" readonly></div>
        <div class="field"><label>Registered Email</label><input value="<%= session.getAttribute("userEmail") != null ? session.getAttribute("userEmail") : "" %>" readonly></div>
        <div class="field full"><label>College / Institution</label><input value="<%= session.getAttribute("userCollege") != null ? session.getAttribute("userCollege") : "" %>" readonly></div>
        <div class="field"><label>Participation Type *</label><select name="participationType" required><option value="Individual">Individual</option><option value="Team">Team</option></select></div>
        <div class="field"><label>Team Name</label><input name="teamName" maxlength="100" placeholder="If team entry"></div>
    </div>
    <button type="submit" class="btn primary full-btn">Confirm & Generate Pass</button>
    <p class="form-note">A unique TN26 registration code will be created and one seat will be reserved in MySQL.</p>
</form>
</main>
<footer class="footer">VAR College of Engineering and Technology, Virudhunagar • Online Event Registration System</footer>
</body>
</html>
