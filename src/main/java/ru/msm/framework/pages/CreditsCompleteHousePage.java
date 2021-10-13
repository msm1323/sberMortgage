package ru.msm.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.function.Consumer;

public class CreditsCompleteHousePage extends BasePage {

    private final WebDriver DRIVER = DRIVER_MANAGER.getDriver();

    @FindBy(xpath = "//iframe[@id = 'iFrameResizer0']")
    protected WebElement iframeCalculator;

    @FindBy(xpath = "//div[contains(@class,'input-5-3-5')]")    //пара
    protected List<WebElement> divInputs;

    @FindBy(xpath = "//input[contains(@class,'input-5-3-5')]")
    protected List<WebElement> inputs;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts')]//span[@class='_1ZfZYgvLm4KBWPL41DOSO']")    //пара
    protected List<WebElement> discountSpans;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts')]//input[@type='checkbox']")
    protected List<WebElement> discountCheckboxes;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'result-block')]//span[contains(@class,'_270Um')]")    //пара
    protected List<WebElement> resultBlockSpans;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'result-block')]//span[contains(@class,'_1bsib')]")
    protected List<WebElement> valuesSpans;

    public void fillFields(List<List<String>> args) {
        DRIVER.switchTo().frame(iframeCalculator);
        for (List<String> arg : args) {
            for (int j = 0; j < divInputs.size(); j++) {
                WebElement div = divInputs.get(j);
                if (div.getText().contains(arg.get(0))) {
                    WebElement field = inputs.get(j);
                    String v = formatD(arg.get(1));
                    if (formatD(field.getAttribute("value")).equals(v)) {
                        break;
                    }
                    String oldV = field.getAttribute("value");
                    field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    field.sendKeys(v);
                    wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(field,
                            "value", oldV)));
                    Assertions.assertEquals(v, formatD(field.getAttribute("value")),
                            "Значение в поле не совпадает с ожидаемым!");
                    break;
                }
            }
        }
        DRIVER_MANAGER.getDriver().switchTo().parentFrame();
    }

    public void switcherDiscountsBlock(boolean select, String nameSpan) {
        DRIVER.switchTo().frame(iframeCalculator);
        for (int i = 0; i < discountSpans.size(); i++) {
            WebElement span = discountSpans.get(i);
            if (span.getText().contains(nameSpan)) {
                if (!discountCheckboxes.get(i).isSelected() == (select)) {
                    String oldV = valuesSpans.get(0).getAttribute("outerText");
                    scrollToElementJs(discountCheckboxes.get(i));
                    action.sendKeys(Keys.chord(Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP)).perform();
//                    waitUntilElementToBeClickable(discountCheckboxes.get(i)).click();
                    discountCheckboxes.get(i).click();
                    wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(valuesSpans.get(0),  //?
                            "outerText", oldV)));
                }
                Assertions.assertEquals(select, discountCheckboxes.get(i).isSelected(),
                        "Не удалось перевести в нужное состояние checkbox \"" + nameSpan + "\"!");
                break;
            }
        }
        DRIVER_MANAGER.getDriver().switchTo().parentFrame();
    }

    public void checkLabelsValues(List<List<String>> args) {
        DRIVER.switchTo().frame(iframeCalculator);
        for (List<String> arg : args) {
            for (int j = 0; j < resultBlockSpans.size(); j++) {
                WebElement span = resultBlockSpans.get(j);
                if (span.getText().contains(arg.get(0))) {
                    Assertions.assertEquals(formatD(arg.get(1)), formatD(valuesSpans.get(j).getText()),
                            "Значение поля " + span.getText() + " не соответствует ожидаемому!");
                    break;
                }
            }
        }
        DRIVER_MANAGER.getDriver().switchTo().parentFrame();
    }

}
