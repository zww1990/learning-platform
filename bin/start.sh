#!/bin/sh

APP_NAME=java-changepwd
APP_FILE=$APP_NAME.jar
JAVA_OPTS="-Dserver.port=8888"
JAVA_OPTS="$JAVA_OPTS -Dserver.servlet.context-path=/changepwd"
JAVA_OPTS="$JAVA_OPTS -Dspring.ldap.urls=ldap://10.2.1.114:389"
JAVA_OPTS="$JAVA_OPTS -Dspring.ldap.base=dc=bacic5i5j,dc=com"
JAVA_OPTS="$JAVA_OPTS -Dspring.ldap.username=cn=admin,dc=bacic5i5j,dc=com"
JAVA_OPTS="$JAVA_OPTS -Dspring.ldap.password=1q2w3e4r"
JAVA_OPTS="$JAVA_OPTS -Dspring.mail.host=smtp.exmail.qq.com"
JAVA_OPTS="$JAVA_OPTS -Dspring.mail.port=465"
JAVA_OPTS="$JAVA_OPTS -Dspring.mail.username=siqianwen@5i5j.com"
JAVA_OPTS="$JAVA_OPTS -Dspring.mail.password=DcAfBAJsxCvAt6NA"

nohup java $JAVA_OPTS -jar $APP_FILE &
