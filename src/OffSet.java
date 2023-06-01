
import java.io.IOException;
import java.io.RandomAccessFile;

public class OffSet {
	private int offset;
	
	public OffSet(int o) {
		this.offset = o;
	}
	
	public String offsCateg(RandomAccessFile f, long fLength) throws IOException {
		
		if (offset < 0) {
			String a = "Offset is not positive";
			return a;
		}
		else if (offset == 0 ) {
			return "nothing";
		}
		else if (offset > fLength) {
			String b = "Offset is too large";
			return b;
					
		}
		f.seek((long)offset - 1);
		if (offset >= 0 && f.read() != '\n') {
			String c = "Offset is unaligned";
			return c;
		}
		
		return "nothing";
	}
	
	

}
