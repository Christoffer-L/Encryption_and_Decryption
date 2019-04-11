package chapter17_and_18_BinaryIO_and_Recursion;

import java.io.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Crypt_File extends Application
{
	public static void main(String args[])
	{
		launch(args);
	}

	@Override 
	public void start(Stage arg0) throws Exception 
	{
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 900, 300);
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hboxDesination = new HBox();
		VBox vbox = new VBox();
		
		Button btn = new Button("Encrypt");
		Text txtFileDestinationDecrypted = new Text("ABS decrypted file location");
		Text txtFileDestinationEncrypted = new Text("ABS encrypted file location");
		
		TextField txtF1 = new TextField();
		TextField txtF2 = new TextField();
		TextField txtF3 = new TextField();
		
		txtF1.setPrefWidth(350.0);
		txtF2.setPrefWidth(350.0);
		txtF3.setPrefWidth(700.0);
		txtF3.setEditable(false);
		
		vbox.setLayoutX(50.0);
		vbox.setLayoutY(50.0);
		
		hbox1.setSpacing(10.0);
		hbox1.setPadding(new Insets(5, 5, 5, 5));
		
		hbox2.setSpacing(10.0);
		hbox2.setPadding(new Insets(5, 5, 5, 5));
		
		hboxDesination.setSpacing(215.0);
		hboxDesination.setPadding(new Insets(5, 5, 5, 5));
		
		hboxDesination.getChildren().addAll(txtFileDestinationDecrypted, txtFileDestinationEncrypted);
		
		hbox1.getChildren().addAll(txtF1, txtF2, btn);
		hbox2.getChildren().add(txtF3);
		
		vbox.getChildren().addAll(hboxDesination, hbox1, hbox2);
		pane.getChildren().add(vbox);
		
		arg0.setTitle("Crypt File");
		arg0.setScene(scene);
		arg0.show();
		
		btn.setOnMouseClicked
    	(e -> 
    	{
    		if(!(txtF1.getText().equals("")) && !(txtF2.getText().equals("")))
    		{
				try 
				{
					encryptFile(txtF1.getText(), txtF2.getText(), txtF3);
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}	
    		}
    		else
    			System.out.println("No Path's Selected!");
    	}
    	);
	}
	
	private void encryptFile(String fileName, String saveFile, TextField txt) throws IOException 
	{
		
		try
		(
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(saveFile));
		)
		{
			int data = input.read();
			int r = data + 5;
			output.write(r);
			
			while (data != (-1))
			{
				data = input.read();
				r = data + 5;
				output.write(r);
			}
			txt.setText(saveFile + " has been encrypted");
			
			// Close input / output
			input.close();
			output.flush();
			output.close();
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}	
	}
	
}

