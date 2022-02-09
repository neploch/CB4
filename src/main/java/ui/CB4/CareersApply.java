package ui.CB4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.Reusable;

import static utilities.Common.getRepValue;

public class CareersApply extends Reusable {
    public CareersApply(WebDriver driver){
        super(driver);
    }

    //Required field is not filled class indicator
    String requiredFiledNotFilled = "field_need_to_fill";

    //Page elements locators
    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By emailInput = By.cssSelector("input#email");
    By phoneInput = By.cssSelector("input#phone");
    String uploadCVElement = "//input[@type='file'][@id='resume']";
    By submitBtn = By.id("carrers_apply_btn");
    String yourSourceContainer = "//div[./input[@id='question_13970118002']]";

    //User data from repository
    String firstName = getRepValue("firstName");
    String lastName = getRepValue("lastName");
    String email = getRepValue("email");
    String phone = getRepValue("phone");
//    String resumeFile = System.getProperty("user.dir") + "/src/main/resources/CV.docx";

    //Fill the application form with user data
    public void insertUserDataInApplicationForm(){
        sendTextToVisibleElement(firstNameInput,firstName);
        sendTextToVisibleElement(lastNameInput,lastName);
        sendTextToVisibleElement(emailInput,email);
        sendTextToVisibleElement(phoneInput,phone);
//        justSendTextToElement(uploadCVElement,resumeFile);
    }

    //Click Submit button
    public void clickSubmit(){
        clickVisible(submitBtn);
    }

    //Validate "How did you hear about this job" field is not filled with data
    public boolean validateYourSourceNotFilled(){
        return waitForAttributeToContain(yourSourceContainer,"class",requiredFiledNotFilled,5);
    }



}
