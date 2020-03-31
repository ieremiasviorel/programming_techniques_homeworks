package model;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Processor {

	private List<MonitoredData> monitoredData;
	
	public Processor() {
		monitoredData = DataReader.readFromFile();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(MonitoredData m : monitoredData) {
			sb.append(m.toString() + "END\n");
		}
		return sb.toString();
	}
	
	public int countDistinctDays() {
		return monitoredData.stream().collect(Collectors.groupingBy(m -> ((MonitoredData)m).getStartTime().getDayOfYear())).size();
	}
	
	public Map<String, Long> countNoActivities() {
		return monitoredData.stream().collect(Collectors.groupingBy(m -> ((MonitoredData)m).getActivityLabel(), Collectors.counting())); 
	}
	
	public Map<Integer, Map<String, Long>> activitiesPerDay() {
		return monitoredData.stream().collect(Collectors.groupingBy(m -> ((MonitoredData)m).getStartTime().getDayOfYear(), (Collectors.groupingBy(m -> ((MonitoredData)m).getActivityLabel(), Collectors.counting())))); 
	}
	
	public Map<String, Duration> totalTimePerActivity() {
		
		return monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> {
			MonitoredData d = (MonitoredData)m;
			return d.getEndTime().getHour() * 3600 - d.getStartTime().getHour() * 3600 + d.getEndTime().getMinute() * 60 - d.getStartTime().getMinute() * 60
					+ d.getEndTime().getSecond() - d.getStartTime().getSecond();
		}, Integer::sum)).entrySet().stream().filter(m -> m.getValue().intValue() > 10 * 3600).collect(Collectors.toMap(m -> m.getKey(), m -> Duration.ofSeconds(m.getValue())));
	}
	
	public List<String> listShortActivities() {
		
		Map<String, Long> map1 = monitoredData.stream().collect(Collectors.groupingBy(m -> ((MonitoredData)m).getActivityLabel(), Collectors.counting()));
		
		Map<String, Integer> map2 = monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> {
			if(((MonitoredData)m).getActivityDurationInSeconds() < 5 * 60) return 1; else return 0;
			}, Integer::sum));
		
		return map1.entrySet().stream().filter(m -> map2.get(m.getKey()) / m.getValue() >= 0.9).map(m -> m.getKey()).collect(Collectors.toList());
	}
}