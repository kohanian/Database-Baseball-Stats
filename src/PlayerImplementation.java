import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerImplementation {
	
	/* This creates a string that has all player 
	 * information, and separate arrays of players.
	 * The player array, will take each component and
	 * attribute of each player and put all attributes into
	 * separate strings into the array.
	 * The batters array will be an array with the separate 
	 * players'attributes all in a single array. One player
	 * with his attributes in one array position.
	 */
	
	//Declaration of the String and Two Arrays 
	static ArrayList<Batter> batters = new ArrayList<Batter>();
	static String[] player = new String[batters.size()];
    static ArrayList<Integer> stats = new ArrayList<Integer>();
    
    public PlayerImplementation(String c) {
	    try {
	    	File file = new File(c);
	    	BufferedReader reader = new BufferedReader(new FileReader(file));
	    	String line = null;
	    	int i = 0;
	    	while((line = reader.readLine()) != null) {
	    		String[] player = line.split(",");
	    		Batter b = new Batter(player[i+0], player[i+1], Integer.parseInt(player[i+2]), 
		    			(player[i+3]),Integer.parseInt(player[i+4]),Integer.parseInt(player[i+5]),
		    		    Integer.parseInt(player[i+6]),Integer.parseInt(player[i+7]),Integer.parseInt(player[i+8]),
		    			Integer.parseInt(player[i+9]),Integer.parseInt(player[i+10]),Integer.parseInt(player[i+11]),
		    			Integer.parseInt(player[i+12]),Integer.parseInt(player[i+13]),Integer.parseInt(player[i+14]),
		    			Integer.parseInt(player[i+15]),Integer.parseInt(player[i+16]),Integer.parseInt(player[i+17]),
		    			Integer.parseInt(player[i+18]),Integer.parseInt(player[i+19]),Integer.parseInt(player[i+20]));
	    		batters.add(b);
	    	}
		}
		catch (Exception e) {
			System.out.print("Error: " + e.getMessage() );
		}
    }
    
    /*
     * This private "Batter" class will Initialize a batter
     * object. The batter object's attributes will
     * have minimal information on the player, and certain
     * stats that can be also used to calculate sabermetrics.
     */
    private static class Batter {
    	
    	// Declaration of Player's Attributes
        String position, fullname, batting, throwing, team;
        String z = "";
        int age, g, ab, r, h, d, t, hr, rbi, sb, cs, 
            bb, k, gidp, hbp, sh, sf, ibb;
        String[] player = new String[z.length()];
        int[] stats = new int[53];
        
        //Batter Class Constructor
        
        /*
         * This class actually takes two constructors.
         * One will take a String, and the array of 
         * strings, and the other will take and 
         * initialize all player attributes.
         */
        
        public Batter(String z, String[] player) {
        	if(z.equals(null)) {
        		z = "0";
        	}
        	else
		        this.z = z;
		        this.player = player;
        }
        public Batter(String position, String fullname,
                               int age, String team, int g, int ab, int r, int h, int d, int t, int hr, int rbi, int sb, int cs,
                      int bb, int k, int gidp, int hbp, int sh, int sf, int ibb) {
        this.position = position;
        this.fullname = fullname;
        this.age = age;
        this.team = team;
        this.g = g;
        this.ab = ab;
        this.r = r;
        this.h = h;
        this.d = d;
        this.t = t;
        this.hr = hr;
        this.rbi = rbi;
        this.sb = sb;
        this.cs = cs;
        this.bb = bb;
        this.k = k;
        this.gidp = gidp;
        this.hbp = hbp;
        this.sh = sh;
        this.sf = sf;
        this.ibb = ibb;
        this.stats = stats;
        }
        
        /*
         * These are the public accessor methods.
         * All are implemented for all of the 
         * different attributes in the Batter Class.
         */
        
        public String getPosition() {
            return position;
        }
        
        public String getFullName() {
        	return fullname;
        }
        
        public int getAge() {
            return age;
        }
        
        public String getTeam() {
        	return team;
        }
        
        public int getGames() {
            return g;
        }
        
        public int getAtBats() {
            return ab;
        }
        
        public int getRuns() {
            return r;
        }
        
        public int getHits() {
            return h;
        }
        
        public int getDoubles() {
            return d;
        }
        
        public int getTriples() {
            return t;
        }
        
        public int getHomeRuns() {
            return hr;
        }
        
        public int getRunsBattedIn() {
            return rbi;
        }
        
        public int getStolenBases() {
            return sb;
        }
        
        public int getCaughtStealing() {
            return cs;
        }
        
        public int getWalks() {
            return bb;
        }
        
        public int getStrikeouts() {
            return k;
        }
        
        public int getGroundedIntoDoublePlay() {
            return gidp;
        }
        
        public int getHitByPitch() {
            return hbp;
        }
        
        public int getSacraficeHits() {
            return sh;
        }
        
        public int getSacraficeFlies() {
            return sf;
        }
        
        public int getIntentionalWalks() {
            return ibb;
        }
        
        /*
         * After that, these methods calculate
         * certain stats based off the 
         * attribute stats above. They also return 
         * the specified type.
         * Ex: Batting Average is calculated by taking
         * hits and at-bats. So those variables are
         * included in the method.
         */
        
        public int getSingles() {
            return h-(d+t+hr);
        }
        public int getNonIntentionalWalks() { 
            return bb-ibb;
        }
        public int getReachedOnBaseByError() {
            return getPlateAppearances()-bb-h-hbp;
        }
        public double getBattingAverage() {
            return (double)h/ab;
        }
        public double getOnBasePercentage() {
            return (h+bb+hbp)/(double)getPlateAppearances();
        }
        public int getPlateAppearances() {
            return ab+bb+hbp+sf+sh;
        }
        public double getSluggingPercentage() {
            return (double)getTotalBases()/ab;
        }
        public double getOnBasePlusSlugging() {
            return getOnBasePercentage()+getSluggingPercentage();
        }
        public int getTotalBases() {
            return (getSingles()+2*d+3*t+4*hr);
        }
        public int getExtraBaseHits() {
            return d+t+hr;
        }
        public int getOuts() {
            return (ab-h)+gidp+sf+sh+cs;
        }
        public double getExtrapolatedRuns() {
            return (double).5*getSingles()+.72*d+1.04*t+1.44*hr+.34*(hbp+getTotalBases()-ibb)+.25*ibb+.18*sb-.32*cs-.09*(ab-h-k)-.098*k-.37*gidp+.37*sf+.04*sh;
        }
        public double getExtrapolatedRunsReduced() {
            return (double).5*getSingles()+.72*d+1.04*t+1.44*hr+.33*(hbp+getTotalBases())+.18*sb-.32*cs-.098*(ab-h);
        }   
        public double getExtrapolatedRunsBasic() {
            return (double).5*getSingles()+.72*d+1.04*t+1.44*hr+.34*getTotalBases()+.18*sb-.32*cs-.096*(ab-h);
        }
        public double getRunsCreated() {
            return (((double)h+bb-cs+hbp-gidp)*(getTotalBases()+.26*(bb-ibb+hbp))+.52*(sh+sf+sb))/(ab+bb+hbp+sh+sf);
        }
        public double getWeightedOnBaseAverage() {
            return ((double).72*bb+.75*hbp+.9*getSingles()+.92*r+1.24*d+1.56*t+1.95*hr)/getPlateAppearances();
        }
        public double getSecondaryAverage() {
            return ((double)bb+getTotalBases()-h+sb-cs)/ab;
        }
        public double getWeightedRunsAboveAverage() {
            return ((getWeightedOnBaseAverage()-.32)/1.25)*(ab+bb+hbp+sh+sf);
        }
        public double getBattingAverageBallsInPlay() {
            return (h-hr)/(ab-k-h+sf);
        }
        public double getEquivalentAverage() {
            return ((double)h+getTotalBases()+1.5*(bb+hbp)+sb+sh+sf)/(ab+bb+hbp+sh+sf+cs+(sb/3));
        }
        public double getTotalAverage() {
            return ((double)getTotalBases()+hbp+bb+sb)/(ab-h+cs+gidp);
        }
        public double getPowerSpeedNumber() {
            return (2*(double)hr*sb)/(hr+sb);
        }
        public double getIsolatedPower() {
            return getSluggingPercentage()-getBattingAverage();
        }
        
        public double getHomeRunPlateAppearanceRatio() {
        	return (double)hr/getPlateAppearances();
        }
        
        public double getStrikeoutPlateAppearanceRatio() {
        	return (double)k/getPlateAppearances();
        }
        
        public double getWalkPlateAppearanceRatio() {
        	return (double)bb/getPlateAppearances();
        }
        
        public double getExtraBaseHitPlateAppearanceRatio() {
        	return (double)getExtraBaseHits()/getPlateAppearances();
        }
        
        public double getExtraBaseHitHitsRatio() {
        	return (double)getExtraBaseHits()/h;
        }
        
        public double getStrikeoutWalkRatio() {
        	return (double)k/bb;
        }
        
        public double getAtBatStrikeoutRatio() {
        	return (double)ab/k;
        }
        
        public double getAtBatHomeRunRatio() {
        	return (double)ab/hr;
        }
        
        public double getAtBatRunsBattedInRatio() {
        	return (double)ab/rbi;
        }
        
        public double getDoubleHitRatio() {
        	return (double)d/h;
        }
        
        public double getTripleHitRatio() {
        	return (double)t/h;
        }
        
        public double getHomeRunHitRatio() {
        	return (double)hr/h;
        }
        
        public double getBallsInPlayPlateAppearanceRatio() {
        	return (double)(ab-k-hr+sf)/getPlateAppearances();
        }
        
        /*
         * This method returns a stat that
         * will be specified in the argument
         * for an attribute stat.
         */
        
        public static int specifiedStats(int a) {
        	return a;
        }
        
    }
    
    /*
     * This will be the class for constructing
     * a pitcher.
     */
    
    private static class Pitcher {
    	boolean starter;
    	String firstname, lastname, team;
    	int age, w, l, gp, gs, cg, sh, sv, ip, h, ra, er, hr, bb,
    		k, hbp, bk, wp, bf;
    	// pitcher constructor
    	public Pitcher (boolean starter, String firstname, String lastname, 
    			String team, int age, int w, 
    			int l, int gp, int gs, int cg, int sh, int sv, int ip, int h, 
    			int ra, int er, int hr, int bb, int k, int hbp, int bk, int wp, int bf) {
    		this.starter = starter;
    		this.firstname = firstname;
    		this.lastname = lastname;
    		this.team = team;
    		this.age = age;
    		this.w = w;
    		this.l = l;
    		this.gp = gp;
    		this.gs = gs;
    		this.cg = cg;
    		this.sh = sh;
    		this.sv = sv;
    		this.ip = ip;
    		this.h = h;
    		this.ra = ra;
    		this.er = er;
    		this.hr = hr;
    		this.bb = bb;
    		this.k = k;
    		this.hbp = hbp;
    		this.bk = bk;
    		this.wp = wp;
    		this.bf = bf;
    	}
    	
    	// accessor methods for pitcher class
    	public String getFirstName() {
            return firstname;
        }
        
        public String getLastName() {
            return lastname;
        }
        
        public String getFullName() {
        	return firstname + " " + lastname;
        }
        
        public int getAge() {
            return age;
        }
        
        public String getTeam() {
        	return team;
        }
        
        public int getWins() {
        	return w;
        }
    	
        public int getLosses() {
        	return l;
        }
        
        public int getGamesPitched() {
        	return gp;
        }
        
        public int getGamesStarted() {
        	return gs;
        }
        
        public int getCompleteGames() {
        	return cg;
        }
        
        public int getShutouts() {
        	return sh;
        }
        
        public int getSaves() {
        	return sv;
        }
        
        public int getInningsPitched() {
        	return ip;
        }
        
        public int getHitsAgainst() {
        	return h;
        }
        
        public int getRunsAllowed() {
        	return ra;
        }
        
        public int getEarnedRuns() {
        	return er;
        }
        
        public int getHomeRunsAgainst() {
        	return hr;
        }
        
        public int getWalksIssued() {
        	return bb;
        }
        
        public int getStrikeoutsFor() {
        	return k;
        }
        
        public int getBattersHitByPitch() {
        	return hbp;
        }
        
        public int getBalks() {
        	return bk;
        }
        
        public int getWildPitches() {
        	return wp;
        }
        
        public int getBattersFaced() {
        	return bf;
        }
    }
    
    /*
     * This method will take the integer
     * stats and fill them in an array.
     * This is copied from the constructor.
     */
    
    public static ArrayList<Integer> fillStatsArray() {
    	for (int a = 0; a < batters.size(); a++) {
    		stats.add(batters.get(a).getGames());
    		stats.add(batters.get(a).getAtBats());
    		stats.add(batters.get(a).getRuns());
    		stats.add(batters.get(a).getHits());
    		stats.add(batters.get(a).getDoubles());
    		stats.add(batters.get(a).getTriples());
    		stats.add(batters.get(a).getHomeRuns());
    		stats.add(batters.get(a).getRunsBattedIn());
    		stats.add(batters.get(a).getStolenBases());
    		stats.add(batters.get(a).getCaughtStealing());
    		stats.add(batters.get(a).getWalks());
    		stats.add(batters.get(a).getStrikeouts());
    		stats.add(batters.get(a).getGroundedIntoDoublePlay());
    		stats.add(batters.get(a).getHitByPitch());
    		stats.add(batters.get(a).getSacraficeHits());
    		stats.add(batters.get(a).getSacraficeFlies());
    		stats.add(batters.get(a).getIntentionalWalks());
    		stats.add(batters.get(a).getSingles());
        	stats.add(batters.get(a).getNonIntentionalWalks());
        	stats.add(batters.get(a).getReachedOnBaseByError());
        	stats.add(batters.get(a).getPlateAppearances());
        	stats.add(batters.get(a).getTotalBases());
        	stats.add(batters.get(a).getExtraBaseHits());
        	stats.add(batters.get(a).getOuts());
    	}
    	return stats;
    }
    
    /*
     * This method will simply print out
     * all of the player's baseball stats
     */
    
    public static void printPlayerStats() {
    }
    
    /*
     * This method will put all players on a
     * specified team in an array and print out
     * their names.
     */
    
    public static int getBattersOnTeam() {
    	Scanner c = new Scanner(System.in);
    	String s = c.nextLine();
    	int a = 0;
    	for(int i = 0; i < batters.size(); i++) {
    		if(s != null && s.equals(batters.get(i).getTeam())) {
    			System.out.println(batters.get(i).getFullName());
    			a++;
    		}
    	}
    	return a;
    }
    
    /*
     * This next method will similarly take
     * players from a team, and put all information
     * into a smaller array of team players.
     */
    
    public static Batter[] listBattersOnTeam() {
    	Scanner c = new Scanner(System.in);
    	String s = c.nextLine();
    	Batter[] teamPlayers = new Batter[getBattersOnTeam()];
    	int a = 0;
    	for(int i = 0; i < batters.size(); i++) {
    		if(s != null && s.equals(batters.get(i).getTeam())) {
    			teamPlayers[a] = batters.get(i);
    			a++;
    		}
    	}
    	return teamPlayers;
    }
    
    
    /*
     * These methods will take the 
     * players' stats and take averages of them.
     * Takes parameter representing what stat
     * to list.
     */
    
    public static double averageLeagueStats(int c) {
    	fillStatsArray();
    	int sum = 0;
    	for(int i = 0; i < batters.size(); i++) {
    		sum += stats.get(24*i+c);
    	}
    	return (double)sum/batters.size();
    	
    }
    
    public double averageTeamStats(Batter[] a, int c) {
    	int sum = 0;
    	for(int i = 0; i < a.length; i++) {
    		sum += stats.get(24*i+c);
    	}
    	return (double)sum/a.length;
    }
    
    /*
     * These next methods will return the 
     * player with the best, and worst 
     * stats.
     * Takes parameter representing what
     * stat to calculate.
     */
    
    public static ArrayList<String> bestStats(int c) {
    	ArrayList<Batter> leaders = new ArrayList<Batter>();
    	ArrayList<String> leaderNames = new ArrayList<String>();
    	fillStatsArray();
    	int a = 0;
    	if (batters.size() > 1) {
	    	for(int i = 0; i < batters.size(); i++) {
	    		if(stats.get(24*i+c) > a) {
	    			leaders.clear();
	    			leaders.add(batters.get(i));
	    			a = stats.get(24*i+c);
	    			
	    		}
	    		else if(stats.get(24*i+c) == a) {
	    			leaders.add(batters.get(i));
	    		}
	    		else continue;
	    	}
	    	System.out.println(a);
	    	for(int j = 0; j < leaders.size(); j++) {
	    		leaderNames.add(leaders.get(j).getFullName());
	    	}
    	}
    	return leaderNames;
    }
    
    
    /*
     * This is where all of the magic happens.
     */
    
    public static void main(String[] args) {
    	PlayerImplementation b = new PlayerImplementation("Database.txt");
    	System.out.println(fillStatsArray());
    	System.out.println(bestStats(6));
    }
}
    