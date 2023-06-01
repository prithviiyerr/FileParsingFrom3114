
public class Records {
	private String command;
	private String theOffset;
	
	public Records(String line) {
		String[] splitLine = line.split("\\t");
		command = splitLine[0];
		theOffset = splitLine[1];
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getTheOffset() {
		return theOffset;
	}
	
	

}
