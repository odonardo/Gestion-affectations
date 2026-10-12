// // package com.yourapp.servlets;

// // import com.yourapp.dao.EmployeDAO;
// // import com.yourapp.entities.Employe;

// // import javax.servlet.ServletException;
// // import javax.servlet.http.HttpServlet;
// // import javax.servlet.http.HttpServletRequest;
// // import javax.servlet.http.HttpServletResponse;
// // import java.io.IOException;
// // import java.util.List;
// // import java.util.logging.Level;
// // import java.util.logging.Logger;

// // public class EmployeServlet extends HttpServlet {

// //     private static final Logger LOGGER = Logger.getLogger(EmployeServlet.class.getName());
// //     private static final String PARAM_CODEEMP = "codeemp";
// //     private final EmployeDAO employeDAO = new EmployeDAO();

// //     @Override
// //     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String action = req.getParameter("action");
// //         if (action == null) {
// //             action = "list";
// //         }

// //         try {
// //             switch (action) {
// //                 case "list":
// //                     listEmployes(req, resp);
// //                     break;
// //                 case "add":
// //                     req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
// //                     break;
// //                 case "edit":
// //                     showEditForm(req, resp);
// //                     break;
// //                 case "delete":
// //                     deleteEmploye(req, resp);
// //                     break;
// //                 case "search":
// //                     searchEmployes(req, resp);
// //                     break;
// //                 default:
// //                     listEmployes(req, resp);
// //             }
// //         } catch (ServletException | IOException e) {
// //             LOGGER.log(Level.SEVERE, "Erreur lors du traitement de la requête GET", e);
// //             handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
// //         }
// //     }

// //     @Override
// //     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String action = req.getParameter("action");

// //         try {
// //             if ("save".equals(action)) {
// //                 saveEmploye(req, resp);
// //             } else {
// //                 listEmployes(req, resp);
// //             }
// //         } catch (ServletException | IOException e) {
// //             LOGGER.log(Level.SEVERE, "Erreur lors du traitement de la requête POST", e);
// //             handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
// //         }
// //     }

// //     private void listEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         List<Employe> employes = employeDAO.findAll();
// //         req.setAttribute("employes", employes);
// //         req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
// //     }

// //     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String code = req.getParameter(PARAM_CODEEMP);
// //         Employe employe = employeDAO.findById(code);
// //         req.setAttribute("employe", employe);
// //         req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
// //     }

// //     private void saveEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
// //         String code = req.getParameter(PARAM_CODEEMP);
// //         String nom = req.getParameter("nom");
// //         String prenom = req.getParameter("prenom");
// //         String poste = req.getParameter("poste");

// //         Employe employe = new Employe(code, nom, prenom, poste);
// //         employeDAO.save(employe);
// //         resp.sendRedirect("employe?action=list");
// //     }

// //     private void deleteEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
// //         String code = req.getParameter(PARAM_CODEEMP);
// //         employeDAO.delete(code);
// //         resp.sendRedirect("employe?action=list");
// //     }

// //     private void searchEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String keyword = req.getParameter("keyword");
// //         List<Employe> result = employeDAO.searchByCodeOrName(keyword);
// //         req.setAttribute("employes", result);
// //         req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
// //     }

// //     private void handleError(HttpServletResponse resp, int statusCode, String message) {
// //         try {
// //             resp.sendError(statusCode, message);
// //         } catch (IOException e) {
// //             LOGGER.log(Level.SEVERE, "Impossible d'envoyer l'erreur HTTP", e);
// //         }
// //     }
// // }




// // // package com.yourapp.servlets;

// // // import com.yourapp.dao.EmployeDAO;
// // // import com.yourapp.entities.Employe;

// // // import javax.servlet.ServletException;
// // // import javax.servlet.annotation.WebServlet;
// // // import javax.servlet.http.HttpServlet;
// // // import javax.servlet.http.HttpServletRequest;
// // // import javax.servlet.http.HttpServletResponse;
// // // import javax.servlet.http.HttpSession;
// // // import java.io.IOException;
// // // import java.util.List;
// // // import java.util.logging.Level;
// // // import java.util.logging.Logger;

// // // @WebServlet("/employe")
// // // public class EmployeServlet extends HttpServlet {

// // //     private static final Logger LOGGER = Logger.getLogger(EmployeServlet.class.getName());
// // //     private static final String PARAM_CODEEMP = "codeemp";
// // //     private final EmployeDAO employeDAO = new EmployeDAO();

// // //     @Override
// // //     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// // //         String action = req.getParameter("action");
// // //         if (action == null) {
// // //             action = "list";
// // //         }

// // //         try {
// // //             switch (action) {
// // //                 case "list":
// // //                     listEmployes(req, resp);
// // //                     break;
// // //                 case "add":
// // //                     req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
// // //                     break;
// // //                 case "edit":
// // //                     showEditForm(req, resp);
// // //                     break;
// // //                 case "delete":
// // //                     deleteEmploye(req, resp);
// // //                     break;
// // //                 case "search":
// // //                     searchEmployes(req, resp);
// // //                     break;
// // //                 default:
// // //                     listEmployes(req, resp);
// // //             }
// // //         } catch (ServletException | IOException e) {
// // //             LOGGER.log(Level.SEVERE, "Erreur lors du traitement de la requête GET", e);
// // //             handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
// // //         }
// // //     }

// // //     @Override
// // //     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// // //         String action = req.getParameter("action");

// // //         try {
// // //             if ("save".equals(action)) {
// // //                 saveEmploye(req, resp);
// // //             } else {
// // //                 listEmployes(req, resp);
// // //             }
// // //         } catch (ServletException | IOException e) {
// // //             LOGGER.log(Level.SEVERE, "Erreur lors du traitement de la requête POST", e);
// // //             handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
// // //         }
// // //     }

// // //     private void listEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// // //         List<Employe> employes = employeDAO.findAll();
// // //         req.setAttribute("employes", employes);
// // //         req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
// // //     }

// // //     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// // //         String code = req.getParameter(PARAM_CODEEMP);
// // //         Employe employe = employeDAO.findById(code);
// // //         req.setAttribute("employe", employe);
// // //         req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
// // //     }

// // //     private void saveEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
// // //         String code = req.getParameter(PARAM_CODEEMP);
// // //         String nom = req.getParameter("nom");
// // //         String prenom = req.getParameter("prenom");
// // //         String poste = req.getParameter("poste");

// // //         // Vérifier si c'est un ajout (nouveau) ou une modification
// // //         boolean isNew = (employeDAO.findById(code) == null);

// // //         Employe employe = new Employe(code, nom, prenom, poste);
// // //         employeDAO.save(employe);

// // //         String msg = isNew ? "Employé ajouté avec succès !" : "Employé modifié avec succès !";
// // //         addMessage(req, msg, "success", "check-circle");
// // //         resp.sendRedirect("employe?action=list");
// // //     }

// // //     private void deleteEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
// // //         String code = req.getParameter(PARAM_CODEEMP);
// // //         Employe e = employeDAO.findById(code);
// // //         if (e != null) {
// // //             employeDAO.delete(code);
// // //             addMessage(req, "Employé supprimé avec succès !", "danger", "trash-alt");
// // //         } else {
// // //             addMessage(req, "Employé introuvable !", "warning", "exclamation-triangle");
// // //         }
// // //         resp.sendRedirect("employe?action=list");
// // //     }

// // //     private void searchEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// // //         String keyword = req.getParameter("keyword");
// // //         List<Employe> result = employeDAO.searchByCodeOrName(keyword);
// // //         req.setAttribute("employes", result);
// // //         req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
// // //     }

// // //     private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
// // //         HttpSession session = req.getSession();
// // //         session.setAttribute("message", msg);
// // //         session.setAttribute("messageType", type);
// // //         session.setAttribute("messageIcon", icon);
// // //     }

// // //     private void handleError(HttpServletResponse resp, int statusCode, String message) {
// // //         try {
// // //             resp.sendError(statusCode, message);
// // //         } catch (IOException e) {
// // //             LOGGER.log(Level.SEVERE, "Impossible d'envoyer l'erreur HTTP", e);
// // //         }
// // //     }
// // // }




// package com.yourapp.servlets;

// import com.yourapp.dao.EmployeDAO;
// import com.yourapp.entities.Employe;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
// import java.util.List;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// @WebServlet("/employe")
// public class EmployeServlet extends HttpServlet {

//     private static final Logger LOGGER = Logger.getLogger(EmployeServlet.class.getName());
//     private static final String PARAM_CODEEMP = "codeemp";
//     private final EmployeDAO employeDAO = new EmployeDAO();

//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         if (action == null) {
//             action = "list";
//         }

//         try {
//             switch (action) {
//                 case "list":
//                     listEmployes(req, resp);
//                     break;
//                 case "add":
//                     req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
//                     break;
//                 case "edit":
//                     showEditForm(req, resp);
//                     break;
//                 case "delete":
//                     deleteEmploye(req, resp);
//                     break;
//                 case "search":
//                     searchEmployes(req, resp);
//                     break;
//                 default:
//                     listEmployes(req, resp);
//             }
//         } catch (ServletException | IOException e) {
//             LOGGER.log(Level.SEVERE, "Erreur lors du traitement de la requête GET", e);
//             handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
//         }
//     }

//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");

//         try {
//             if ("save".equals(action)) {
//                 saveEmploye(req, resp);
//             } else {
//                 listEmployes(req, resp);
//             }
//         } catch (ServletException | IOException e) {
//             LOGGER.log(Level.SEVERE, "Erreur lors du traitement de la requête POST", e);
//             handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
//         }
//     }

//     private void listEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         List<Employe> employes = employeDAO.findAll();
//         req.setAttribute("employes", employes);
//         req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
//     }

//     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String code = req.getParameter(PARAM_CODEEMP);
//         Employe employe = employeDAO.findById(code);
//         req.setAttribute("employe", employe);
//         req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
//     }

//     private void saveEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String code = req.getParameter(PARAM_CODEEMP);
//         String nom = req.getParameter("nom");
//         String prenom = req.getParameter("prenom");
//         String poste = req.getParameter("poste");

//         // Vérifier si c'est un ajout (nouveau) ou une modification
//         boolean isNew = (employeDAO.findById(code) == null);

//         Employe employe = new Employe(code, nom, prenom, poste);
//         employeDAO.save(employe);

//         String msg = isNew ? "Employé ajouté avec succès !" : "Employé modifié avec succès !";
//         addMessage(req, msg, "success", "check-circle");
//         resp.sendRedirect("employe?action=list");
//     }

//     private void deleteEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String code = req.getParameter(PARAM_CODEEMP);
//         Employe e = employeDAO.findById(code);
//         if (e != null) {
//             employeDAO.delete(code);
//             addMessage(req, "Employé supprimé avec succès !", "danger", "trash-alt");
//         } else {
//             addMessage(req, "Employé introuvable !", "warning", "exclamation-triangle");
//         }
//         resp.sendRedirect("employe?action=list");
//     }

//     private void searchEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String keyword = req.getParameter("keyword");
//         List<Employe> result = employeDAO.searchByCodeOrName(keyword);
//         req.setAttribute("employes", result);
//         req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
//     }

//     private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
//         HttpSession session = req.getSession();
//         session.setAttribute("message", msg);
//         session.setAttribute("messageType", type);
//         session.setAttribute("messageIcon", icon);
//     }

//     private void handleError(HttpServletResponse resp, int statusCode, String message) {
//         try {
//             resp.sendError(statusCode, message);
//         } catch (IOException e) {
//             LOGGER.log(Level.SEVERE, "Impossible d'envoyer l'erreur HTTP", e);
//         }
//     }
// }




package com.yourapp.servlets;

import com.yourapp.dao.EmployeDAO;
import com.yourapp.entities.Employe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/employe")
public class EmployeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EmployeServlet.class.getName());
    private static final String PARAM_CODEEMP = "codeemp";
    private final EmployeDAO employeDAO = new EmployeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listEmployes(req, resp);
                    break;
                case "add":
                    req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteEmploye(req, resp);
                    break;
                case "search":
                    searchEmployes(req, resp);
                    break;
                default:
                    listEmployes(req, resp);
            }
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Erreur GET", e);
            handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("save".equals(action)) {
                saveEmploye(req, resp);
            } else {
                listEmployes(req, resp);
            }
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Erreur POST", e);
            handleError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur.");
        }
    }

    private void listEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employe> employes = employeDAO.findAll();
        req.setAttribute("employes", employes);
        req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter(PARAM_CODEEMP);
        Employe employe = employeDAO.findById(code);
        req.setAttribute("employe", employe);
        req.getRequestDispatcher("/jsp/employe-form.jsp").forward(req, resp);
    }

    private void saveEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter(PARAM_CODEEMP);
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String poste = req.getParameter("poste");

        boolean isNew = (employeDAO.findById(code) == null);
        Employe employe = new Employe(code, nom, prenom, poste);
        employeDAO.save(employe);

        String msg = isNew ? "Employé ajouté avec succès !" : "Employé modifié avec succès !";
        addMessage(req, msg, "success", "check-circle");
        resp.sendRedirect("employe?action=list");
    }

    private void deleteEmploye(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter(PARAM_CODEEMP);
        Employe e = employeDAO.findById(code);
        if (e != null) {
            employeDAO.delete(code);
            addMessage(req, "Employé supprimé avec succès !", "danger", "trash-alt");
        } else {
            addMessage(req, "Employé introuvable !", "warning", "exclamation-triangle");
        }
        resp.sendRedirect("employe?action=list");
    }

    private void searchEmployes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Employe> result;
        if (keyword != null && !keyword.trim().isEmpty()) {
            result = employeDAO.searchByKeyword(keyword.trim());
        } else {
            result = employeDAO.findAll();
        }
        req.setAttribute("employes", result);
        req.getRequestDispatcher("/jsp/employe-list.jsp").forward(req, resp);
    }

    private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
        HttpSession session = req.getSession();
        session.setAttribute("message", msg);
        session.setAttribute("messageType", type);
        session.setAttribute("messageIcon", icon);
    }

    private void handleError(HttpServletResponse resp, int statusCode, String message) {
        try {
            resp.sendError(statusCode, message);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Impossible d'envoyer l'erreur HTTP", e);
        }
    }
}