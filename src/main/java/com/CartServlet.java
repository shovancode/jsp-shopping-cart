package com.example;   //Declares the package name. Helps organize classes and avoid naming conflicts.
//Imports required libraries:
import jakarta.servlet.*; //Core servlet classes.
import jakarta.servlet.http.*;//HTTP-specific servlet classes like HttpServlet, HttpSession
import java.io.IOException;//Handles input/output errors.
import java.util.*;//Utilities like List, ArrayList.

public class CartServlet extends HttpServlet  {  //Defines a class named CartServlet.Extends HttpServlet, so it can handle HTTP requests (GET, POST, etc.).

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) //doPost Method (Handles form submissions)
            throws ServletException, IOException //Called when the client sends a POST request.
            //request → contains client data.
            //response → used to send data back to client.
            { 

        String item = request.getParameter("item");//Retrieves value of "item" parameter from request.

        HttpSession session = request.getSession();//Creates or retrieves current user session.

      
        List<String> cart = (List<String>) session.getAttribute("cart");//Gets cart object from session and casts to List.


        if (cart == null) { //Checks if cart is not already created.
            cart = new ArrayList<>();//Creates a new empty ArrayList.
        }

       
        if (item != null && !item.trim().isEmpty()) { //Checks if item is valid (not null or empty).
            cart.add(item);//Adds item to cart list.
        }

     
        session.setAttribute("cart", cart); //Stores updated cart back into session.


        response.sendRedirect("cart");  //Redirects client to "cart" URL (triggers doGet).
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  //Handles GET request (display cart).
            throws ServletException, IOException {  //Declares possible exceptions.

        HttpSession session = request.getSession();//Retrieves existing session.

        List<String> cart = (List<String>) session.getAttribute("cart"); //Gets cart from session.


        request.setAttribute("cartItems", cart); //Sets cart as request attribute to send to JSP.


        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp"); //Creates dispatcher for cart.jsp.
        rd.forward(request, response); //Forwards request and response to JSP for display.
    }
}
