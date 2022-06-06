package seleniumtest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;

import java.util.Optional;

public class App {
    private static ChromeDriver driver;
    private static DevTools chromeDevTools;

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "/Users/valodzka/tmp/chromedriver");
        driver = new ChromeDriver();
        chromeDevTools = driver.getDevTools();
        chromeDevTools.createSession();

        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        chromeDevTools.addListener(Network.requestWillBeSent(),
                entry -> {
                    System.out.println("Request URI : " + entry.getRequest().getUrl()+"\n"
                            + " With method : "+entry.getRequest().getMethod() + "\n");
                    entry.getRequest().getMethod();
                });
        driver.get("https://www.google.com");
        chromeDevTools.send(Network.disable());
    }
}
