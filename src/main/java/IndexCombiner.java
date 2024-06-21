import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IndexCombiner extends Reducer<Text, Text, Text, Text> {

    private final Text fileAtWordLineNumber = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            int sum = Integer.parseInt(value.toString());
            int splitIndex = key.toString().indexOf("@");

            fileAtWordLineNumber.set(key.toString().substring(splitIndex + 1) + ":" + sum);
            key.set("&" + key.toString().substring(0, splitIndex) + "&");
            context.write(key, fileAtWordLineNumber);
        }
    }
}
