package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

import static org.junit.Assert.assertEquals;

public class SendAppPage {

    @FindBy(name = "insured0_surname")
    WebElement insured_surname;

    @FindBy(name = "insured0_name")
    WebElement insured_name;

    @FindBy(name = "insured0_birthDate")
    WebElement insured_birthDate;

    @FindBy(name = "surname")
    WebElement surname;

    @FindBy(name = "middlename")
    WebElement middlename;

    @FindBy(name = "name")
    WebElement name;

    @FindBy(name = "birthDate")
    WebElement birthDate;

    @FindBy (xpath = "//SPAN[@ng-click='save()'][text()='Продолжить']")
    public WebElement sendButton;

    public SendAppPage() {
        PageFactory.initElements( BaseSteps.getDriver(), this);
    }

    public void fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void fillField(String fieldName, String value){
        switch (fieldName){
            case  "Фамилия":
                fillField(surname, value);
                break;
            case  "Имя":
                fillField(name, value);
                break;
            case  "Отчество":
                fillField(middlename, value);
                break;
            case  "Фамилия2":
                fillField(insured_surname, value);
                break;
            case  "Имя2":
                fillField(insured_name, value);
                break;
            case  "Дата рождения":
                fillField(insured_birthDate, value);
                break;
            case  "Дата рождения2":
                fillField(birthDate, value);
                break;
            default:  throw new AssertionError("Поле '"+fieldName+"' не объявлено на странице");
        }
    }

    public String getFillField(String fieldName){
        switch (fieldName){

            case  "Фамилия":
                return surname.getAttribute("value");
            case  "Имя":
                return name.getAttribute("value");
            case  "Отчество":
                return middlename.getAttribute("value");
            case  "Фамилия2":
                return insured_surname.getAttribute("value");
            case  "Имя2":
                return insured_name.getAttribute("value");
            case  "Дата рождения":
                return insured_birthDate.getAttribute("value");
            case  "Дата рождения2":
                return birthDate.getAttribute("value");
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    public void checkError(){
        assertEquals("Заполнены не все обязательные поля", BaseSteps.getDriver().findElement(By.xpath("//DIV[@ng-show='tryNext && myForm.$invalid'][text()='Заполнены не все обязательные поля']")).getText());
    }
}