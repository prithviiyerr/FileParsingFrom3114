// On my honor:
//
// - I have not discussed the Java language code in my program with
// anyone other than my instructor or the teaching assistants
// assigned to this course.
//
// - I have not used Java language code obtained from another student,
// or any other unauthorized source, including the Internet, either
// modified or unmodified.
//
// - If any Java language code or documentation used in my program
// was obtained from another source, such as a text book or course
// notes, that has been clearly noted with a proper citation in
// the comments of my program.
//
// - I have not designed this program in such a way as to defeat or
// interfere with the normal operation of the supplied grading code.
//
// prithvi iyer
// prithvii

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GISParser {

	public static void main(String[] args) throws IOException {
		if (args[0].equals("-index")) {
			RandomAccessFile r = new RandomAccessFile(args[1], "r");
			FileWriter fin = new FileWriter(args[2]);
			long os = r.getFilePointer();
			String s = r.readLine();
			os = r.getFilePointer();
			s = r.readLine();
			fin.write("\n");
			fin.write("\n");
			
			do {
				ParseLine p = new ParseLine(s);
				String name = p.getFeatName();
				
				fin.write(Long.toString(os)+" "+name+"\n");
				os = r.getFilePointer();
				s = r.readLine();
				
				
			}
			while (s != null);
			r.close();	
			fin.write("\n");
			fin.close();
					
		}
		
		if (args[0].equals("-search")) {
			String command;
			String theOffset;
			int count = 1;
			
			RandomAccessFile ind = new RandomAccessFile(args[1], "r");
			RandomAccessFile search = new RandomAccessFile(args[2], "r");
			long fLength = ind.length();
			String s = search.readLine();
			FileWriter fin = new FileWriter(args[3]);
			
			do {
				if (s.contains("quit")) {
					fin.write(String.valueOf(count) + ": quit\n");
					fin.write("   Exiting\n");
					break;
				}
				if (s.contains(";")) {
					s = search.readLine();
					continue;
				}
				Records line = new Records(s);
				theOffset = line.getTheOffset();
				
				OffSet comp = new OffSet(Integer.parseInt(theOffset));
				command = line.getCommand();
				
				
				if (command.equals("show_name")) {
					fin.write(String.valueOf(count) + ": " + command + "  \t" + theOffset + "\n");
					if (comp.offsCateg(ind, fLength) != "nothing") {
						fin.write("   " + comp.offsCateg(ind, fLength) + "\n");
					}
					else {
						ind.seek(Integer.parseInt(theOffset));
						String current = ind.readLine();
						ParseLine l = new ParseLine(current);
						fin.write("   " + l.getFeatName() + "\n");
					}
				}
				
				if (command.equals("show_elevation")) {
					fin.write(String.valueOf(count) + ": " + command + "  \t" + theOffset + "\n");
					
					if (comp.offsCateg(ind, fLength) != "nothing") {
						fin.write("   " + comp.offsCateg(ind, fLength) + "\n");
					}
					else {
						ind.seek((Integer.parseInt(theOffset)));
						String current = ind.readLine();
						ParseLine l = new ParseLine(current);
						if (l.getElevationF().equals("")) {
							fin.write("   Elevation is not given\n");
						}
						else {
							fin.write("   " + l.getElevationF()+"\n");
						}
					}
					
					
				}
				
				if (command.equals("show_latitude")) {
					fin.write(String.valueOf(count) + ": " + command + "  \t" + theOffset + "\n");
					
					if (comp.offsCateg(ind, fLength) != "nothing") {
						fin.write("   " + comp.offsCateg(ind, fLength) + "\n");
												
		
					}
					else {
						ind.seek(Integer.parseInt(theOffset));
						String current = ind.readLine();
						ParseLine l = new ParseLine(current);
						if (l.getLat().equals("") || l.getLat().equals("Unknown")) {
							fin.write("   Coordinate is not given\n");
						}
						else {
							String a, b, c, d;
							if (l.getLat().substring(0,1).equals("0")) {
								a = l.getLat().substring(1, 2);
							}
							
							else { 
								a = l.getLat().substring(0, 2);
							}
							
							if (l.getLat().substring(2, 3).equals("0")) {
								b = l.getLat().substring(3, 4);
							}
							else {
								b = l.getLat().substring(2, 4);
							}
							if (l.getLat().substring(4, 5).equals("0")) {
								c = l.getLat().substring(5, 6);
							}
							else {
								c = l.getLat().substring(4, 6);
							}
							if (l.getLat().substring(6).equals("S")) {
								d = "South";
							}
							else {
								d = "North";
							}
							fin.write("   "+a+"d "+b+"m "+c+"s "+d+"\n");
						}
					}
				
				}
					
				
				
				if (command.equals("show_longitude")) {
					fin.write(String.valueOf(count) + ": " + command + "  \t" + theOffset + "\n");
					
					if (comp.offsCateg(ind, fLength) != "nothing") {
						
						fin.write("   " + comp.offsCateg(ind, fLength) + "\n");						
						
					}
					else {
						ind.seek(Integer.parseInt(theOffset));
						String current = ind.readLine();
						ParseLine l = new ParseLine(current);
						if (l.getLat().equals("") || l.getLat().equals("Unknown")) {
							fin.write("   Coordinate is not given\n");
						}
						else {
							String a, b, c, d; 
							if (l.getLong().substring(0, 1).equals("0") && l.getLong().substring(1, 2).equals("0")) { 
								a = l.getLong().substring(2, 3); 
								}
							else if (l.getLong().substring(0,1).equals("0")) { 
								a = l.getLong().substring(1, 3); 
								} 
							else { 
								a = l.getLong().substring(0, 3); 
								} 
							if(l.getLong().substring(3, 4).equals("0")) { 
								b = l.getLong().substring(4, 5);
									  } 
							else { 
								b = l.getLong().substring(3, 5); 
								} 
							if (l.getLong().substring(5,6).equals("0")) { 
								c = l.getLong().substring(6, 7); 
								} 
							else { 
								c =l.getLong().substring(5, 7); 
								} 
							if (l.getLong().substring(7).equals("E")) { 
									d = "East"; 
									} 
							else { 
								d = "West"; 
								}
									 
							fin.write("   "+a+"d "+b+"m "+c+"s "+d+"\n");
						}
						
					}
				
				
					
				  
				}
				
				s = search.readLine();
				count++;
				
			} while (s != null);
			ind.close();
			search.close();
			fin.close();
			
			
			
			
			
		}
		

	}
	
}


  