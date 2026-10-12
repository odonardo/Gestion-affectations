<!-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire Lieu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="container">
    <div class="card" style="max-width:600px; margin:20px auto;">
        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:20px;">
            <h2><i class="fas fa-map-pin"></i> <c:if test="${lieu != null}">Modifier</c:if><c:if test="${lieu == null}">Ajouter</c:if> un lieu</h2>
            <a href="${pageContext.request.contextPath}/lieu?action=list" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Retour</a>
        </div>

        <c:if test="${not empty sessionScope.message}">
            <div class="message message-${sessionScope.messageType}">
                <i class="fas fa-${sessionScope.messageIcon}"></i>
                <span>${sessionScope.message}</span>
            </div>
            <% session.removeAttribute("message"); session.removeAttribute("messageType"); session.removeAttribute("messageIcon"); %>
        </c:if>

        <form action="${pageContext.request.contextPath}/lieu" method="post">
            <input type="hidden" name="action" value="save">

            <div class="form-group">
                <label for="codelieu">Code *</label>
                <input type="text" id="codelieu" name="codelieu" value="${lieu.codelieu}" <c:if test="${lieu != null}">readonly</c:if> required placeholder="ex: L001">
            </div>
            <div class="form-group">
                <label for="designation">Désignation *</label>
                <input type="text" id="designation" name="designation" value="${lieu.designation}" required placeholder="ex: Siège social">
            </div>
            <div class="form-group">
                <label for="province">Province</label>
                <input type="text" id="province" name="province" value="${lieu.province}" placeholder="ex: Antananarivo">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-success"><i class="fas fa-save"></i> Enregistrer</button>
                <a href="${pageContext.request.contextPath}/lieu?action=list" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
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
    <title>Formulaire Lieu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        .form-container {
            max-width: 600px;
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
        .form-group input[type="text"] {
            width: 100%;
            padding: 10px 14px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 1rem;
            background: #fafafa;
            transition: border-color 0.2s;
        }
        .form-group input[type="text"]:focus {
            border-color: #1a2b4c;
            outline: none;
            box-shadow: 0 0 0 3px rgba(26, 43, 76, 0.1);
            background: #fff;
        }
        .form-group input[readonly] {
            background: #e9ecef;
            color: #6c757d;
            cursor: not-allowed;
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
                <i class="fas fa-map-pin" style="color:#1a2b4c;"></i>
                <c:if test="${lieu != null}">Modifier</c:if>
                <c:if test="${lieu == null}">Ajouter</c:if>
                un lieu
            </h2>
            <a href="${pageContext.request.contextPath}/lieu?action=list" class="btn btn-secondary" style="padding:8px 18px;">
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

        <form action="${pageContext.request.contextPath}/lieu" method="post">
            <input type="hidden" name="action" value="save">

            <div class="form-group">
                <label for="codelieu"><i class="fas fa-tag"></i> Code *</label>
                <input type="text" id="codelieu" name="codelieu" value="${lieu.codelieu}"
                       <c:if test="${lieu != null}">readonly</c:if> required
                       placeholder="ex: L001">
                <c:if test="${lieu != null}">
                    <div style="font-size:0.85rem; color:#6c757d; margin-top:5px;">(Non modifiable)</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="designation"><i class="fas fa-building"></i> Désignation *</label>
                <input type="text" id="designation" name="designation" value="${lieu.designation}" required placeholder="ex: Siège social">
            </div>

            <div class="form-group">
                <label for="province"><i class="fas fa-globe"></i> Province</label>
                <input type="text" id="province" name="province" value="${lieu.province}" placeholder="ex: Antananarivo">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-success">
                    <i class="fas fa-save"></i> Enregistrer
                </button>
                <a href="${pageContext.request.contextPath}/lieu?action=list" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Annuler
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>