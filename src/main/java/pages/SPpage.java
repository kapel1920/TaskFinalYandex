package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

public class SPpage {

    @FindBy (xpath = "//a//img[contains(@src,'banner-zashita-traveler')]")

    public WebElement sendButton;


    public SPpage (){
        PageFactory.initElements(BaseSteps.getDriver(), this);

    }


    public void switchWindows(){
        for (String winHandle : BaseSteps.getDriver().getWindowHandles()) {

            BaseSteps.getDriver().switchTo().window(winHandle);
        }
    }
    public void waitElementToBeClickable(){
        Wait<WebDriver> wait = new WebDriverWait(BaseSteps.getDriver(), 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(
                BaseSteps.getDriver().findElement(By.xpath("//*[contains(text(),\"Минимальная\")]")))).click();
    }

    public void clickButton(){
        BaseSteps.getDriver().findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Оформить']")).click();
    }

    public void checkTitle (){
        Assert.assertTrue(BaseSteps.getDriver().findElement(By.cssSelector("div[class='sbrf-rich-outer']")).getText().contains("Страхование путешественников"));
    }
}