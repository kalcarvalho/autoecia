FROM tomcat:latest

EXPOSE 8080
#COPY dist/autoecia.war /usr/local/tomcat/webapps/autoecia/autoecia.war

ADD ./dist/autoecia.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]