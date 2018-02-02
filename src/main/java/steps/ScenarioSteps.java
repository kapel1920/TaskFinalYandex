package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.SPpage;
import pages.SendAppPage;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static steps.BaseSteps.baseUrl;

public class ScenarioSteps {

    MainSteps mainSteps = new MainSteps();
    SendAppSteps sendAppSteps = new SendAppSteps();
    MainPage mainPage = new MainPage();
    SPpage sPpage = new SPpage();
    SendAppPage sendAppPage = new SendAppPage();

    @When("Осуществлен переход на страницу сбербанка")
    public void stepTransferToSberbank() {
        BaseSteps.getDriver().get( baseUrl );
    }

    @When("^Выбран пунк меню \"(.*)\"$")
    public void stepSelectMainMenu(String menuItem) {
        mainSteps.stepSelectMainMenu( menuItem );
    }

    @When("^Выбран пунк меню - \"(.*)\"$")
    public void stepSelectTypeInsurance(String menuItem) {
        mainPage.waitVisibilityOfelement();
        mainSteps.stepSelectSubMenu( menuItem );
    }
    @When( "Выполнена проверка текстовки Страхование путешественников на странице SP" )
    public void stepCheckTextOnPAgeSP (){
        Assert.assertTrue(BaseSteps.getDriver().findElement( By.cssSelector("div[class='sbrf-rich-outer']")).getText().contains("Страхование путешественников"));
    }

    @When( "Выполнено нажатие на кнопку - оформить онлайн" )
    public void stepClickAppButton(){
        sPpage.sendButton.click();
    }

    @Then("Выполнен переход на страницу выбора полиса")
    public void stepChooseWindows() {
        sPpage.switchWindows();
    }

    @When( "Выполнено нажатие на поле 'выберите сумму страховой защиты' - Минимальная" )
    public void stepClickMinSumma (){
        Wait<WebDriver> wait = new WebDriverWait(BaseSteps.getDriver(), 5, 1000);
        wait.until( ExpectedConditions.visibilityOf(
                BaseSteps.getDriver().findElement(By.xpath("//*[contains(text(),\"Минимальная\")]")))).click();
    }

    @When( "Выполнено нажатие на кнопку Продолжить" )
    public void stepClickButtonNextPage() {
        BaseSteps.getDriver().findElement( By.xpath( "//SPAN[@ng-click='save()'][text()='Оформить']" ) ).click();
    }


    @When("^заполняются поля:$")
    public void stepFullfillFields(DataTable fields) {
        fields.asMap( String.class, String.class ).forEach(
                (key, value) -> sendAppSteps.stepFillField( key, value ) );
    }

    @Then("^значения полей равны:$")
    public void checkFillForm(DataTable fields){
        fields.asMap(String.class, String.class)
                .forEach((field, value) -> sendAppSteps.stepCheckFillField(field, value));
    }

    @When("Выполнено нажатие на кнопку - Продолжить")
    public void stepSendAppButton() {
        sendAppPage.sendButton.click();
    }

    @Then( "Проверка появления текста Заполнены не все обязательные поля")
    public void stepCheckErrorMsg(){
        assertEquals("Заполнены не все обязательные поля", BaseSteps.getDriver().findElement(By.xpath("//DIV[@ng-show='tryNext && myForm.$invalid'][text()='Заполнены не все обязательные поля']")).getText());
    }
}