package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static utilities.Common.setRepValues;

public class Sanity extends BaseTest{

    public Sanity(){
        setRepValues();
    }

    @Test(priority = 1 ,groups= {"Sanity"}, description= "Open some case study, play the video")
    public void caseStudies(){
        bc4Homepage.getCB4HomePage();
        bc4Homepage.closeCookies();
        bc4Homepage.openTopMenuCaseStudies();
        caseStudies.openFestivalFoods();
        festivalFoods.switchToVideoIframe();
        Assert.assertTrue(festivalFoods.validateVideoIsPlaying(),"Failed playing the embedded YouTube video");
    }

    @Test(priority = 2 ,groups= {"Sanity"}, description= "Navigate to Automation Engineer vacancy page, almost apply for that position")
    public void applyForAutomationEngineerPosition(){
        bc4Homepage.getCB4HomePage();
        bc4Homepage.closeCookies();
        bc4Homepage.openBottomCareers();
        careers.goToOpenRoles();
        careers.openAutomationInRNDPositions();
        careers.openAutomationVacancy();
        careersApply.insertUserDataInApplicationForm();
        careersApply.clickSubmit();
        Assert.assertTrue(careersApply.validateYourSourceNotFilled(),"Required field not filled indication is not found");
    }

}
