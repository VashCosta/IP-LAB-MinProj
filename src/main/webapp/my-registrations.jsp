<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tnsymposium.Registration" %>
<%
    List<Registration> registrations = (List<Registration>) request.getAttribute("registrations");
    if (registrations == null) { response.sendRedirect("myRegistrations"); return; }
%>
<!DOCTYPE html>
<html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>My Registrations</title><link rel="stylesheet" href="css/style.css"></head>
<body class="page-bg">
<header class="topbar"><div class="brand"><div class="brand-icon">TN</div><div class="brand-text-group"><span class="brand-main">VAR <span>CET</span></span><span class="brand-sub">Virudhunagar • Event Portal</span></div></div><nav><a href="dashboard.jsp">Dashboard</a><a href="events">Events</a><a href="myRegistrations" class="active">My Registrations</a><a href="logout" class="nav-btn">Logout</a></nav></header>
<main class="listing-shell">
<section class="listing-head"><div><div class="college-crest-badge"><span class="crest-dot"></span> PARTICIPATION TRACKER</div><h1>My Registrations</h1><p>Your registration records are loaded dynamically from MySQL.</p></div><div class="event-count"><span><%= registrations.size() %></span><small>Registrations</small></div></section>
<% if ("registered".equals(request.getParameter("success"))) { %><div class="alert success">Registration completed successfully.</div><% } %>
<% if ("cancelled".equals(request.getParameter("success"))) { %><div class="alert success">Registration cancelled successfully.</div><% } %>
<div class="registration-list">
<% for (Registration r : registrations) { %>
<article class="registration-row"><div class="registration-main"><div class="registration-heading"><span class="badge"><%= r.getCategory() %></span><span class="registration-status">REGISTERED</span></div><h2><%= r.getEventName() %></h2><p><%= r.getEventDate() %> • <%= r.getStartTime() %> • <%= r.getVenue() %>, <%= r.getCity() %></p><div class="registration-chips"><span>CODE: <b><%= r.getRegistrationCode() %></b></span><span>TYPE: <b><%= r.getParticipationType() %></b></span><% if (r.getTeamName() != null) { %><span>TEAM: <b><%= r.getTeamName() %></b></span><% } %></div><small>Registered: <%= r.getRegisteredAt() %></small></div><form action="cancel-registration" method="post" onsubmit="return confirm('Cancel this registration?');"><input type="hidden" name="registrationId" value="<%= r.getId() %>"><button class="btn danger" type="submit">Cancel</button></form></article>
<% } %>
</div></main><footer class="footer">VAR College of Engineering and Technology, Virudhunagar • Online Event Registration System</footer></body></html>
