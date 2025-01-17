package com.shpp.cs.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */


public class NameSurferEntry implements NameSurferConstants {
    private String name;
    private int [] rank = new int [NDECADES];

	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
       String [] values = line.split(" ");
       name = values[0];
       for (int i = 1; i < values.length; i++){
           if (values[i] != null) {
               rank[i - 1] = Integer.parseInt(values[i]);
           }
       }
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return rank[decade];
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String nameResult = "Name: " + getName();
        StringBuilder rankResult = new StringBuilder(" Rank: ");
        for (int i= 0; i < rank.length; i++)
            rankResult.append(" ").append(getRank(i));
        return  nameResult + rankResult;
    }
}

