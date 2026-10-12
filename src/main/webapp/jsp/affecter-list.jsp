<!-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Affectations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="container">

    <div class="card" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap;">
        <h1><i class="fas fa-link" style="color:#1a2b4c;"></i> Gestion des Affectations</h1>
        <div>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary" style="margin-right:10px;">
                <i class="fas fa-home"></i> Accueil
            </a>
            <a href="${pageContext.request.contextPath}/employe?action=list" class="btn btn-secondary" style="margin-right:10px;">
                <i class="fas fa-users"></i> Employés
            </a>
            <a href="${pageContext.request.contextPath}/lieu?action=list" class="btn btn-secondary" style="margin-right:10px;">
                <i class="fas fa-map-pin"></i> Lieux
            </a>
            <a href="${pageContext.request.contextPath}/affecter?action=add" class="btn btn-success">
                <i class="fas fa-plus"></i> Ajouter
            </a>
        </div>
    </div>


    <c:if test="${not empty sessionScope.message}">
        <div class="message message-${sessionScope.messageType}">
            <i class="fas fa-${sessionScope.messageIcon}"></i>
            <span>${sessionScope.message}</span>
        </div>
        <% session.removeAttribute("message"); session.removeAttribute("messageType"); session.removeAttribute("messageIcon"); %>
    </c:if>


    <div class="card">
        <form action="${pageContext.request.contextPath}/affecter" method="get" class="search-bar">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" placeholder="Rechercher par code employé ou code lieu..." value="${param.keyword}">
            <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Rechercher</button>
        </form>
    </div>


    <div class="card">
        <table>
            <thead>
                <tr><th>Employé</th><th>Lieu</th><th>Date</th><th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${affectations}">
                    <tr>
                        <td>${a.employe.nom} ${a.employe.prenom} (${a.id.codeemp})</td>
                        <td>${a.lieu.designation} (${a.id.codelieu})</td>
                        <td>${a.id.date}</td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/affecter?action=edit&codeemp=${a.id.codeemp}&codelieu=${a.id.codelieu}&date=${a.id.date}" class="btn btn-warning"><i class="fas fa-edit"></i></a>
                            <a href="${pageContext.request.contextPath}/affecter?action=delete&codeemp=${a.id.codeemp}&codelieu=${a.id.codelieu}&date=${a.id.date}" class="btn btn-danger" onclick="return confirm('Supprimer cette affectation ?')"><i class="fas fa-trash-alt"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty affectations}">
                    <tr><td colspan="4" style="text-align:center; padding:30px;">Aucune affectation trouvée.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

</div>
</body>
</html> -->




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Affectations</title>
    <!-- Lien vers le nouveau CSS (style amélioré) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Styles supplémentaires pour cette page uniquement */
        .search-wrapper {
            display: flex;
            align-items: center;
            gap: 12px;
            background: #fff;
            padding: 4px 4px 4px 18px;
            border-radius: 50px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.04);
            border: 1.5px solid #e2e8f0;
            transition: all 0.2s;
        }
        .search-wrapper:focus-within {
            border-color: #3b82f6;
            box-shadow: 0 0 0 4px rgba(59,130,246,0.1);
        }
        .search-wrapper input {
            border: none;
            padding: 12px 0;
            font-size: 1rem;
            flex: 1;
            background: transparent;
            outline: none;
        }
        .search-wrapper button {
            background: #1e293b;
            border: none;
            color: #fff;
            padding: 10px 20px;
            border-radius: 40px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.2s;
            font-size: 0.95rem;
        }
        .search-wrapper button:hover {
            background: #0f172a;
        }
        .search-wrapper i {
            color: #94a3b8;
        }
        .table-responsive {
            overflow-x: auto;
            border-radius: 16px;
        }
        .badge {
            display: inline-block;
            background: #e9edf2;
            color: #1e293b;
            padding: 4px 10px;
            border-radius: 20px;
            font-size: 0.8rem;
            font-weight: 500;
        }
    </style>
</head>
<body>
<div class="container">

    <!-- En-tête avec navigation -->
    <div class="card" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 12px;">
        <h1><i class="fas fa-link" style="color:#3b82f6;"></i> Gestion des Affectations</h1>
        <div style="display: flex; flex-wrap: wrap; gap: 8px;">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">
                <i class="fas fa-home"></i> Accueil
            </a>
            <a href="${pageContext.request.contextPath}/employe?action=list" class="btn btn-secondary">
                <i class="fas fa-users"></i> Employés
            </a>
            <a href="${pageContext.request.contextPath}/lieu?action=list" class="btn btn-secondary">
                <i class="fas fa-map-pin"></i> Lieux
            </a>
            <a href="${pageContext.request.contextPath}/affecter?action=add" class="btn btn-success">
                <i class="fas fa-plus"></i> Ajouter
            </a>
        </div>
    </div>

    <!-- Messages -->
    <c:if test="${not empty sessionScope.message}">
        <div class="message message-${sessionScope.messageType}">
            <i class="fas fa-${sessionScope.messageIcon}"></i>
            <span>${sessionScope.message}</span>
        </div>
        <% session.removeAttribute("message"); session.removeAttribute("messageType"); session.removeAttribute("messageIcon"); %>
    </c:if>

    <!-- Barre de recherche avec auto-submit -->
    <div class="card">
        <form action="${pageContext.request.contextPath}/affecter" method="get" id="searchForm" class="search-wrapper">
            <input type="hidden" name="action" value="search">
            <i class="fas fa-search"></i>
            <input type="text" name="keyword" id="keywordInput" placeholder="Rechercher par code employé ou code lieu..."
                   value="${param.keyword}" autocomplete="off">
            <button type="submit"><i class="fas fa-arrow-right"></i> Rechercher</button>
        </form>
    </div>

    <!-- Tableau des affectations -->
    <div class="card">
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Employé</th>
                        <th>Lieu</th>
                        <th>Date</th>
                        <th style="text-align:center;">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${affectations}">
                        <tr>
                            <td><span class="badge">${a.id.codeemp}</span> ${a.employe.nom} ${a.employe.prenom}</td>
                            <td><span class="badge">${a.id.codelieu}</span> ${a.lieu.designation}</td>
                            <td><span class="badge">${a.id.date}</span></td>
                            <td class="actions" style="justify-content:center;">
                                <a href="${pageContext.request.contextPath}/affecter?action=edit&codeemp=${a.id.codeemp}&codelieu=${a.id.codelieu}&date=${a.id.date}" 
                                   class="btn btn-warning btn-sm"><i class="fas fa-edit"></i></a>
                                <a href="${pageContext.request.contextPath}/affecter?action=delete&codeemp=${a.id.codeemp}&codelieu=${a.id.codelieu}&date=${a.id.date}" 
                                   class="btn btn-danger btn-sm" 
                                   onclick="return confirm('Supprimer cette affectation ?')"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty affectations}">
                        <tr>
                            <td colspan="4" style="text-align:center; padding:40px; color:#94a3b8;">
                                <i class="fas fa-inbox" style="font-size:2.5rem; display:block; margin-bottom:12px;"></i>
                                Aucune affectation trouvée.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

</div>

<!-- JavaScript pour la recherche automatique (sans bouton) -->
<script>
    (function() {
        const form = document.getElementById('searchForm');
        const input = document.getElementById('keywordInput');
        let timeout = null;

        // Fonction pour soumettre le formulaire après un délai (debounce)
        function autoSubmit() {
            clearTimeout(timeout);
            timeout = setTimeout(function() {
                form.submit();
            }, 300); // délai de 300ms après la dernière frappe
        }

        // Écouteur d'événement sur le champ de recherche
        input.addEventListener('input', autoSubmit);

        // Si le formulaire est soumis par le bouton, on annule le debounce pour éviter double soumission
        form.addEventListener('submit', function(e) {
            clearTimeout(timeout);
        });
    })();
</script>

</body>
</html>