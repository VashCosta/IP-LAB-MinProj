# Online Event Registration and Management System

**Project:** TN TechSympo 2026  
**Stack:** NetBeans, Java Servlets, JSP, JDBC, MySQL, Apache Tomcat 10.1

The full dynamic application is under `src/main/` and runs locally with Tomcat + MySQL. The `docs/` directory is a static GitHub Pages preview; GitHub Pages does not execute JSP/Servlets or connect to your local MySQL database.

## Local setup
1. Run `database.sql` in MySQL Workbench.
2. Set the `MYSQL_PASSWORD` environment variable to the MySQL password used by the app.
3. Open the project in NetBeans and run it on Apache Tomcat 10.1.
4. Use `com.tnsymposium.DBTest` to verify JDBC.

## GitHub Pages
The repository contains a GitHub Actions Pages workflow. In **Settings → Pages**, select **GitHub Actions** as the source for the repository. Then the static preview is published at:

`https://vashcosta.github.io/IP-LAB-MinProj/`
