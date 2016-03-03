package com.kowree.cfsox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		 System.out.printf("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
        String s = null;
        
        try {
             
        // run the Unix "ps -ef" command
            // using the Runtime exec method:
            //Process p = Runtime.getRuntime().exec(new String[] {"lsb_release", "-a"}); //.java-buildpack/sox/sox
            String[] cmd = new String[] {"/home/vcap/app/sox","/home/vcap/app/mp3/Mama_Dogood.mp3","-c","1","-r","8000","/home/vcap/app/mp3/Mama_Dogood.wav"};
        	Process p = Runtime.getRuntime().exec(cmd); //.java-buildpack/sox/sox
             
            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(p.getInputStream()));
 
            BufferedReader stdError = new BufferedReader(new
                 InputStreamReader(p.getErrorStream()));
 
            // read the output from the command
            System.out.println("Here is the standard output of the command:"+Arrays.toString(cmd)+"\n");
            while ((s = stdInput.readLine()) != null) {
            	 System.out.println(s);
            }
             
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
            	 System.out.println(s);
            }
             
           // System.exit(0);
        }
        catch (IOException e) {
        	 System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            //System.exit(-1);
        }
		
		return "home";
	}
	
}
