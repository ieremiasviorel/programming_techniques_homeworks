package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
	
	public static List<MonitoredData> readFromFile() {
		
		FileReader fr = null;
		try {
			fr = new FileReader("Activities.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();
		
		while(line != null) {
			String s[] = line.split("		");
			monitoredData.add(new MonitoredData(LocalDateTime.parse(s[0], formatter), LocalDateTime.parse(s[1], formatter), s[2].replace("\t", "")));
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return monitoredData;
	}
}