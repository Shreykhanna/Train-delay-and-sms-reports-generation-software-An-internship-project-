    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Database_connection.Db_conn;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shrey
 */
public class Db_servlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String train_num=request.getParameter("train_number");
            String station_code=request.getParameter("station_code");
            String res_upto=request.getParameter("RESUPTO_dropdown");
            String source_date=request.getParameter("source_date_dropdown");
            String orderby_date=request.getParameter("orderby_date");
            String orderby_isl=request.getParameter("orderby_isl");
            String orderby_train=request.getParameter("orderby_train");
            String selected_train_delay=request.getParameter("delay_train");
            String particular_station_delay=request.getParameter("delay_stn");
            String selected_train_sms=request.getParameter("train_sms");
            String particular_station_sms=request.getParameter("particular_sms");
            System.out.println("Station code : "+station_code);
            System.out.println(source_date);
            HttpSession session=request.getSession(true);
  ////////////////////////////////////DELAY REPORT////////////////////////////////////////////////////////////////////  
           if(request.getParameter("selected_trains")!=null)
              {
                  RequestDispatcher rd=request.getRequestDispatcher("selected_trains.jsp");
                  rd.forward(request, response);
              }
              else if(request.getParameter("num_train")!=null)
              {
                  ArrayList<String> details=Db_conn.selected_trains(train_num,station_code,source_date,orderby_date,orderby_isl,orderby_train);
                  request.setAttribute("details",details);
                  RequestDispatcher rd=request.getRequestDispatcher("selected_trains.jsp");
                  rd.forward(request, response);
              }
              else if(request.getParameter("particular_stations")!=null)
             {   
                 RequestDispatcher rd=request.getRequestDispatcher("delay_particularstn.jsp");
                 rd.forward(request, response);
                 
             }
              else if(request.getParameter("code_station")!=null)
               {
                 ArrayList<String> particular_stations=Db_conn.particular_stations(station_code,train_num,source_date,orderby_date,orderby_isl,orderby_train );
                 request.setAttribute("particular_stations",particular_stations);
                 RequestDispatcher rd=request.getRequestDispatcher("delay_particularstn.jsp");
                 rd.forward(request, response);
               }
                
              else if(request.getParameter("delay_units")!=null)
              {
                  ArrayList<String> delay_unit=Db_conn.fetch_delay_unit();
                  request.setAttribute("delay_unit",delay_unit);
                  RequestDispatcher rd=request.getRequestDispatcher("delay_unit.jsp");
                  rd.forward(request, response);
              
              }
 /////////////////////////////////DELAY REPORT///////////////////////////////////////////////////////////////////////////////// 
 /////////////////////////SMS REPORT//////////////////////////////////////////////////////////////////////////////////////////
              
              else if(request.getParameter("selected_train")!=null)
              {
                  
                  RequestDispatcher rd=request.getRequestDispatcher("selected_train_sms.jsp");
                  rd.forward(request, response);
              }
              else if(request.getParameter("num_train_sms")!=null)
              {
                  System.out.println("Inside selectd train sms");
                  ArrayList<String> selected_train=Db_conn.selected_train_sms(train_num,station_code,source_date,orderby_date,orderby_isl,orderby_train);
                  request.setAttribute("selected_train", selected_train);
                  RequestDispatcher rd=request.getRequestDispatcher("selected_train_sms.jsp");
                  rd.forward(request, response);

              }
              else if(request.getParameter("particular_station")!=null)
              {
                  RequestDispatcher rd=request.getRequestDispatcher("particular_station_sms.jsp");
                  rd.forward(request, response);
              
              }
              else if(request.getParameter("particular_station_submit")!=null)
              {     
                  System.out.println("insdie partcular stsn submit");
                  ArrayList<String> Particular_station=Db_conn.particular_station_sms(station_code,train_num,source_date,orderby_date,orderby_isl,orderby_train);
                  request.setAttribute("Particular_station", Particular_station);
                  RequestDispatcher rd=request.getRequestDispatcher("particular_station_sms.jsp");
                  rd.forward(request, response);
              
              }

    ////////////////////////////////STATISTICS CONDITION////////////////////////////////////////////////////////////////////////        
              else if(request.getParameter("statistics_submit")!=null)
              {
                  
                  ArrayList<String> statistics=Db_conn.statistics(train_num,res_upto,source_date);
                  request.setAttribute("statistics",statistics);
                  RequestDispatcher rd=request.getRequestDispatcher("Statistics.jsp");
                  rd.forward(request, response);
              }
   //////////////////////////////////////////////REDIRECT///////////////////////////////////////////////////////////////////////        
              else if(train_num!=null && station_code!=null && "1".equals(particular_station_delay) )
              {
                  ArrayList<String> train_number=Db_conn.Train_number(station_code);
                  request.setAttribute("station_code",station_code); 
                  request.setAttribute("train_number",train_number);
                  RequestDispatcher rd=request.getRequestDispatcher("delay_particularstn.jsp");
                  rd.forward(request, response); 
              }
              else if(train_num!=null && station_code!=null && "2".equals(selected_train_delay) )
              {
                  ArrayList<String> stn_code=Db_conn.ISL_detail(train_num);
                  request.setAttribute("stn_code",stn_code);
                  request.setAttribute("train_num",train_num);
                  RequestDispatcher rd=request.getRequestDispatcher("selected_trains.jsp");
                  rd.forward(request, response);
              
              }
             else if(train_num!=null && train_num!=null && "3".equals( selected_train_sms) )
              {
                  System.out.println("inside selected train smsm loop");
                  ArrayList<String> stn_code=Db_conn.ISL_detail(train_num);
                  request.setAttribute("stn_code",stn_code);
                  request.setAttribute("train_num",train_num);
                  RequestDispatcher rd=request.getRequestDispatcher("selected_train_sms.jsp");
                  rd.forward(request, response);
              
              }
              else if(train_num!=null && station_code!=null && "4".equals( particular_station_sms) )
              {
                System.out.println("inside particluar station smsm loop");

                  ArrayList<String> train_number=Db_conn.Train_number(station_code);
                  request.setAttribute("train_number",train_number);
                  request.setAttribute("station_code",station_code);
                  RequestDispatcher rd=request.getRequestDispatcher("particular_station_sms.jsp");
                  rd.forward(request, response); 
              }
        } finally {            
            out.close();
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
