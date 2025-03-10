

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/save")
public class savedata extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		String email=req.getParameter("em");
		String p=req.getParameter("ps");
		int password=Integer.parseInt(p);
		
		String firstname=req.getParameter("fn");
		String lastname=req.getParameter("ln");
		String gender=req.getParameter("gender");
		String country=req.getParameter("country");
		System.out.println(email);
		System.out.println(p);
		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(gender);
		System.out.println(country);
		
	
		Connection con=null;
		PreparedStatement pstmt=null;
		String qry="insert into jspiders.registration values(?,?,?,?,?,?)";
		
		try 
		{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=j2ee&password=root");
			pstmt=con.prepareStatement(qry);
			
			pstmt.setString(1,firstname);
			pstmt.setString(2,lastname);
			pstmt.setString(3,email);
			pstmt.setInt(4,password);
			pstmt.setString(5,gender);
			pstmt.setString(6,country);
			
			pstmt.executeUpdate();
			
			
			
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();	
		}
		finally

		{
			if(pstmt!=null)
			{

				try 
				{
					pstmt.close();
				}
				catch (SQLException e) 
				{

					e.printStackTrace();
				}
			}

			if(con!=null)
			{
				try 
				{

					con.close();

				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("fetchdata.jsp");
		rd.forward(req, res);
		
	}
	
	}


