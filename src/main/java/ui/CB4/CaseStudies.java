package ui.CB4;

import org.openqa.selenium.WebDriver;
import ui.BasePage;

public class CaseStudies extends BasePage {
    public CaseStudies(WebDriver driver){
        super(driver);
    }

    //Page elements locators
    String festivalFoodsLink = "//a[contains(@href,'festival-foods')]";

    //Open Festival Food page
    public void openFestivalFoods(){
        clickVisible(festivalFoodsLink);
    }

}
