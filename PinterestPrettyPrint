import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Assumptions:
//			For any pair of records, they have different start_time.
// 			For any pair of records, they have different end_time.
//			If record A is within the time range of record B, then A has a higher nesting level than B.
//			Records are sorted in increasing order of start_time.
// Reasoning:
//			No two functions start at exactly the same time.
//			No two functions end at exactly the same time.
//			If B call A, then A starts later than B and ends earlier than B; A has a higher nesting level.
//			Once a function is called, a record for this function call is generated. So records are sorted
//				in ascending order of start_time.
// Note:
//			The output for the total time spent in each function call is formatted into one decimal point.

public class PinterestPrettyPrint {
	public static void main(String[] args) throws IOException {
		// Read the input from a file.
		BufferedReader reader = new BufferedReader(new FileReader(
				"/home/reacher/workspace/euler/src/euler/PinterestPrettyPrint.txt"));
		ArrayList<StopwatchRecord> records = new ArrayList<StopwatchRecord>();
		// The pattern used to fetch key, start_time and end_time from a line of String.
		Pattern p = Pattern.compile("(key=')(\\w*)(',start_time=)(\\d.*)(,end_time=)(\\d.*)(\\)|\\),)");
		String str = reader.readLine();
		int i = 0;
		int j = 0;
		// The current size of ArrayList records.
		int records_size = 0;
		
		while (str != null) {
			Matcher m = p.matcher(str);
			if (m.find()) {
				StopwatchRecord record = new StopwatchRecord(
						m.group(2), Double.parseDouble(m.group(4)), Double.parseDouble(m.group(6)));
				// Update nesting level for the current record.
				for (i = records_size - 1; i >= 0; i--) {
					if (record.withinTimeRange(records.get(i))) {
						record.level = records.get(i).level + 1;
						break;
					}
				}
				records.add(record);
				records_size++;
			}
			str = reader.readLine();
		}
		
		// Print the output according to the given rule.
		for (i = 0; i < records_size; i++) {
			StopwatchRecord record = records.get(i);
			System.out.print(record.start_time);
			for (j = 0; j < record.level; j++) {
				System.out.print("    ");
			}
			System.out.print("[" + String.format("%.1f", (record.end_time - record.start_time)) + "]");
			System.out.println(record.key);
		}
	}
}

class StopwatchRecord {
	String key;
	double start_time;
	double end_time;
	int level;  //Nesting level.
	
	StopwatchRecord(String k, double s_time, double e_time) {
		key = k;
		start_time = s_time;
		end_time = e_time;
		level = 1;
	}
	
	// Check if this StopwatchRecord is within the time range of other StopwatchRecord.
	boolean withinTimeRange(StopwatchRecord other) {
		if (start_time > other.start_time && end_time <= other.end_time) {
			return true;
		}
		return false;
	}
}
