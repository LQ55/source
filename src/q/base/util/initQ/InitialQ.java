package q.base.util.initQ;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.base.util.DBTableInfoProcessor;

/**
 * Servlet implementation class InitialQ
 */
@WebServlet("/InitialQ")
public class InitialQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitialQ() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	
	public void init() throws ServletException {
		DBTableInfoProcessor dbTableInfoProcessor= new DBTableInfoProcessor();
		try {
			dbTableInfoProcessor.Process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
