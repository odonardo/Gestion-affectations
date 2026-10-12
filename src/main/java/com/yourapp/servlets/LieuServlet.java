// package com.yourapp.servlets;

// import com.yourapp.dao.LieuDAO;
// import com.yourapp.entities.Lieu;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
// import java.util.logging.Logger;

// @WebServlet("/lieu")
// public class LieuServlet extends HttpServlet {

//     private static final Logger LOGGER = Logger.getLogger(LieuServlet.class.getName());
//     private final LieuDAO lieuDAO = new LieuDAO();

//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         if (action == null) action = "list";

//         try {
//             switch (action) {
//                 case "list":
//                     listLieus(req, resp);
//                     break;
//                 case "add":
//                     req.getRequestDispatcher("/jsp/lieu-form.jsp").forward(req, resp);
//                     break;
//                 case "edit":
//                     showEditForm(req, resp);
//                     break;
//                 case "delete":
//                     deleteLieu(req, resp);
//                     break;
//                 case "search":
//                     searchLieus(req, resp);
//                     break;
//                 default:
//                     listLieus(req, resp);
//             }
//         } catch (Exception e) {
//             LOGGER.severe("Erreur GET Lieu : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         try {
//             if ("save".equals(action)) {
//                 saveLieu(req, resp);
//             } else {
//                 listLieus(req, resp);
//             }
//         } catch (Exception e) {
//             LOGGER.severe("Erreur POST Lieu : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     private void listLieus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         req.setAttribute("lieux", lieuDAO.findAll());
//         req.getRequestDispatcher("/jsp/lieu-list.jsp").forward(req, resp);
//     }

//     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String code = req.getParameter("codelieu");
//         req.setAttribute("lieu", lieuDAO.findById(code));
//         req.getRequestDispatcher("/jsp/lieu-form.jsp").forward(req, resp);
//     }

//     private void saveLieu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String code = req.getParameter("codelieu");
//         String designation = req.getParameter("designation");
//         String province = req.getParameter("province");

//         boolean isNew = (lieuDAO.findById(code) == null);
//         Lieu lieu = new Lieu(code, designation, province);
//         lieuDAO.save(lieu);

//         String msg = isNew ? "Lieu ajouté avec succès !" : "Lieu modifié avec succès !";
//         addMessage(req, msg, "success", "check-circle");
//         resp.sendRedirect("lieu?action=list");
//     }

//     private void deleteLieu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String code = req.getParameter("codelieu");
//         lieuDAO.delete(code);
//         addMessage(req, "Lieu supprimé avec succès !", "danger", "trash-alt");
//         resp.sendRedirect("lieu?action=list");
//     }

//     private void searchLieus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String keyword = req.getParameter("keyword");
//         req.setAttribute("lieux", lieuDAO.searchByCodeOrDesignation(keyword));
//         req.getRequestDispatcher("/jsp/lieu-list.jsp").forward(req, resp);
//     }

//     private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
//         HttpSession session = req.getSession();
//         session.setAttribute("message", msg);
//         session.setAttribute("messageType", type);
//         session.setAttribute("messageIcon", icon);
//     }
// }






package com.yourapp.servlets;

import com.yourapp.dao.LieuDAO;
import com.yourapp.entities.Lieu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/lieu")
public class LieuServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LieuServlet.class.getName());
    private final LieuDAO lieuDAO = new LieuDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listLieus(req, resp);
                    break;
                case "add":
                    req.getRequestDispatcher("/jsp/lieu-form.jsp").forward(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteLieu(req, resp);
                    break;
                case "search":
                    searchLieus(req, resp);
                    break;
                default:
                    listLieus(req, resp);
            }
        } catch (Exception e) {
            LOGGER.severe("Erreur GET Lieu : " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("save".equals(action)) {
                saveLieu(req, resp);
            } else {
                listLieus(req, resp);
            }
        } catch (Exception e) {
            LOGGER.severe("Erreur POST Lieu : " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void listLieus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lieux", lieuDAO.findAll());
        req.getRequestDispatcher("/jsp/lieu-list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("codelieu");
        req.setAttribute("lieu", lieuDAO.findById(code));
        req.getRequestDispatcher("/jsp/lieu-form.jsp").forward(req, resp);
    }

    private void saveLieu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("codelieu");
        String designation = req.getParameter("designation");
        String province = req.getParameter("province");

        boolean isNew = (lieuDAO.findById(code) == null);
        Lieu lieu = new Lieu(code, designation, province);
        lieuDAO.save(lieu);

        String msg = isNew ? "Lieu ajouté avec succès !" : "Lieu modifié avec succès !";
        addMessage(req, msg, "success", "check-circle");
        resp.sendRedirect("lieu?action=list");
    }

    private void deleteLieu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("codelieu");
        Lieu l = lieuDAO.findById(code);
        if (l != null) {
            lieuDAO.delete(code);
            addMessage(req, "Lieu supprimé avec succès !", "danger", "trash-alt");
        } else {
            addMessage(req, "Lieu introuvable !", "warning", "exclamation-triangle");
        }
        resp.sendRedirect("lieu?action=list");
    }

    private void searchLieus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Lieu> result;
        if (keyword != null && !keyword.trim().isEmpty()) {
            result = lieuDAO.searchByKeyword(keyword.trim());
        } else {
            result = lieuDAO.findAll();
        }
        req.setAttribute("lieux", result);
        req.getRequestDispatcher("/jsp/lieu-list.jsp").forward(req, resp);
    }

    private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
        HttpSession session = req.getSession();
        session.setAttribute("message", msg);
        session.setAttribute("messageType", type);
        session.setAttribute("messageIcon", icon);
    }
}