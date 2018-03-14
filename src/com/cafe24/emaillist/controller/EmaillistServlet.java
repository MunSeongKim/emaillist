package com.cafe24.emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.emaillist.dao.EmaillistDAO;
import com.cafe24.emaillist.vo.EmaillistVO;
import com.cafe24.mvc.util.WebUtil;

@WebServlet( "/el" )
public class EmaillistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	request.setCharacterEncoding( "UTF-8" );

	String actionName = request.getParameter( "a" );
	if ( "form".equals( actionName ) ) {
	    WebUtil.forward( request, response, "/WEB-INF/views/form.jsp" );
	} else if ( "add".equals( actionName ) ) {
		String firstName = request.getParameter( "first-name" );
		String lastName = request.getParameter( "last-name" );
		String email = request.getParameter( "email" );
		
		EmaillistVO vo = new EmaillistVO();
		vo.setFirstName( firstName );
		vo.setLastName( lastName );
		vo.setEmail( email );
		
		EmaillistDAO dao = new EmaillistDAO();
		dao.insert(vo);
		
		WebUtil.redirect( request, response, "/emaillist2/el");
	} else {
	    /* default action 처리 */
	    EmaillistDAO dao = new EmaillistDAO();
	    List<EmaillistVO> list = dao.getList();

	    request.setAttribute( "list", list );
	    WebUtil.forward( request, response, "/WEB-INF/views/index.jsp" );
	}
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet( request, response );
    }

}
