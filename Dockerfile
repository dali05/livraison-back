# Étape 1 : Utiliser une image JDK légère
FROM openjdk:17-jdk-alpine

# Étape 2 : Définir les variables d'environnement
ENV APP_JAR=livraison-back.jar
ENV KEYSTORE_PASSWORD=mysecretpassword

# Étape 3 : Créer un répertoire pour l'application
WORKDIR /app

# Étape 4 : Générer le keystore SSL
RUN keytool -genkeypair -alias livraison-back -keyalg RSA -keysize 2048 -storetype PKCS12 \
    -keystore /app/keystore.p12 -validity 3650 \
    -dname "CN=livraison-back" \
    -storepass ${KEYSTORE_PASSWORD}

# Étape 5 : Copier l'application dans l'image
COPY target/${APP_JAR} /app/${APP_JAR}

# Étape 6 : Exposer le port HTTPS
EXPOSE 8443

# Étape 7 : Lancer l'application avec SSL activé
CMD ["java", "-jar", "/app/livraison-back.jar", "--server.port=8443", "--server.ssl.key-store=/app/keystore.p12", "--server.ssl.key-store-password=mysecretpassword", "--server.ssl.key-store-type=PKCS12"]
