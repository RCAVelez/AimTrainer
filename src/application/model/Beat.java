package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

//this will be used to handle user statistics such as correct hits, misses,
//and to print those statistics at the end

public class Beat {
	
	
	private String api_key = "e4ce259c2bc87d7a87b44c0bb11cac27";
	private String artist;
	private String song;
	private URL url;
	private BufferedReader in = null;
    private String inputLine;
    private StringBuilder body;
    private HttpURLConnection httpcon;
    private JSONObject result;
    private String removeArray;
    private int beatsPerMinute;
    private int beatsPerSecond;
    private int sleepTime;
    private long startTime;
    private long endTime;
    private static MediaPlayer mediaPlayer;
    
    
	public Beat(String artist, String song){
		this.artist = artist.replaceAll(" ", "+");
		this.song = song.replaceAll(" ", "+");
	} 
	
	public void establishConnection() throws IOException
	{
		this.url = new URL("https://api.getsongbpm.com/search/?api_key="+api_key+"&type=both&lookup=song:"+song+"artist:"+artist);
		this.httpcon = (HttpURLConnection) url.openConnection(); 
		this.httpcon.addRequestProperty("User-Agent", "Mozilla/4.76"); 
		this.httpcon.setRequestMethod("GET");
	}
	
	public void extractBMP() throws IOException, JSONException
	{
		this.in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
	    this.body = new StringBuilder();
	    
	    while ((this.inputLine = this.in.readLine()) != null) {
	         this.body.append(inputLine);
	    }
	    this.in.close();
	    
	    this.result = new JSONObject(this.body.toString()); 
	    
	    this.removeArray = this.result.get("search").toString().substring(1);
		this.removeArray = this.removeArray.substring(0, this.removeArray.length() - 1);
		this.result = new JSONObject(removeArray.toString());
		this.beatsPerMinute = Integer.parseInt(this.result.get("tempo").toString());
		this.beatsPerSecond = this.beatsPerMinute / 60;
		this.sleepTime = 1000 / this.beatsPerSecond; 
	}
	
	 public void playMusic()
     {
    	 Media sound = new Media(Paths.get("song.wav").toUri().toString());
        
    	 mediaPlayer = new MediaPlayer(sound);
         
         mediaPlayer.play();
     }

	public int getBeatsPerMinute() {
		return beatsPerMinute;
	}

	public void setBeatsPerMinute(int beatsPerMinute) {
		this.beatsPerMinute = beatsPerMinute;
	}

	public int getBeatsPerSecond() {
		return beatsPerSecond;
	}

	public void setBeatsPerSecond(int beatsPerSecond) {
		this.beatsPerSecond = beatsPerSecond;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
   

	
}
