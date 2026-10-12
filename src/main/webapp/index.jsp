<!-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Affectations - Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Styles spécifiques à la page d'accueil */
        .hero {
            text-align: center;
            padding: 50px 20px 30px;
        }
        .hero h1 {
            font-size: 2.8rem;
            color: #1a2b4c;
            margin-bottom: 10px;
        }
        .hero p {
            font-size: 1.2rem;
            color: #555;
            margin-bottom: 30px;
        }
        .menu-cards {
            display: flex;
            justify-content: center;
            gap: 30px;
            flex-wrap: wrap;
            margin-top: 20px;
        }
        .menu-card {
            background: #fff;
            border-radius: 16px;
            padding: 30px 25px;
            width: 220px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.08);
            text-align: center;
            transition: transform 0.2s, box-shadow 0.2s;
            text-decoration: none;
            color: #1a2b4c;
        }
        .menu-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 12px 28px rgba(0,0,0,0.15);
        }
        .menu-card i {
            font-size: 3.5rem;
            color: #1a2b4c;
            margin-bottom: 15px;
            display: block;
        }
        .menu-card h3 {
            font-size: 1.4rem;
            margin-bottom: 8px;
        }
        .menu-card p {
            font-size: 0.95rem;
            color: #6c757d;
            margin: 0;
        }
        .footer {
            text-align: center;
            margin-top: 40px;
            color: #6c757d;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="hero">
        <h1><i class="fas fa-building" style="color:#1a2b4c;"></i> Gestion des Affectations</h1>
        <p>Bienvenue sur l'application de gestion des employés, lieux et affectations.</p>
        <p style="font-size:1rem; color:#888;">Choisissez une section ci-dessous pour commencer.</p>
    </div>

    <div class="menu-cards">
        <a href="${pageContext.request.contextPath}/employe?action=list" class="menu-card">
            <i class="fas fa-users"></i>
            <h3>Employés</h3>
            <p>Gérer la liste des employés</p>
        </a>
        <a href="${pageContext.request.contextPath}/lieu?action=list" class="menu-card">
            <i class="fas fa-map-pin"></i>
            <h3>Lieux</h3>
            <p>Gérer les lieux d'affectation</p>
        </a>
        <a href="${pageContext.request.contextPath}/affecter?action=list" class="menu-card">
            <i class="fas fa-link"></i>
            <h3>Affectations</h3>
            <p>Gérer les affectations</p>
        </a>
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
    <title>Gestion des Affectations – Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* ---- Styles spécifiques à l’accueil ---- */
        .hero {
            text-align: center;
            padding: 40px 20px 20px;
        }
        .hero h1 {
            font-size: 2.8rem;
            font-weight: 700;
            color: #0f172a;
            margin-bottom: 8px;
        }
        .hero h1 i {
            color: #3b82f6;
        }
        .hero p {
            font-size: 1.2rem;
            color: #475569;
            margin-bottom: 6px;
        }
        .hero .sub {
            font-size: 1rem;
            color: #94a3b8;
        }

        .menu-cards {
            display: flex;
            justify-content: center;
            gap: 30px;
            flex-wrap: wrap;
            margin: 30px 0 10px;
        }
        .menu-card {
            background: #fff;
            border-radius: 20px;
            padding: 32px 24px 28px;
            width: 220px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.06);
            border: 1px solid rgba(255, 255, 255, 0.5);
            text-align: center;
            transition: all 0.25s ease;
            text-decoration: none;
            color: #0f172a;
            backdrop-filter: blur(2px);
        }
        .menu-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 16px 40px rgba(0, 0, 0, 0.10);
            border-color: #e2e8f0;
        }
        .menu-card i {
            font-size: 3.8rem;
            color: #3b82f6;
            margin-bottom: 12px;
            display: block;
        }
        .menu-card h3 {
            font-size: 1.4rem;
            font-weight: 600;
            margin-bottom: 6px;
        }
        .menu-card p {
            font-size: 0.95rem;
            color: #64748b;
            margin: 0;
        }

        .footer {
            text-align: center;
            margin-top: 50px;
            padding: 20px 0 10px;
            border-top: 1px solid #e9edf2;
            color: #94a3b8;
            font-size: 0.9rem;
        }
        .footer i {
            color: #3b82f6;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .hero h1 {
                font-size: 2rem;
            }
            .menu-card {
                width: 100%;
                max-width: 300px;
            }
        }
    </style>
</head>
<body>
<div class="container">

    <!-- Affichage d’un éventuel message de session (pour une redirection) -->
    <c:if test="${not empty sessionScope.message}">
        <div class="message message-${sessionScope.messageType}">
            <i class="fas fa-${sessionScope.messageIcon}"></i>
            <span>${sessionScope.message}</span>
        </div>
        <% session.removeAttribute("message");
           session.removeAttribute("messageType");
           session.removeAttribute("messageIcon"); %>
    </c:if>

    <!-- En‑tête -->
    <div class="hero">
        <h1><i class="fas fa-building"></i> Gestion des Affectations</h1>
        <p>Bienvenue sur l’application de gestion des employés, lieux et affectations.</p>
        <p class="sub"><i class="fas fa-arrow-down" style="font-size:0.9rem; margin-right:6px;"></i> Choisissez une section pour commencer</p>
    </div>

    <!-- Cartes de navigation -->
    <div class="menu-cards">
        <a href="${pageContext.request.contextPath}/employe?action=list" class="menu-card">
            <i class="fas fa-users"></i>
            <h3>Employés</h3>
            <p>Gérer la liste des employés</p>
        </a>
        <a href="${pageContext.request.contextPath}/lieu?action=list" class="menu-card">
            <i class="fas fa-map-pin"></i>
            <h3>Lieux</h3>
            <p>Gérer les lieux d’affectation</p>
        </a>
        <a href="${pageContext.request.contextPath}/affecter?action=list" class="menu-card">
            <i class="fas fa-link"></i>
            <h3>Affectations</h3>
            <p>Gérer les affectations</p>
        </a>
    </div>


  
</div>
</body>
</html>