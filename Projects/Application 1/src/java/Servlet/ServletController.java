/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAOS.BookDAOS;
import DAOS.CategoryDAOS;
import DAOS.Login;
import DTOS.Book;
import DTOS.Category;
import DTOS.User;
import Ultils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author megap
 */
@WebServlet(name = "ServletController", urlPatterns = {"/ServletController"})
public class ServletController extends HttpServlet {

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
            String action = request.getParameter("action");
            String loginForm = "Login.jsp";
            String indexPage = "landingPage.jsp";
            String listPage = "listPage.jsp";
            String formPage = "form.jsp";
            String errorPage = "error.jsp";
            String username="";
            BookDAOS daos = new BookDAOS();
            CategoryDAOS categorydaos = new CategoryDAOS();
            
            ArrayList<Book> bookList = daos.getAllBooks();
            ArrayList <Category> categoryList = categorydaos.getAllCategories();
            

            try{
                if (action.equals("Login")){ 
                    
                    Login login = new Login();
                    username = (String)request.getParameter("Username");
                    String password = (String)request.getParameter("Password");
                    User user = new User(username, " ", password);
                    
                    if (login.Login(user)){
                        HttpSession session  = request.getSession();
                        session.setAttribute("username", username);
                        RequestDispatcher rd = request.getRequestDispatcher(indexPage);
                        rd.forward(request, response);
                    }
                    else {
                        request.setAttribute("loginAlert", "Wrong username or password. Please try again");
                        RequestDispatcher rd = request.getRequestDispatcher(loginForm);  
                        rd.forward(request, response);
                    }
                    
                    
                    
                } else if(action.equals("register")){
                    
                    String user = (String)request.getParameter("username");
                    String name = (String)request.getParameter("name");
                    String password = (String)request.getParameter("password");
                    String repeatPass = (String)request.getParameter("repeatPassword");
                    Login register = new Login();
                    
                    if(Validation.checkUsername(user) && Validation.checkPassword(password) && !name.isEmpty() && password.equals(repeatPass)){
                        User newUser = new User(user, name, password);
                        if(register.addUser(newUser)){
                            RequestDispatcher rd = request.getRequestDispatcher(loginForm);
                            rd.forward(request, response);
                        }
                        else{
                            request.setAttribute("message", "We have trouble registering you. Please try again later");
                            RequestDispatcher rd = request.getRequestDispatcher("idex.jsp");
                            rd.forward(request, response);
                        }                        
                    }                   
                    else{
                        if(!Validation.checkUsername(user)){
                            if(register.searchUser(user)){
                                request.setAttribute("usernameAlert", "Username already existed");
                            }
                            else{
                                request.setAttribute("usernameAlert", "Username must be at least 6 characters long");
                            }
                        }
                        if (!Validation.checkPassword(password)){
                            request.setAttribute("passAlert", "Password must be at least 9 characters long");
                        }
                        if(name.isEmpty()){
                            request.setAttribute("nameAlert", "Name must not be empty");
                        }
                        if(!password.equals(repeatPass))
                            request.setAttribute("repeatPassAlert", "The password did not match");
                        RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                        rd.forward(request, response);
                    }
                    
                    
                    
                }else if(action.equals("booklist")){  
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        request.setAttribute("categoryList", categoryList);
                        request.setAttribute("categoryName", null);
                        RequestDispatcher rd = request.getRequestDispatcher(listPage);
                        rd.forward(request, response);
                    }
                    else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                    
                }else if(action.equals("categoryView")){
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        String categoryName = request.getParameter("category");
                        ArrayList bookByCategory = daos.getBookByCategory(categoryName);
                        request.setAttribute("categoryList", categoryList);
                        request.setAttribute("categoryName", bookByCategory);
                        RequestDispatcher rd = request.getRequestDispatcher(listPage);
                        rd.forward(request, response);
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                
                } else if(action.equals("addbook")){      
                    HttpSession session = request.getSession(false);
                    if(session != null){
                        Book input = new Book("", "", "", "", "", "", true);  
                    
                        request.setAttribute("Category", categoryList);
                        request.setAttribute("book", input);
                        request.setAttribute("action", "Add");
                        request.setAttribute("message", "Add a book's information");
                        RequestDispatcher rd = request.getRequestDispatcher(formPage);
                        rd.forward(request, response);
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                } else if(action.equals("Add") || action.equals("Update")){
                    HttpSession session = request.getSession(false);
                    if(session != null){
                        String bookID = request.getParameter("bookID");
                        String bookName = request.getParameter("bookName");
                        String bookDescription = request.getParameter("bookDescription");
                        String bookPublishingDate = request.getParameter("bookPublishYear");
                        String bookAuthor = request.getParameter("bookAuthor");
                        String bookCategory = "";
                        String pattern = "[0-9]+";
                        boolean bookStatus;
                        String reStatus = request.getParameter("status");
                        String reCategory = request.getParameter("Category");
                        boolean status = false;
                        
                        if (reStatus.equals("false"))
                            bookStatus = false;
                        else
                            bookStatus = true;
                        
                        for (Category category : categoryList) {
                            if(reCategory.equals(category.getCategoryName()))
                                bookCategory = category.getCategoryID();
                        }
                    
                        if(daos.searchBook(bookID) && action.equals("Add")){
                            request.setAttribute("idAlert", "Book ID had already existed");
                            status = true;
                        }
                        if(bookID.length()==0){
                            request.setAttribute("idAlert", "Book ID cannot be empty");
                            status = true;
                        }
                        if(bookName.length()==0){
                            request.setAttribute("nameAlert","Book name cannot be empty");
                            status = true;
                        }
                        if(bookDescription.length()==0){
                            request.setAttribute("desAlert", "Description cannot be empty");
                            status = true;
                        }
                        if(bookAuthor.length()==0){
                            request.setAttribute("authorAlert", "Author cannot be empty");
                            status = true;
                        }
                        if(bookCategory.length()==0){
                            request.setAttribute("categoryAlert", "Category cannot be empty");
                            status = true;
                        }
                        if(bookPublishingDate.length()==0){
                            request.setAttribute("publishDateAlert", "Publishing year cannot be empty");
                            status = true;
                        }
                        if(!bookPublishingDate.matches(pattern)){
                            status = true;
                            request.setAttribute("publishDateAlert", "Publishing year must be a number");
                        }else {
                            if(Integer.parseInt(bookPublishingDate)>2021){
                                status = true;
                                request.setAttribute("publishDateAlert", "Publishing year must be smaller or equal to 2021");
                            }
                        }
                        
                        Book input = new Book(bookID, bookName, bookAuthor, bookCategory, bookPublishingDate, bookDescription, bookStatus);
                        if(status){
                            RequestDispatcher rd = request.getRequestDispatcher(formPage);
                            request.setAttribute("message", "Add a book's information");
                            request.setAttribute("Category", categoryList);
                            request.setAttribute("book", input);
                            
                            if(action.equals("Add")){
                                request.setAttribute("action", "Add");
                                rd.forward(request, response);
                            } else{
                                request.setAttribute("action", "Update");
                                rd.forward(request, response);
                            }
                        } else if(action.equals("Add")){
                        
                            if(daos.addNewBook(input)){
                                RequestDispatcher rd = request.getRequestDispatcher(indexPage);
                                rd.forward(request, response);
                            }                        
                            else{
                                response.sendRedirect(errorPage);
                            }
                        } else if(action.equals("Update")){
                            if(daos.updateBook(input)){
                                RequestDispatcher rd = request.getRequestDispatcher(indexPage);
                                rd.forward(request, response);
                            }
                            else{
                                response.sendRedirect(errorPage);
                            }
                        }
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                } else if(action.equals("updatebook")){
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        Book input = null;
                        String bookID = request.getParameter("bookID");
                        input = daos.getBook(bookID);
                        
                        RequestDispatcher rd = request.getRequestDispatcher(formPage);
                        request.setAttribute("action", "Update");
                        request.setAttribute("message", "Update a book's information");
                        request.setAttribute("book", input);
                        request.setAttribute("Category", categoryList);
                       
                        rd.forward(request, response);
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                }else if (action.equals("deletebook")){
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        String bookID = request.getParameter("bookID");
                        request.setAttribute("username", username);
                        RequestDispatcher rd;
                    
                        if(daos.deleteBook(bookID)){
                             rd = request.getRequestDispatcher(indexPage);
                        }                    
                        else{
                            rd = request.getRequestDispatcher(errorPage);
                        }
                        rd.forward(request, response);
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                } else if(action.equals("addcategory")){
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        response.sendRedirect("Category.jsp");
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                } else if(action.equals("addNewCategory")){
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        String categoryID = (String)request.getParameter("categoryID");
                        String categoryName = (String) request.getParameter("categoryName");
                        String categoryDescription = (String)request.getParameter("categoryDescription");
                        boolean status = false;
                    
                        if(categoryID.isEmpty() || categoryID.equals("null")){
                            request.setAttribute("IDAlert", "The category's ID must not be empty");
                            categoryID = "";
                            status = true;
                        }
                        for (Category category : categoryList) {
                            if(categoryID.equals(category.getCategoryID()))
                            {
                                request.setAttribute("IDAlert", "The category's ID already existed");
                                status = true;
                            }
                        }
                        if(categoryName.isEmpty() || categoryName.equals("null")){
                            request.setAttribute("nameAlert", "The category's name must not be empty");
                            categoryName="";
                            status = true;
                        }
                        if(categoryDescription.isEmpty() || categoryDescription.equals(("null"))){
                            request.setAttribute("descriptionAlert", "The category's description must not be empty");
                            categoryDescription="";
                            status = true;
                        }
                        if(status){
                            RequestDispatcher rd = request.getRequestDispatcher("Category.jsp");
                            request.setAttribute("categoryID", categoryID);
                            request.setAttribute("categoryName", categoryName);
                            request.setAttribute("categoryDescription", categoryDescription);
                            rd.forward(request, response);
                        }
                        else{
                            Category input = new Category(categoryID, categoryName, categoryDescription);
                            if(categorydaos.addNewCategory(input)){
                                response.sendRedirect(indexPage);
                            }
                            else{
                                response.sendRedirect(errorPage);
                            }
                        }
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                }else if(action.equals("LoginForm")){
                    response.sendRedirect("Login.jsp");
                    
                    
                    
                }else if(action.equals("RegisterForm")){
                    response.sendRedirect("Register.jsp");
                    
                    
                    
                }else if(action.equals("HomePage")){
                    HttpSession session = request.getSession(false);
                    
                    if(session != null){
                        response.sendRedirect("landingPage.jsp");
                    } else{
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        request.setAttribute("LoginAlert", "Please login to continue");
                        rd.forward(request, response);
                    }
                    
                    
                    
                }else if(action.equals("LogOut")){
                    request.setAttribute("LoginAlert", "Please login to continue");
                    HttpSession session = request.getSession();
                    session.invalidate();
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
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
