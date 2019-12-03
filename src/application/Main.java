package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


  
// launches the application 
public class Main extends Application {  
  
	
    @Override
    public void start(Stage primaryStage) {
    	
    	try {
			
			Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			Scene scene = new Scene(root,588,384);
			primaryStage.setTitle("Aim Trainer");
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.setResizable(false);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
 
    }

     // Main function to launch the application 
     public static void main(String[] args){ 
           launch(args); 
     }
     
    
} 