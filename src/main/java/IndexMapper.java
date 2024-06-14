import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class IndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final Text wordAtFileNameKey = new Text();
    private long lineNumber = 0;
    private final static Set<String> stopWords = new HashSet<>();

    @Override
    public void setup(Context context) throws IOException {
        URI[] cacheFiles = context.getCacheFiles();

        if (cacheFiles != null && cacheFiles.length > 0) {
            try {
                String line;
                FileSystem fs = FileSystem.get(context.getConfiguration());
                Path getFilePath = new Path(cacheFiles[0].toString());
                BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(getFilePath)));

                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(" ");
                    Collections.addAll(stopWords, words);
                }
            } catch (Exception e) {
                System.out.println("Unable to read the File");
                System.exit(1);
            }
        }
    }

    @Override
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        StringTokenizer tokenizer = new StringTokenizer(value.toString());
        lineNumber++;
        while (tokenizer.hasMoreTokens()) {
            String fileName = split.getPath().getName().split("\\.")[0];
            String word = tokenizer.nextToken();
            if (!stopWords.contains(word)) {
                wordAtFileNameKey.set(word + "@" + fileName);
                context.write(wordAtFileNameKey, new Text(String.valueOf(lineNumber)));
            }
        }
    }
}
