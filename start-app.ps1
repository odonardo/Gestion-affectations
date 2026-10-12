Write-Host "=== Lancement de l'application ===" -ForegroundColor Green

# 1. Vérifier si MySQL est démarré
$service = Get-Service -Name MySQL80 -ErrorAction SilentlyContinue
if ($service -and $service.Status -eq 'Running') {
    Write-Host "MySQL est déjà démarré." -ForegroundColor Yellow
} else {
    Write-Host "Démarrage de MySQL..." -ForegroundColor Cyan
    Start-Service -Name MySQL80 -ErrorAction SilentlyContinue
    if ($?) {
        Write-Host "MySQL démarré avec succès." -ForegroundColor Green
    } else {
        Write-Host "Impossible de démarrer MySQL. Vérifiez que le service existe." -ForegroundColor Red
        exit 1
    }
}

# 2. Démarrer Tomcat
$tomcatBin = "C:\Tomcat9\apache-tomcat-9.0.119\bin"
if (Test-Path "$tomcatBin\startup.bat") {
    Write-Host "Démarrage de Tomcat..." -ForegroundColor Cyan
    Start-Process -FilePath "$tomcatBin\startup.bat" -WindowStyle Normal
    Write-Host "Tomcat lancé (fenêtre séparée)." -ForegroundColor Green
} else {
    Write-Host "Impossible de trouver startup.bat dans $tomcatBin" -ForegroundColor Red
    exit 1
}

# 3. Ouvrir le navigateur (attendre que Tomcat démarre)
Start-Sleep -Seconds 5
Start-Process "http://localhost:8080/projet_hibernate/employe?action=list"
Write-Host "Application ouverte dans le navigateur." -ForegroundColor Green
