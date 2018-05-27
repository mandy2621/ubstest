/**
 * 
 */
package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author www
 *
 */
public class BerlinClock implements TimeConverter{
	
	private String theTime;
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String NO_TIME_ERROR = "No time provided";
    private static final String INVALID_TIME_ERROR = "Invalid time provided.";
    private static final String NUMERIC_TIME_ERROR = "Time values must be numeric.";
    
    
    public BerlinClock(String time) {

        if(time == null) throw new IllegalArgumentException(NO_TIME_ERROR);

        String[] times = time.split(":", 3);

        if(times.length != 3) throw new IllegalArgumentException(INVALID_TIME_ERROR);

        int hours, minutes, seconds = 0;
        
        try {
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
            seconds = Integer.parseInt(times[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMERIC_TIME_ERROR);
        }

        if (hours < 0 || hours > 23) throw new IllegalArgumentException("Hours out of bounds.");
        if (minutes < 0 || minutes > 59) throw new IllegalArgumentException("Minutes out of bounds.");
        if (seconds < 0 || seconds > 59) throw new IllegalArgumentException("Seconds out of bounds.");

        this.theTime = processTime(hours, minutes, seconds);
    }


    /**
     * 
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
	public String processTime(int hours, int minutes, int seconds) {
		 	String line1 = (seconds % 2 == 0) ? "Y" : "0";
	        String line2 = rowString(hours / 5, 4, "R");
	        String line3 = rowString(hours % 5, 4, "R");
	        String line4 = rowString(minutes / 5, 11, "Y").replaceAll("YYY", "YYR");
	        String line5 = rowString(minutes % 5, 4, "Y");

	        return String.join(NEW_LINE, Arrays.asList(line1, line2, line3, line4, line5));

	}


	/**
	 * 
	 * @param i
	 * @param j
	 * @param type
	 * @return
	 */
	public String rowString(int i, int j, String type) {
		int unlighted = j - i;
        String lighted = String.join("", Collections.nCopies(i, type));
        String unlightedd = String.join("", Collections.nCopies(unlighted, "0"));

        return lighted + unlightedd;
	}
	
	
	 /**
	  * 
	  */
    public void printClockTime() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.theTime;
    }


    
	@Override
	public String convertTime(String aTime) {
		 Integer h1 = Integer.valueOf((aTime.substring(0, 2)));
    	 Integer m1 = Integer.valueOf((aTime.substring(3, 5)));
    	 Integer s1 = Integer.valueOf((aTime.substring(6, 8)));
    	 return processTime(h1,m1,s1);
		
	}

}
