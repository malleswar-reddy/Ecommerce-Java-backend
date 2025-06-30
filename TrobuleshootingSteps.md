## Troubleshooting Steps

### 1. Check the Logs

##The docker name must Start with Captital letter, if not we will get an error.
##To build the "mvn clean install" without testcases we have to use -DskipTests command  "mvn clean install -DskipTests"
##If you are using the "mvn clean install -DskipTests" command, make sure that the "skipTests" property is set in the pom.xml file.