# spring-cas
Spring CAS Demo App

keytool -v -genkeypair -alias tomcat -keyalg RSA -keypass 123 -storepass 123 -keystore tomcat.keystore -validity 365

keytool -v -certreq -alias tomcat -keystore tomcat.keystore -storepass 123 -file tomcat.csr

keytool -v -exportcert -alias tomcat -keystore tomcat.keystore -storepass 123 -file tomcat.cer

keytool -v -importcert -alias tomcat -file tomcat.cer -keystore jdk1.8.0_171\jre\lib\security\cacerts -storepass 123
