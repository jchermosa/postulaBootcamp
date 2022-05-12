package com.roshka.proyectofinal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class LoginHandler extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get the user's name and password
        String name = req.getParameter("name");
        String passwd = req.getParameter("passwd");

        // Check the name and password for validity
        if (!allowUser(name, passwd)) {
            out.println("&lt;HTML&gt;&lt;HEAD&gt;&lt;TITLE&gt;Access Denied&lt;/TITLE&gt;&lt;/HEAD&gt;");
            out.println("&lt;BODY&gt;Your login and password are invalid.&lt;BR&gt;");
            out.println("You may want to &lt;A HREF=\"/login.html\"&gt;try again&lt;/A&gt;");
            out.println("&lt;/BODY&gt;&lt;/HTML&gt;");
        }
        else {
            // Valid login. Make a note in the session object.
            HttpSession session = req.getSession(true);
            session.putValue("logon.isDone", name);  // just a marker object

            // Try redirecting the client to the page he first tried to access
            try {
                String target = (String) session.getValue("login.target");
                if (target != null)
                    res.sendRedirect(target);
                return;
            }
            catch (Exception ignored) { }

            // Couldn't redirect to the target. Redirect to the site's home page.
            res.sendRedirect(req.getScheme() + "://" +
                    req.getServerName() + ":" + req.getServerPort());
        }
    }

    protected boolean allowUser(String user, String passwd) {
        return true;  // trust everyone
    }
}