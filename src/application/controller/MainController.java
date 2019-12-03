package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.json.JSONException;

import application.model.Beat;
import application.model.ScoreBoard;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import  javafx.scene.shape.Rectangle;


public class MainController {
	@FXML
    private List<Rectangle> rectanglesList;
	private ArrayList<Integer> excludeRows = new ArrayList<Integer>();
	int idOfRec;
	Beat beat;
	ScoreBoard scoreboard;
	Color color;
	Rectangle rec;
	
    public void initialize() throws IOException, JSONException, InterruptedException
    {
    	//create new scoreboard object and initialize
    	scoreboard = new ScoreBoard();
    	
    	//create new beat object and call all functions
    	beat = new Beat("Billie Eilish", "bad guy");
    	beat.establishConnection();
    	beat.extractBMP();
    	
    	//play the song and run the Beats simulatniously
    	beat.playMusic();
    	
    	thread1.start(); //this way because otherwise Thread.sleep would pause the whole system
    	
    }
    
    
    @FXML
    void onBox(MouseEvent event) throws IOException{	
    	((Rectangle)event.getSource()).setStroke(Color.ORANGE);
    }
    
    @FXML
    void offBox(MouseEvent event) throws IOException{
    	((Rectangle)event.getSource()).setStroke(Color.BLACK);
    }
    
    
    
    @FXML
    void boxClicked(MouseEvent event) throws IOException {
    	
    	//how will hovering affect the color of this?
    	if( ((Rectangle)event.getSource()).getFill() == Color.BLACK )
    	{
    		scoreboard.addHits();
    		((Rectangle)event.getSource()).setFill(Color.DODGERBLUE);
    		
    		//make spot available again -TODO
    		int id = Integer.parseInt(((Rectangle)event.getSource()).getId().toString().split("idOf")[1]);
    		
    		//System.out.println(excludeRows);
    		excludeRows.remove(new Integer(id));
    		//System.out.println(excludeRows);
    	}
    	else
    	{
    		//System.out.println("Missed");
    		scoreboard.addMisses();
    	}
    	System.out.println(scoreboard.toString());
    	
    	
    }
    
    public int generateRandom(int start, int end, ArrayList<Integer> excludeRows) {
        Random rand = new Random();
        int range = end - start + 1;

        int random = rand.nextInt(range) + 1;
        while(excludeRows.contains(random)) {
            random = rand.nextInt(range) + 1;
        }
        excludeRows.add(random);
        return random;
    }
    
    
    Thread thread1 = new Thread() {
        public void run() {
        	
        	try {
				Thread.sleep( 2000 );
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	beat.setStartTime(System.currentTimeMillis());
        	
        	
        	beat.setEndTime(beat.getStartTime() + 205000);
        	
        	
        	while(System.currentTimeMillis() < beat.getEndTime()) {	
        		
        		idOfRec = generateRandom(0, 251, excludeRows);
        		rectanglesList.get(idOfRec).setFill(Color.BLACK);
        		rectanglesList.get(idOfRec).setStroke(Color.BLACK);
        		
        	    try {
					Thread.sleep( beat.getSleepTime() );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		
        	}
        }
    };
   
   
    
	
}
