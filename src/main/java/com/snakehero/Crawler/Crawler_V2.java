package com.snakehero.Crawler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Crawler_V2 {

    public static void main(String[] args) throws Exception {  
    		// turn off htmlunit warnings
        	java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        	java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
            
        	WebClient webClient = new WebClient(BrowserVersion.CHROME);

            HtmlPage page = (HtmlPage) webClient.getPage("https://www.appannie.com/account/login/?_ref=header"); 
            HtmlForm form = page.getFirstByXPath("//*[@id='login-form']");
            form.getInputByName("username").setValueAttribute("younesi@ce.sharif.edu");
            form.getInputByName("password").setValueAttribute("Une30316");
            
            HtmlButton button = form.getFirstByXPath("//*[@id='submit']");
            page = button.click();

            System.out.println(page.asText());
    } 

}
