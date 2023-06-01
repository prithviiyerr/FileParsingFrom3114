
public class ParseLine {
	private String featName;
	private String primLatDms;
	private String primLongDms;
	private String elevationM;
	private String elevationF;
	
	
	public ParseLine(String line) {
		String[] lineParser = line.split("\\|");
		featName = lineParser[1];
		primLatDms = lineParser[7];
		primLongDms = lineParser[8];
		elevationM = lineParser[15];
		elevationF = lineParser[16];
		
	}
	
	public String getFeatName() {
		return featName;
	}
	
	public String getLat() {
		return primLatDms;
	}
	
	public String getLong() {
		return primLongDms;
	}
	
	public String getElevationM() {
		return elevationM;
	}
	
	public String getElevationF() {
		return elevationF;
	}

}

