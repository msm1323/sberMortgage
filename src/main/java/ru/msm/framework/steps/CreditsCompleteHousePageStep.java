package ru.msm.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import ru.msm.framework.managers.PageManager;

import java.io.InputStream;
import java.util.stream.Stream;

public class CreditsCompleteHousePageStep {

    PageManager PAGE_MANAGER = PageManager.getINSTANCE();

    @Тогда("^заполнить поля$")
    public void fillFields(DataTable arg) {
        PAGE_MANAGER.getCreditsCompleteHousePage().fillFields(arg.asLists());
    }

    @И("убрать галочку {string}")
    public void unselect(String arg0) {
        PAGE_MANAGER.getCreditsCompleteHousePage().switcherDiscountsBlock(false, arg0);
    }

    @И("поставить галочку {string}")
    public void select(String arg0) {
        PAGE_MANAGER.getCreditsCompleteHousePage().switcherDiscountsBlock(true, arg0);
    }

    @Тогда("проверить значения полей")
    public void checkLabelsValues(DataTable arg) {

        PAGE_MANAGER.getCreditsCompleteHousePage().checkLabelsValues(arg.asLists());
    }

}
