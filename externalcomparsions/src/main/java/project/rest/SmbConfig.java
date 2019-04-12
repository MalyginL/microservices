package project.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class SmbConfig {


    public File getFile(){
        return new File("ftp\\GMSU1958.536");
    }
// \\192.168.101.141\6rrt\BIPM\6RRT58464.dat
// true
// true
// ftp\GMSU1958.464


    public boolean name(){
        try(BufferedReader br = new BufferedReader(new FileReader(getFile())))
        {

            for(int i=0; i<19;i++){
                br.readLine();
            }
            String s;
            while((s=br.readLine())!=null){
                String[] array = s.trim().split("\\W+");
                System.out.println(array[0] + "|" + array[3] + "|"+array[4] + "|"+array[5] + "|"+array[7] + "|"+array[22] + "|");
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return true;
    }
}
