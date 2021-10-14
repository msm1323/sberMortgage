package ru.msm.framework.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.msm.framework.managers.PageManager;

public class StartPageStep {

    PageManager PAGE_MANAGER = PageManager.getINSTANCE();

    private static final Logger logger = LoggerFactory.getLogger(StartPageStep.class);

    @Тогда("^перейти на сайт https://www.sberbank.ru/ru/person и в верхнем меню нажать на \"(.+)\"$")
    public void clickOnTopMenuIcon(String icon) {
        PAGE_MANAGER.getStartPage().closeCookiesDialog().clickOnTopMenuIcon(icon);
        logger.info("На стартовой странице нажата иконка \"{}\"", icon);
    }

    @И("^дождаться открытия выпадающего меню и выбрать \"(.+)\"$")
    public void waitDropdownMenuAndClickOn(String subIcon) {
        PAGE_MANAGER.getStartPage().waitDropdownMenuAndClickOn(subIcon);
        logger.info("В выпадающем меню выбрано \"{}\"", subIcon);
    }
}
