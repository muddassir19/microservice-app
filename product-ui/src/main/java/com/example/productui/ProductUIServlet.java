package com.example.productui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//@WebServlet("/addProduct") // Optional if web.xml is present
public class ProductUIServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // form data
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        // Logging input
        System.out.println("Received from UI - Name: " + name + ", Price: " + price);

        String json = String.format("{\"name\":\"%s\",\"price\":%s}", name, price);
        System.out.println("Sending JSON to backend: " + json);


        // call backend service
        //String json = String.format("{\"name\":\"%s\",\"price\":%s}", name, price);
        URL url = new URL("http://35.154.113.145:8081/api/products"); // Use correct backend IP:port

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
         // Send request
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
            os.flush();
        }

        // Read and log backend response
        int responseCode = conn.getResponseCode();
        System.out.println("Backend Response Code: " + responseCode);

        if (responseCode >= 200 && responseCode < 300) {
            System.out.println("Product added successfully via backend API.");
        } else {
            System.out.println("Failed to add product. Error response:");

            try (BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(conn.getErrorStream()))) {
                String line;
                while ((line = errorReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }

        conn.disconnect();

        // Redirect to product list page


        response.sendRedirect("jsp/list.jsp");
    }
}
// @WebServlet("/addProduct")
// public class ProductUIServlet extends HttpServlet {
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         String name = request.getParameter("name");
//         String price = request.getParameter("price");

//         String json = String.format("{\"name\":\"%s\",\"price\":%s}", name, price);

//         URL url = new URL("http://13.203.104.52:8080/api/products");
//         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//         conn.setDoOutput(true);
//         conn.setRequestMethod("POST");
//         conn.setRequestProperty("Content-Type", "application/json");

//         try (OutputStream os = conn.getOutputStream()) {
//             os.write(json.getBytes());
//         }

//         response.sendRedirect("jsp/list.jsp");
//     }
// }