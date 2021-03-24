/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOS.SupplierDAO;
import DAOS.laptopDaos;
import DTO.Laptop;
import DTO.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author megap
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String laptopID = request.getParameter("laptopID");
            String laptopName = request.getParameter("laptopName");
            String technicalInfo = request.getParameter("technicalInfo");
            String manufacturingYear = request.getParameter("manufacturingYear");
            String producer = request.getParameter("producer");
            String Formstatus = request.getParameter("status");
            boolean status;
            String supplierID = request.getParameter("supplierID");
            boolean formStatus=false;
            
            laptopDaos dao = new laptopDaos();
            
            if(laptopName.isEmpty()){
                request.setAttribute("laptopNameAlert", "The name cannot be empty");
                formStatus = true;
            }
            if(technicalInfo.isEmpty()){
                request.setAttribute("technicalInfo", "The info cannot be empty");
                formStatus = true;
            }
            if(manufacturingYear.isEmpty()){
                request.setAttribute("manufacturingAlert", "The manufacturing year cannot be empty");
                formStatus = true;
            }
            if(producer.isEmpty()){
                request.setAttribute("producerAlert", "The producer cannot be null");
                formStatus = true;
            }
            if(Formstatus.equals("true"))
                status=true;
            else
                status=false;
            SupplierDAO daos = new SupplierDAO();
            Supplier suppliers = null;
            
            for (Supplier supplier : daos.getAllSupplier()) {
                if(supplier.getSupplierID().equals(supplierID)){
                    suppliers=supplier;
                }
            }
            Laptop input = new Laptop(laptopID, laptopName, technicalInfo, manufacturingYear, producer, status, suppliers);
            request.setAttribute("laptop", input);
            if(formStatus){
                request.setAttribute("readonly", "readonly");
                request.setAttribute("message", "Update a laptop");
                request.setAttribute("action", "Update");
                request.getRequestDispatcher("FormPage.jsp").forward(request, response);
                request.getRequestDispatcher("Homepage.jsp").forward(request, response);
            } else{
                log(String.valueOf(status));
                dao.updateLaptop(input);
                request.getRequestDispatcher("LoadListController").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
