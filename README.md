## Hadoop Project


Requirements: Using the already configured Hadoop VM (either VM or docker version) from above, 
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
hdfs dfs -cat /output18/part-r-00000
```

docker cp book2.txt 8ce199255190f49ed0e19f61028e742c9940d031d83664d135fa5d4f6d72f0c0:/book2.txt

hdfs dfs -put stopwords.txt /

hdfs dfs -copyFromLocal /output18/part-r-00000 D:\Projects\inverted-index
hdfs dfs -get /input/input1.txt D:\Projects\inverted-index\docker-hadoop

rm part-r-00000
hadoop fs -get /output18/part-r-00000 /
docker cp 8ce199255190f49ed0e19f61028e742c9940d031d83664d135fa5d4f6d72f0c0:/part-r-00000 D:\Projects\inverted-index