package steps;

import org.openqa.selenium.By;
import pages.SendAppPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SendAppSteps {

    @Step("Поле {0} заполняется значенем {1}")
    public void stepFillField(String field, String value) {
        new SendAppPage().fillField(field, value);
    }

    @Step("Заполняются поля: ")
    public void stepFillFields(HashMap<String, String> fields) {
        fields.forEach(this::stepFillField);
    }


    @Step("поле {0} заполнено значением {1}")
    public void stepCheckFillField(String field, String value){
        String actual = new SendAppPage().getFillField(field);
        assertTrue(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, actual, value),
                actual.equals(value));
    }

    @Step("поля заполнены верно")
    public void stepCheckFillFields(HashMap<String, String> fields){
        fields.forEach((k, v)-> stepCheckFillField(k,v));
    }

    @Step ("Проверка появления текста Заполнены не все обязательные поля")
    public void stepCheckError() {
        assertEquals("Заполнены не все обязательные поля", BaseSteps.getDriver().findElement(By.xpath("//DIV[@ng-show='tryNext && myForm.$invalid'][text()='Заполнены не все обязательные поля']")).getText());
    }

    @Step ("Выполнено нажатие на кнопку - Продолжить")
    public void stepClickButton(){
        new SendAppPage().sendButton.click();
    }

}