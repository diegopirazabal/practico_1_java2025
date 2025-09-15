FROM quay.io/wildfly/wildfly:latest

# Copia el WAR generado por Maven a la carpeta de despliegue
COPY target/PrestadorSalud.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

# Arranca WildFly escuchando en todas las interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
