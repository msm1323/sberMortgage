package ru.msm.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class StartPage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesBtnClose;

    public void clickOnTopMenuIcon(String icon) {
        clickOnElByName(menuIcons, icon);
    }

    public void waitDropdownMenu(String icon) {
        clickOnElByName(dropdownIcons, icon);
    }

    private void clickOnElByName(List<WebElement> elements, String name) {
        try {
            waitUtilElementToBeVisible(
                    waitUntilElementToBeClickable(elements.stream()
                            .filter(el -> el.getText().contains(name))
                            .findFirst()
                            .get()
                    )
            ).click();
        } catch (NoSuchElementException ex) {
            Assertions.fail("Элемент \"" + name + "\" не найден!");
        }
    }

    public StartPage closeCookiesDialog() {
        waitUntilElementToBeClickable(cookiesBtnClose).click();
        return this;
    }

}
