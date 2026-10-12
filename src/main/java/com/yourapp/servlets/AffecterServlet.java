// // package com.yourapp.servlets;

// // import com.yourapp.dao.AffecterDAO;
// // import com.yourapp.dao.EmployeDAO;
// // import com.yourapp.dao.LieuDAO;
// // import com.yourapp.entities.Affecter;
// // import com.yourapp.entities.AffecterId;
// // import com.yourapp.entities.Employe;
// // import com.yourapp.entities.Lieu;

// // import javax.servlet.ServletException;
// // import javax.servlet.annotation.WebServlet;
// // import javax.servlet.http.HttpServlet;
// // import javax.servlet.http.HttpServletRequest;
// // import javax.servlet.http.HttpServletResponse;
// // import javax.servlet.http.HttpSession;
// // import java.io.IOException;
// // import java.time.LocalDate;
// // import java.util.List;
// // import java.util.logging.Logger;

// // @SuppressWarnings("unused")
// // @WebServlet("/affecter")
// // public class AffecterServlet extends HttpServlet {

// //     private static final Logger LOGGER = Logger.getLogger(AffecterServlet.class.getName());
// //     private final AffecterDAO affecterDAO = new AffecterDAO();
// //     private final EmployeDAO employeDAO = new EmployeDAO();
// //     private final LieuDAO lieuDAO = new LieuDAO();

// //     @Override
// //     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String action = req.getParameter("action");
// //         if (action == null) action = "list";

// //         try {
// //             switch (action) {
// //                 case "list":
// //                     listAffectations(req, resp);
// //                     break;
// //                 case "add":
// //                     showAddForm(req, resp);
// //                     break;
// //                 case "edit":
// //                     showEditForm(req, resp);
// //                     break;
// //                 case "delete":
// //                     deleteAffectation(req, resp);
// //                     break;
// //                 case "search":
// //                     searchAffectations(req, resp);
// //                     break;
// //                 default:
// //                     listAffectations(req, resp);
// //             }
// //         } catch (Exception e) {
// //             LOGGER.severe("Erreur GET Affecter : " + e.getMessage());
// //             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
// //         }
// //     }

// //     @Override
// //     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String action = req.getParameter("action");
// //         try {
// //             if ("save".equals(action)) {
// //                 saveAffectation(req, resp);
// //             } else {
// //                 listAffectations(req, resp);
// //             }
// //         } catch (Exception e) {
// //             LOGGER.severe("Erreur POST Affecter : " + e.getMessage());
// //             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
// //         }
// //     }

// //     private void listAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         req.setAttribute("affectations", affecterDAO.findAll());
// //         req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
// //     }

// //     private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         req.setAttribute("employes", employeDAO.findAll());
// //         req.setAttribute("lieux", lieuDAO.findAll());
// //         req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
// //     }

// //     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String codeemp = req.getParameter("codeemp");
// //         String codelieu = req.getParameter("codelieu");
// //         String dateStr = req.getParameter("date");
// //         try {
// //             LocalDate date = LocalDate.parse(dateStr);
// //             AffecterId id = new AffecterId(codeemp, codelieu, date);
// //             Affecter affecter = affecterDAO.findById(id);
// //             req.setAttribute("affecter", affecter);
// //             req.setAttribute("dateString", dateStr);
// //             req.setAttribute("employes", employeDAO.findAll());
// //             req.setAttribute("lieux", lieuDAO.findAll());
// //             req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
// //         } catch (Exception e) {
// //             LOGGER.severe("Erreur affichage formulaire affectation : " + e.getMessage());
// //             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
// //         }
// //     }

// //     private void saveAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
// //         String codeemp = req.getParameter("codeemp");
// //         String codelieu = req.getParameter("codelieu");
// //         String dateStr = req.getParameter("date");

// //         try {
// //             LocalDate date = LocalDate.parse(dateStr);
// //             AffecterId id = new AffecterId(codeemp, codelieu, date);

// //             boolean isNew = (affecterDAO.findById(id) == null);

// //             Employe employe = employeDAO.findById(codeemp);
// //             Lieu lieu = lieuDAO.findById(codelieu);

// //             Affecter affecter = new Affecter(id, employe, lieu);
// //             affecterDAO.save(affecter);

// //             String msg = isNew ? "Affectation ajoutée avec succès !" : "Affectation modifiée avec succès !";
// //             addMessage(req, msg, "success", "check-circle");
// //             resp.sendRedirect("affecter?action=list");
// //         } catch (Exception e) {
// //             LOGGER.severe("Erreur sauvegarde affectation : " + e.getMessage());
// //             addMessage(req, "Erreur lors de l'enregistrement : " + e.getMessage(), "danger", "exclamation-triangle");
// //             resp.sendRedirect("affecter?action=add");
// //         }
// //     }

// //     private void deleteAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
// //         String codeemp = req.getParameter("codeemp");
// //         String codelieu = req.getParameter("codelieu");
// //         String dateStr = req.getParameter("date");
// //         try {
// //             LocalDate date = LocalDate.parse(dateStr);
// //             AffecterId id = new AffecterId(codeemp, codelieu, date);
// //             affecterDAO.delete(id);
// //             addMessage(req, "Affectation supprimée avec succès !", "danger", "trash-alt");
// //             resp.sendRedirect("affecter?action=list");
// //         } catch (Exception e) {
// //             LOGGER.severe("Erreur suppression affectation : " + e.getMessage());
// //             addMessage(req, "Erreur lors de la suppression", "danger", "exclamation-triangle");
// //             resp.sendRedirect("affecter?action=list");
// //         }
// //     }

// //     private void searchAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// //         String keyword = req.getParameter("keyword");
// //         req.setAttribute("affectations", affecterDAO.searchByEmployeOrLieu(keyword));
// //         req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
// //     }

// //     private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
// //         HttpSession session = req.getSession();
// //         session.setAttribute("message", msg);
// //         session.setAttribute("messageType", type);
// //         session.setAttribute("messageIcon", icon);
// //     }
// // }




// package com.yourapp.servlets;

// import com.yourapp.dao.AffecterDAO;
// import com.yourapp.dao.EmployeDAO;
// import com.yourapp.dao.LieuDAO;
// import com.yourapp.entities.Affecter;
// import com.yourapp.entities.AffecterId;
// import com.yourapp.entities.Employe;
// import com.yourapp.entities.Lieu;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
// import java.time.LocalDate;
// import java.util.logging.Logger;

// @WebServlet("/affecter")
// public class AffecterServlet extends HttpServlet {

//     private static final Logger LOGGER = Logger.getLogger(AffecterServlet.class.getName());
//     private final AffecterDAO affecterDAO = new AffecterDAO();
//     private final EmployeDAO employeDAO = new EmployeDAO();
//     private final LieuDAO lieuDAO = new LieuDAO();

//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         if (action == null) action = "list";

//         try {
//             switch (action) {
//                 case "list":
//                     listAffectations(req, resp);
//                     break;
//                 case "add":
//                     showAddForm(req, resp);
//                     break;
//                 case "edit":
//                     showEditForm(req, resp);
//                     break;
//                 case "delete":
//                     deleteAffectation(req, resp);
//                     break;
//                 case "search":
//                     searchAffectations(req, resp);
//                     break;
//                 default:
//                     listAffectations(req, resp);
//             }
//         } catch (Exception e) {
//             LOGGER.severe("Erreur GET Affecter : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         try {
//             if ("save".equals(action)) {
//                 saveAffectation(req, resp);
//             } else {
//                 listAffectations(req, resp);
//             }
//         } catch (Exception e) {
//             LOGGER.severe("Erreur POST Affecter : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     private void listAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         req.setAttribute("affectations", affecterDAO.findAll());
//         req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
//     }

//     private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         req.setAttribute("employes", employeDAO.findAll());
//         req.setAttribute("lieux", lieuDAO.findAll());
//         // Pré-remplir la date du jour
//         req.setAttribute("dateString", LocalDate.now().toString());
//         req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
//     }

//     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String codeemp = req.getParameter("codeemp");
//         String codelieu = req.getParameter("codelieu");
//         String dateStr = req.getParameter("date");
//         try {
//             LocalDate date = LocalDate.parse(dateStr);
//             AffecterId id = new AffecterId(codeemp, codelieu, date);
//             Affecter affecter = affecterDAO.findById(id);
//             req.setAttribute("affecter", affecter);
//             req.setAttribute("dateString", dateStr);
//             req.setAttribute("employes", employeDAO.findAll());
//             req.setAttribute("lieux", lieuDAO.findAll());
//             req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
//         } catch (Exception e) {
//             LOGGER.severe("Erreur affichage formulaire affectation : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     private void saveAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String codeemp = req.getParameter("codeemp");
//         String codelieu = req.getParameter("codelieu");
//         String dateStr = req.getParameter("date");

//         try {
//             LocalDate date = LocalDate.parse(dateStr);
//             AffecterId id = new AffecterId(codeemp, codelieu, date);

//             boolean isNew = (affecterDAO.findById(id) == null);

//             Employe employe = employeDAO.findById(codeemp);
//             Lieu lieu = lieuDAO.findById(codelieu);

//             Affecter affecter = new Affecter(id, employe, lieu);
//             affecterDAO.save(affecter);

//             String msg = isNew ? "Affectation ajoutée avec succès !" : "Affectation modifiée avec succès !";
//             addMessage(req, msg, "success", "check-circle");
//             resp.sendRedirect("affecter?action=list");
//         } catch (Exception e) {
//             LOGGER.severe("Erreur sauvegarde affectation : " + e.getMessage());
//             addMessage(req, "Erreur lors de l'enregistrement : " + e.getMessage(), "danger", "exclamation-triangle");
//             resp.sendRedirect("affecter?action=add");
//         }
//     }

//     private void deleteAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String codeemp = req.getParameter("codeemp");
//         String codelieu = req.getParameter("codelieu");
//         String dateStr = req.getParameter("date");
//         try {
//             LocalDate date = LocalDate.parse(dateStr);
//             AffecterId id = new AffecterId(codeemp, codelieu, date);
//             affecterDAO.delete(id);
//             addMessage(req, "Affectation supprimée avec succès !", "danger", "trash-alt");
//             resp.sendRedirect("affecter?action=list");
//         } catch (Exception e) {
//             LOGGER.severe("Erreur suppression affectation : " + e.getMessage());
//             addMessage(req, "Erreur lors de la suppression", "danger", "exclamation-triangle");
//             resp.sendRedirect("affecter?action=list");
//         }
//     }

//     private void searchAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String keyword = req.getParameter("keyword");
//         req.setAttribute("affectations", affecterDAO.searchByEmployeOrLieu(keyword));
//         req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
//     }

//     private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
//         HttpSession session = req.getSession();
//         session.setAttribute("message", msg);
//         session.setAttribute("messageType", type);
//         session.setAttribute("messageIcon", icon);
//     }
// }



// package com.yourapp.servlets;

// import com.yourapp.dao.AffecterDAO;
// import com.yourapp.dao.EmployeDAO;
// import com.yourapp.dao.LieuDAO;
// import com.yourapp.entities.Affecter;
// import com.yourapp.entities.AffecterId;
// import com.yourapp.entities.Employe;
// import com.yourapp.entities.Lieu;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.logging.Logger;

// @WebServlet("/affecter")
// public class AffecterServlet extends HttpServlet {

//     private static final Logger LOGGER = Logger.getLogger(AffecterServlet.class.getName());
//     private final AffecterDAO affecterDAO = new AffecterDAO();
//     private final EmployeDAO employeDAO = new EmployeDAO();
//     private final LieuDAO lieuDAO = new LieuDAO();

//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         if (action == null) action = "list";

//         try {
//             switch (action) {
//                 case "list":
//                     listAffectations(req, resp);
//                     break;
//                 case "add":
//                     showAddForm(req, resp);
//                     break;
//                 case "edit":
//                     showEditForm(req, resp);
//                     break;
//                 case "delete":
//                     deleteAffectation(req, resp);
//                     break;
//                 case "search":
//                     searchAffectations(req, resp);
//                     break;
//                 default:
//                     listAffectations(req, resp);
//             }
//         } catch (Exception e) {
//             LOGGER.severe("Erreur GET Affecter : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String action = req.getParameter("action");
//         try {
//             if ("save".equals(action)) {
//                 saveAffectation(req, resp);
//             } else {
//                 listAffectations(req, resp);
//             }
//         } catch (Exception e) {
//             LOGGER.severe("Erreur POST Affecter : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     private void listAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         req.setAttribute("affectations", affecterDAO.findAll());
//         req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
//     }

//     private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         req.setAttribute("employes", employeDAO.findAll());
//         req.setAttribute("lieux", lieuDAO.findAll());
//         req.setAttribute("dateString", LocalDate.now().toString());
//         req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
//     }

//     private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String codeemp = req.getParameter("codeemp");
//         String codelieu = req.getParameter("codelieu");
//         String dateStr = req.getParameter("date");
//         try {
//             LocalDate date = LocalDate.parse(dateStr);
//             AffecterId id = new AffecterId(codeemp, codelieu, date);
//             Affecter affecter = affecterDAO.findById(id);
//             req.setAttribute("affecter", affecter);
//             req.setAttribute("dateString", dateStr);
//             req.setAttribute("employes", employeDAO.findAll());
//             req.setAttribute("lieux", lieuDAO.findAll());
//             req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
//         } catch (Exception e) {
//             LOGGER.severe("Erreur affichage formulaire affectation : " + e.getMessage());
//             resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//         }
//     }

//     private void saveAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String codeemp = req.getParameter("codeemp");
//         String codelieu = req.getParameter("codelieu");
//         String dateStr = req.getParameter("date");

//         try {
//             LocalDate date = LocalDate.parse(dateStr);
//             AffecterId id = new AffecterId(codeemp, codelieu, date);
//             boolean isNew = (affecterDAO.findById(id) == null);

//             Employe employe = employeDAO.findById(codeemp);
//             Lieu lieu = lieuDAO.findById(codelieu);

//             Affecter affecter = new Affecter(id, employe, lieu);
//             affecterDAO.save(affecter);

//             String msg = isNew ? "Affectation ajoutée avec succès !" : "Affectation modifiée avec succès !";
//             addMessage(req, msg, "success", "check-circle");
//             resp.sendRedirect("affecter?action=list");
//         } catch (Exception e) {
//             LOGGER.severe("Erreur sauvegarde affectation : " + e.getMessage());
//             addMessage(req, "Erreur lors de l'enregistrement : " + e.getMessage(), "danger", "exclamation-triangle");
//             resp.sendRedirect("affecter?action=add");
//         }
//     }

//     private void deleteAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         String codeemp = req.getParameter("codeemp");
//         String codelieu = req.getParameter("codelieu");
//         String dateStr = req.getParameter("date");
//         try {
//             LocalDate date = LocalDate.parse(dateStr);
//             AffecterId id = new AffecterId(codeemp, codelieu, date);
//             affecterDAO.delete(id);
//             addMessage(req, "Affectation supprimée avec succès !", "danger", "trash-alt");
//             resp.sendRedirect("affecter?action=list");
//         } catch (Exception e) {
//             LOGGER.severe("Erreur suppression affectation : " + e.getMessage());
//             addMessage(req, "Erreur lors de la suppression", "danger", "exclamation-triangle");
//             resp.sendRedirect("affecter?action=list");
//         }
//     }

//     private void searchAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String keyword = req.getParameter("keyword");
//         if (keyword != null && !keyword.trim().isEmpty()) {
//             req.setAttribute("affectations", affecterDAO.searchByKeyword(keyword.trim()));
//         } else {
//             req.setAttribute("affectations", affecterDAO.findAll());
//         }
//         req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
//     }

//     private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
//         HttpSession session = req.getSession();
//         session.setAttribute("message", msg);
//         session.setAttribute("messageType", type);
//         session.setAttribute("messageIcon", icon);
//     }
// }



package com.yourapp.servlets;

import com.yourapp.dao.AffecterDAO;
import com.yourapp.dao.EmployeDAO;
import com.yourapp.dao.LieuDAO;
import com.yourapp.entities.Affecter;
import com.yourapp.entities.AffecterId;
import com.yourapp.entities.Employe;
import com.yourapp.entities.Lieu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/affecter")
public class AffecterServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AffecterServlet.class.getName());
    private final AffecterDAO affecterDAO = new AffecterDAO();
    private final EmployeDAO employeDAO = new EmployeDAO();
    private final LieuDAO lieuDAO = new LieuDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    listAffectations(req, resp);
                    break;
                case "add":
                    showAddForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteAffectation(req, resp);
                    break;
                case "search":
                    searchAffectations(req, resp);
                    break;
                default:
                    listAffectations(req, resp);
            }
        } catch (Exception e) {
            LOGGER.severe("Erreur GET Affecter : " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("save".equals(action)) {
                saveAffectation(req, resp);
            } else {
                listAffectations(req, resp);
            }
        } catch (Exception e) {
            LOGGER.severe("Erreur POST Affecter : " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void listAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("affectations", affecterDAO.findAll());
        req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employes", employeDAO.findAll());
        req.setAttribute("lieux", lieuDAO.findAll());
        req.setAttribute("dateString", LocalDate.now().toString());
        req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeemp = req.getParameter("codeemp");
        String codelieu = req.getParameter("codelieu");
        String dateStr = req.getParameter("date");
        try {
            LocalDate date = LocalDate.parse(dateStr);
            AffecterId id = new AffecterId(codeemp, codelieu, date);
            Affecter affecter = affecterDAO.findById(id);
            req.setAttribute("affecter", affecter);
            req.setAttribute("dateString", dateStr);
            // Passer aussi l'ancien ID pour les champs cachés
            req.setAttribute("oldCodeemp", codeemp);
            req.setAttribute("oldCodelieu", codelieu);
            req.setAttribute("oldDate", dateStr);
            req.setAttribute("employes", employeDAO.findAll());
            req.setAttribute("lieux", lieuDAO.findAll());
            req.getRequestDispatcher("/jsp/affecter-form.jsp").forward(req, resp);
        } catch (Exception e) {
            LOGGER.severe("Erreur affichage formulaire affectation : " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void saveAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Récupérer les nouvelles valeurs
        String newCodeemp = req.getParameter("codeemp");
        String newCodelieu = req.getParameter("codelieu");
        String newDateStr = req.getParameter("date");

        // Récupérer les anciennes valeurs (champs cachés)
        String oldCodeemp = req.getParameter("oldCodeemp");
        String oldCodelieu = req.getParameter("oldCodelieu");
        String oldDateStr = req.getParameter("oldDate");

        try {
            LocalDate newDate = LocalDate.parse(newDateStr);
            AffecterId newId = new AffecterId(newCodeemp, newCodelieu, newDate);

            // Vérifier si c'est une modification (ancien ID fourni)
            boolean isEdit = (oldCodeemp != null && !oldCodeemp.isEmpty() &&
                              oldCodelieu != null && !oldCodelieu.isEmpty() &&
                              oldDateStr != null && !oldDateStr.isEmpty());

            // Si c'est une modification, supprimer l'ancienne affectation
            if (isEdit) {
                LocalDate oldDate = LocalDate.parse(oldDateStr);
                AffecterId oldId = new AffecterId(oldCodeemp, oldCodelieu, oldDate);
                Affecter oldAffecter = affecterDAO.findById(oldId);
                if (oldAffecter != null) {
                    // Si l'ID a changé, supprimer l'ancienne
                    if (!oldId.equals(newId)) {
                        affecterDAO.delete(oldId);
                    }
                    // Si l'ID est le même, on ne fait rien (pas de modification réelle)
                }
            }

            // Vérifier si la nouvelle affectation existe déjà (pour éviter doublon)
            if (affecterDAO.findById(newId) == null) {
                Employe employe = employeDAO.findById(newCodeemp);
                Lieu lieu = lieuDAO.findById(newCodelieu);
                Affecter newAffecter = new Affecter(newId, employe, lieu);
                affecterDAO.save(newAffecter);
                String msg = isEdit ? "Affectation modifiée avec succès !" : "Affectation ajoutée avec succès !";
                addMessage(req, msg, "success", "check-circle");
            } else {
                addMessage(req, "Cette affectation existe déjà !", "warning", "exclamation-triangle");
            }

            resp.sendRedirect("affecter?action=list");

        } catch (Exception e) {
            LOGGER.severe("Erreur sauvegarde affectation : " + e.getMessage());
            addMessage(req, "Erreur lors de l'enregistrement : " + e.getMessage(), "danger", "exclamation-triangle");
            resp.sendRedirect("affecter?action=add");
        }
    }

    private void deleteAffectation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String codeemp = req.getParameter("codeemp");
        String codelieu = req.getParameter("codelieu");
        String dateStr = req.getParameter("date");
        try {
            LocalDate date = LocalDate.parse(dateStr);
            AffecterId id = new AffecterId(codeemp, codelieu, date);
            affecterDAO.delete(id);
            addMessage(req, "Affectation supprimée avec succès !", "danger", "trash-alt");
            resp.sendRedirect("affecter?action=list");
        } catch (Exception e) {
            LOGGER.severe("Erreur suppression affectation : " + e.getMessage());
            addMessage(req, "Erreur lors de la suppression", "danger", "exclamation-triangle");
            resp.sendRedirect("affecter?action=list");
        }
    }

    private void searchAffectations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword != null && !keyword.trim().isEmpty()) {
            req.setAttribute("affectations", affecterDAO.searchByKeyword(keyword.trim()));
        } else {
            req.setAttribute("affectations", affecterDAO.findAll());
        }
        req.getRequestDispatcher("/jsp/affecter-list.jsp").forward(req, resp);
    }

    private void addMessage(HttpServletRequest req, String msg, String type, String icon) {
        HttpSession session = req.getSession();
        session.setAttribute("message", msg);
        session.setAttribute("messageType", type);
        session.setAttribute("messageIcon", icon);
    }
}