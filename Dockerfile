FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

# Descarga las dependencias y compila la aplicación
COPY pom.xml ./
RUN mvn -B dependency:go-offline
COPY src ./src
RUN set -eux; \
    mvn -B clean package; \
    if [ -f target/PrestadorSalud.war ]; then \
        :; \
    else \
        WAR_PATH="$(find target -maxdepth 1 -type f -name '*.war' | head -n 1)"; \
        if [ -z "$WAR_PATH" ]; then echo "WAR artifact not found" >&2; exit 1; fi; \
        mv "$WAR_PATH" target/PrestadorSalud.war; \
    fi

FROM quay.io/wildfly/wildfly:latest
# Copia el WAR generado en la etapa de construcción a la carpeta de despliegue
COPY --from=builder /build/target/PrestadorSalud.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
# Arranca WildFly escuchando en todas las interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
