Hadoop Project: Using the already configured Hadoop VM (either VM or docker version) from above, 
together with the development environment write an inversed index app for
a set of books as data. 
In order to build the inversed index you need to account for a stop word list
(words that will not be indexed by the application. Ex: and, or, how, so, etc).
These stop words will be read by the application from a text file (stopwords.txt)

An inversed index contains for each distinct unique word, 
a list of files containing the given word with its location within the file
(line number in our case). When running the application you should have a small
cluster/cloud of at least two nodes build from VMs/docker eventually a 
larger cluster build from all your individual VMs.

Ex: word: (file#1, line#1, line#2, ...) (file#4, line#1, line#2, ...) ...)



```
docker build -t inverted-index ./submit
docker run --network docker-hadoop_default --env-file hadoop.env inverted-index
docker cp angajati.csv 0c63b5e96e54971ac728722a3d81353d188770ace7c67a9fd0b20abf5f564379:/angajati.csv
hdfs dfs -cat /output17/part-r-00000
```