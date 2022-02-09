package ui.CB4;

import org.openqa.selenium.WebDriver;
import ui.BasePage;

public class Careers extends BasePage {
    public Careers(WebDriver driver){
        super(driver);
    }

    //Page elements locators
    String openRolesBtn = "//a[contains(@href,'open-roles')]";
    String RNDOpenings = "//section[@data-id='1b68bbe']//span[contains(text(),'R&D')]";
    String automationEngineerPosition = "//a[contains(text(),'Automation Engineer')]";

    //Go to Open Roles section
    public void goToOpenRoles(){
        clickVisible(openRolesBtn);
    }

    //Expand the R&D open positions list
    public void openAutomationInRNDPositions(){
        do {
            wait(800);
            clickVisible(RNDOpenings);
        }while (!waitForElementToBeVisible(automationEngineerPosition,1));
    }

    //Go to Automation Engineer position page
    public void openAutomationVacancy(){
        clickVisible(automationEngineerPosition);
    }

}
