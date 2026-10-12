<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Affectation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        .form-container {
            max-width: 650px;
            margin: 30px auto;
            padding: 25px 30px;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }
        .form-container h2 {
            font-size: 1.6rem;
            color: #1a2b4c;
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
            margin-bottom: 25px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: 600;
            margin-bottom: 6px;
            color: #1a2b4c;
        }
        .form-group select,
        .form-group input[type="date"] {
            width: 100%;
            padding: 10px 14px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 1rem;
            background: #fafafa;
            transition: border-color 0.2s;
        }
        .form-group select:focus,
        .form-group input[type="date"]:focus {
            border-color: #1a2b4c;
            outline: none;
            box-shadow: 0 0 0 3px rgba(26, 43, 76, 0.1);
            background: #fff;
        }
        .form-actions {
            display: flex;
            gap: 15px;
            margin-top: 25px;
        }
        .form-actions .btn {
            flex: 1;
            text-align: center;
            padding: 12px;
            font-weight: 600;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="header-actions">
            <h2>
                <i class="fas fa-link" style="color:#1a2b4c;"></i>
                <c:if test="${affecter != null}">Modifier</c:if>
                <c:if test="${affecter == null}">Ajouter</c:if>
                une affectation
            </h2>
            <a href="${pageContext.request.contextPath}/affecter?action=list" class="btn btn-secondary" style="padding:8px 18px;">
                <i class="fas fa-arrow-left"></i> Retour
            </a>
        </div>

        <!-- Messages de session -->
        <c:if test="${not empty sessionScope.message}">
            <div class="message message-${sessionScope.messageType}">
                <i class="fas fa-${sessionScope.messageIcon}"></i>
                <span>${sessionScope.message}</span>
            </div>
            <% session.removeAttribute("message");
               session.removeAttribute("messageType");
               session.removeAttribute("messageIcon"); %>
        </c:if>

        <form action="${pageContext.request.contextPath}/affecter" method="post">
            <input type="hidden" name="action" value="save">

            <!-- Champs cachés pour l'ancien ID (modification) -->
            <c:if test="${affecter != null}">
                <input type="hidden" name="oldCodeemp" value="${oldCodeemp}">
                <input type="hidden" name="oldCodelieu" value="${oldCodelieu}">
                <input type="hidden" name="oldDate" value="${oldDate}">
            </c:if>

            <div class="form-group">
                <label for="codeemp"><i class="fas fa-user"></i> Employé *</label>
                <select id="codeemp" name="codeemp" required>
                    <option value="">-- Sélectionner un employé --</option>
                    <c:forEach var="e" items="${employes}">
                        <option value="${e.codeemp}" ${affecter != null && affecter.id.codeemp == e.codeemp ? 'selected' : ''}>
                            ${e.nom} ${e.prenom} (${e.codeemp})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="codelieu"><i class="fas fa-map-pin"></i> Lieu *</label>
                <select id="codelieu" name="codelieu" required>
                    <option value="">-- Sélectionner un lieu --</option>
                    <c:forEach var="l" items="${lieux}">
                        <option value="${l.codelieu}" ${affecter != null && affecter.id.codelieu == l.codelieu ? 'selected' : ''}>
                            ${l.designation} (${l.codelieu})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="date"><i class="fas fa-calendar-alt"></i> Date *</label>
                <input type="date" id="date" name="date" value="${dateString}" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-success">
                    <i class="fas fa-save"></i> Enregistrer
                </button>
                <a href="${pageContext.request.contextPath}/affecter?action=list" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Annuler
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>