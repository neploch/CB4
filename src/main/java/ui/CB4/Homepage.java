package ui.CB4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.BasePage;

public class Homepage extends BasePage {
    public Homepage(WebDriver driver){
        super(driver);
    }

    //Page elements locators
    String CB4HomePageUrl = "https://cb4.com/";
    String closeCookiesBtn = "//div[@id='cookieinfo-close']//a[@role='button']";
    String topMenuCaseStudies = "//div[@data-id='2f67d3d']//nav[not (@aria-hidden)]//a[contains(@href,'case-studies')]";
    By bottomCareersLink = By.cssSelector("div[id='footer-menu1'] nav.elementor-nav-menu--layout-horizontal li.menu-item-796 a");

    //Open CB4 home page
    public void getCB4HomePage(){
        driver.get(CB4HomePageUrl);
    }

    //Close the cookies banner
    public void closeCookies(){
        clickVisible(closeCookiesBtn);
    }

    //Open Case Studies from the upper menu
    public void openTopMenuCaseStudies(){
        clickVisible(topMenuCaseStudies);
    }

    //Scroll down to the page bottom and open Careers page from there
    public void openBottomCareers(){
        while (true){
            try {
                hoverOverVisibleElement(bottomCareersLink,1);
                jsClick(bottomCareersLink);
                break;
            }catch (Exception e){
                scrollX(500);
            }
        }
    }

}
