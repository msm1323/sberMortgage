package ru.msm.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//div[contains(@data-e2e-id,'result-block')]//span[contains(@class,'_270Um')]/..//span[contains(@class,'_1bsib')]")
    protected List<WebElement> valuesSpans;

    public void fillFields(List<List<String>> args) {
        DRIVER.switchTo().frame(iframeCalculator);
        for (List<String> arg : args) {
            for (int j = 0; j < divInputs.size(); j++) {
                WebElement div = divInputs.get(j);
                if (div.getText().contains(arg.get(0))) {
                    inputs.get(j).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    inputs.get(j).sendKeys(formatD(arg.get(1)));
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
                String checked = discountCheckboxes.get(i).getAttribute("aria-checked");
                if (!checked.equals(Boolean.toString(select))) {
                    scrollToElementJs(discountCheckboxes.get(i));
                    action.sendKeys(Keys.chord(Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP)).perform();
                    discountCheckboxes.get(i).click();
//                        waitUntilElementToBeClickable(discountCheckboxes.get(i)).click();
                }
                Assertions.assertEquals(Boolean.toString(select), discountCheckboxes.get(i).getAttribute("aria-checked"),
                        "Не удалось перевести в нужное состояние checkbox \"" + nameSpan + "\"!");
                break;
            }
        }
        DRIVER_MANAGER.getDriver().switchTo().parentFrame();
    }

    public void checkLabelsValues(List<List<String>> args) {
        DRIVER.switchTo().frame(iframeCalculator);
        for (List<String> arg : args) {
            for (int j = 0; j < resultBlockSpans.size(); j++) { //switch case???
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
