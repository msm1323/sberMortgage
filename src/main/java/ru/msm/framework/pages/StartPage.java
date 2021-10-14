package ru.msm.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StartPage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    protected WebElement cookiesBtnClose;

    public void clickOnTopMenuIcon(String icon) {
        clickOnElByName(menuIcons, icon);
    }

    public void waitDropdownMenuAndClickOn(String icon) {
        clickOnElByName(dropdownIcons, icon);
    }

    private void clickOnElByName(List<WebElement> elements, String name) {
        Optional<WebElement> op = elements.stream()
                .filter(el -> el.getText().contains(name))
                .findFirst();
        if (op.isPresent()) {
            waitUtilElementToBeVisible(
                    waitUntilElementToBeClickable(op.get())).click();
        } else {
            Assertions.fail("Элемент \"" + name + "\" не найден!");
        }
    }

    public StartPage closeCookiesDialog() {
        waitUntilElementToBeClickable(cookiesBtnClose).click();
        return this;
    }

}
