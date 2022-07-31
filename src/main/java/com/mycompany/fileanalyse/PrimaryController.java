package com.mycompany.fileanalyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{

    @FXML
    private Button Browse;
    @FXML
    private Button Analyse;
    @FXML
    private TextField path;
    @FXML
    private TextArea result;
    @FXML
    private Label Status;

    /**
     * Initializes the controller class.
     */
    
    int line=0;
    int sen=0;
    int words=0;
    int vowel=0;
    int con=0;
    int digit=0;
    int others=0;
    File f;
    FileChooser fchooser;
    String FilePathName ="";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fileReader(ActionEvent event)
    {
        fchooser = new FileChooser();
        f = fchooser.showOpenDialog(new Stage());
        FilePathName = f.getAbsolutePath();
        
        path.setText(FilePathName);
    
    }

    @FXML
    private void doAnalyse(ActionEvent event) 
    {
        
        if(FilePathName.length()>0)
        {
            String fileText="";
        String tempLine="";
        
        try{
            FileReader fr = new FileReader(FilePathName);
            BufferedReader br = new BufferedReader(fr);
            
            while((tempLine = br.readLine())!= null)
            {
                line++;
                fileText = fileText+tempLine;
                StringTokenizer st = new StringTokenizer(tempLine);
                
                while(st.hasMoreTokens())
                {
                    words++;
                    String tempWords = st.nextToken();
                    tempWords = tempWords.toLowerCase();
                    
                    for(int i=0; i<tempWords.length(); i++)
                    {
                        if(tempWords.charAt(i)=='.')
                        {
                            sen++;
                        }
                        else if(tempWords.charAt(i)=='a' || tempWords.charAt(i)=='e' || tempWords.charAt(i)=='i' || tempWords.charAt(i)=='o' || tempWords.charAt(i)=='u')
                        {
                            vowel++;
                        }
                        else if(tempWords.charAt(i)>='a' && tempWords.charAt(i)<='z' )
                        {
                            con++;
                        }
                        else if(tempWords.charAt(i)>='0' && tempWords.charAt(i)<='9')
                        {
                            digit++;
                        }
                        else
                        {
                            others++;
                        }
                    }
                }
                
            }
            
            
        }catch(Exception e)
        {
            Status.setText("" +e);
        }
        
        Status.setText("Successfully Done ...");
        String msg = "\nLine : "+line;
         msg += "\nSentences : "+sen;
         msg += "\nWords : "+words;
         msg += "\nVowel : "+vowel;
         msg += "\nConsonent : "+con;
         msg += "\nDigit: "+digit;
         msg += "\nOthers : "+others;
        result.setText(msg);
        }
        else{
            Status.setText("Please Select File...");
        }
        
        
    }
}
