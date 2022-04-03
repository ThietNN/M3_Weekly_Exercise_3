package controller;

import dao.PersonaDAO;
import dao.PersonaUserDAO;
import model.Persona;
import model.PersonaUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PersonaServlet", value = "/p5")
public class PersonaServlet extends HttpServlet {
    private PersonaDAO personaDAO;
    private PersonaUserDAO personaUserDAO;

    public void init(){
        personaDAO = new PersonaDAO();
        personaUserDAO = new PersonaUserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action){
                case "createPersonaUser":
                    createNewPersonaUser(request,response);
                    break;
                case "editPersonaUser":
                    editPersonaUser(request,response);
                    break;
                case "deletePersonaUser":
                    deletePersonaUser(request,response);
                    break;
                default:
                    personaUserList(request,response);
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action){
                case "createPersonaUser":
                    insertPersonaUser(request,response);
                    break;
                case "editPersonaUser":
                    updatePersonaUser(request,response);
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }
    private void personaUserList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<PersonaUser> personaUserList = personaUserDAO.selectAllPersonaUser();
        request.setAttribute("personaUserList",personaUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("personaUserList.jsp");
        requestDispatcher.forward(request,response);
    }
    private void createNewPersonaUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("createPersonaUser.jsp");
        requestDispatcher.forward(request,response);
    }
    private void editPersonaUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        PersonaUser personaUser = personaUserDAO.selectPersonaUser(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editPersonaUser.jsp");
        request.setAttribute("personaUser",personaUser);
        requestDispatcher.forward(request,response);
    }
    private void deletePersonaUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        personaUserDAO.deletePersonaUser(id);

        List<PersonaUser> personaUserList = personaUserDAO.selectAllPersonaUser();
        request.setAttribute("personaUserList",personaUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("personaUserList.jsp");
        requestDispatcher.forward(request,response);
    }
    private void insertPersonaUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int personaId = Integer.parseInt(request.getParameter("personaId"));
        PersonaUser personaUser = new PersonaUser(id,name,gender,personaId);
        personaUserDAO.insertPersonaUser(personaUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("createPersonaUser.jsp");
        requestDispatcher.forward(request,response);
    }
    private void updatePersonaUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int personaId = Integer.parseInt(request.getParameter("personaId"));

        PersonaUser personaUser = new PersonaUser(id,name,gender,personaId);
        personaUserDAO.updatePersonaUser(personaUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editPersonaUser.jsp");
        requestDispatcher.forward(request,response);
    }
}
