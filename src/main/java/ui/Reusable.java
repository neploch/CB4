package ui;
import org.openqa.selenium.WebDriver;


public class Reusable extends BasePage {
    public Reusable(WebDriver driver){
        super(driver);
    }

    //Embedded YouTube elements
    public String videoIframe = "//iframe[@class='elementor-video']";
    public String playVideoBtn = "//button[@aria-label ='Play']";
    public String videoCurrentTimeIndicator = "//span[@class='ytp-time-current']";
    public String muteYoutubeBtn = "//button[contains(@class,'mute-button')]";

    //Embedded YouTube methods
    public void switchToVideoIframe(){
        switchToIframe(videoIframe);
    }

    public void playVideo(){
        clickVisible(playVideoBtn);
    }

    public String getVideoCurrentTime(){
        return getVisibleElementText(videoCurrentTimeIndicator);
    }

    public boolean validateVideoIsPlaying(){
        playVideo();
        String initialVideoTime = getVideoCurrentTime();
        wait(3000);
        hoverOverElement(muteYoutubeBtn);
        String secondVideoTime = getVideoCurrentTime();
        return !(initialVideoTime.contains(secondVideoTime));
    }


}
