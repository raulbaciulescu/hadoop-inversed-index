FROM bde2020/hadoop-base:2.0.0-hadoop3.2.1-java8

MAINTAINER Ivan Ermilov <ivan.s.ermilov@gmail.com>

COPY InvertedIndex-1.jar /opt/hadoop/applications/InvertedIndex.jar

ENV JAR_FILEPATH="/opt/hadoop/applications/InvertedIndex.jar"
ENV CLASS_TO_RUN="Main"
ENV PARAMS="/input /output18"

ADD run.sh /run.sh
RUN chmod a+x /run.sh

CMD ["/run.sh"]
