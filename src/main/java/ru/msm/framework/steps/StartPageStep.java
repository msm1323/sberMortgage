package ru.msm.framework.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import ru.msm.framework.managers.PageManager;

public class StartPageStep {

    PageManager PAGE_MANAGER = PageManager.getINSTANCE();

    @Тогда("^перейти на сайт https://www.sberbank.ru/ru/person и в верхнем меню нажать на \"([^\"]*)\"$")
    public void clickOnTopMenuIcon(String icon) {
        PAGE_MANAGER.getStartPage().closeCookiesDialog().clickOnTopMenuIcon(icon);
    }

    @И("^дождаться открытия выпадающего меню и выбрать \"([^\"]*)\"$")
    public void waitDropdownMenu(String subIcon) {
        PAGE_MANAGER.getStartPage().waitDropdownMenu(subIcon);
    }
}
