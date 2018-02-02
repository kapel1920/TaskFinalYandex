package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SPpage;
import ru.yandex.qatools.allure.annotations.Step;

public class SPSteps {

    @Step ("Выполнена проверка текстовки Страхование путешественников на странице SP")
    public void stepCheckText (){
        Assert.assertTrue(BaseSteps.getDriver().findElement(By.cssSelector("div[class='sbrf-rich-outer']")).getText().contains("Страхование путешественников"));
    }


    @Step("Выполнено нажатие на кнопку - оформить онлайн")
    public void stepSendAppButton() {
        new SPpage().sendButton.click();
    }

    @Step("Выполнен переход на страницу выбора полиса")
    public void stepSwitchWindow() {
        for (String winHandle : BaseSteps.getDriver().getWindowHandles()) {

            BaseSteps.getDriver().switchTo().window(winHandle);
        }
    }
    @Step ("Выполнено нажатие на поле 'выберите сумму страховой защиты' - Минимальная")
    public void stepClickMinSum(){
        Wait<WebDriver> wait = new WebDriverWait(BaseSteps.getDriver(), 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(
                BaseSteps.getDriver().findElement(By.xpath("//*[contains(text(),\"Минимальная\")]")))).click();
    }

    @Step ("Выполнено нажатие на кнопку Продолжить")
    public void stepClickButtonNext(){
        BaseSteps.getDriver().findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Оформить']")).click();
    }
}