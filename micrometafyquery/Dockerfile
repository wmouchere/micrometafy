FROM tomcat:9-jre8

COPY target/micrometafy-query-1.0.war /usr/local/tomcat/webapps/micrometafy-query.war
COPY apikeys.yml apikeys.yml

EXPOSE 8080

CMD ["catalina.sh", "run"]