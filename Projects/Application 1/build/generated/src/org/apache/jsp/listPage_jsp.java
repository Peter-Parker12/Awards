package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DTOS.Category;
import DTOS.Book;
import java.util.ArrayList;

public final class listPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Book List </title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Welcome to FPTU Library's book list</h1>\n");
      out.write("        <p>Please choose the category that you want to look</p>\n");
      out.write("        ");
 ArrayList<Category> categoryList = (ArrayList<Category>) request.getAttribute("categoryList"); 
        
      out.write("\n");
      out.write("        <form action=\"ServletController?action=categoryView\" method=\"POST\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td>\n");
      out.write("                        <select name=\"category\" id=\"\">\n");
      out.write("                            <option></option>\n");
      out.write("                            ");
 for (Category category : categoryList) { 
      out.write("\n");
      out.write("                                <option>");
      out.print( category.getCategoryName() );
      out.write("</option>\n");
      out.write("                            ");
 } 
      out.write("\n");
      out.write("                        </select>\n");
      out.write("                    </td>\n");
      out.write("                    <td><button type=\"submit\">Search</button></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>    \n");
      out.write("        <table border=\"1\">\n");
      out.write("            <tr>\n");
      out.write("                <td>Book ID</td>\n");
      out.write("                <td>Book Name</td>\n");
      out.write("                <td>Author</td>\n");
      out.write("                <td>Description</td>\n");
      out.write("                <td>Publishing Year</td>\n");
      out.write("                <td>Category</td>\n");
      out.write("                <td>Status</td>\n");
      out.write("            </tr>\n");
      out.write("            ");
 ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("categoryName");
            if(bookList != null){
                for (Book book : bookList) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print( book.getBookID() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getBookName() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getBookAuthor());
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getBookDescription());
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getBookPublishingDate());
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getBookCategory());
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.isBookStatus() );
      out.write("</td>\n");
      out.write("                <td><a href=\"ServletController?action=updatebook&bookID=");
      out.print( book.getBookID() );
      out.write("\">Edit</a></td>\n");
      out.write("                <td><a onclick=\"return confirmation()\" href=\"ServletController?action=deletebook&bookID=");
      out.print( book.getBookID() );
      out.write("\" >Delete</a></td>\n");
      out.write("            </tr>\n");
      out.write("            ");
 }
            }
      out.write("\n");
      out.write("        </table>\n");
      out.write("        \n");
      out.write("        <p><a href=\"landingPage.jsp\">Return to Homepage</a></p>\n");
      out.write("        <script>\n");
      out.write("            function confirmation(){\n");
      out.write("                var r = confirm(\"Do you really want to delete the book?\")\n");
      out.write("                return r;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
