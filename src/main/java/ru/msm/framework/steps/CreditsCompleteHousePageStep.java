package ru.msm.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.msm.framework.managers.PageManager;

public class CreditsCompleteHousePageStep {

    PageManager PAGE_MANAGER = PageManager.getINSTANCE();

    private static final Logger logger = LoggerFactory.getLogger(CreditsCompleteHousePageStep.class);

    @Тогда("^заполнить поля$")
    public void fillFields(DataTable arg) {
        PAGE_MANAGER.getCreditsCompleteHousePage().fillFields(arg.asLists());
        logger.info("Поля \"{}\", \"{}\" и \"{}\" заполнены",
                arg.asLists().get(0).get(0), arg.asLists().get(1).get(0), arg.asLists().get(2).get(0));
    }

    @И("^убрать галочку \"(.+)\"$")
    public void unselect(String arg0) {
        PAGE_MANAGER.getCreditsCompleteHousePage().switcherDiscountsBlock(false, arg0);
        logger.info("Выключен чекбокс \"{}\"", arg0);
    }

    @И("^поставить галочку \"(.+)\"$")
    public void select(String arg0) {
        PAGE_MANAGER.getCreditsCompleteHousePage().switcherDiscountsBlock(true, arg0);
        logger.info("Включен чекбокс \"{}\"", arg0);
    }

    @Тогда("проверить значения полей")
    public void checkLabelsValues(DataTable arg) {
        PAGE_MANAGER.getCreditsCompleteHousePage().checkLabelsValues(arg.asLists());
        logger.info("Поля \"{}\", \"{}\", \"{}\" и \"{}\" проверены", arg.asLists().get(0).get(0),
                arg.asLists().get(1).get(0), arg.asLists().get(2).get(0), arg.asLists().get(3).get(0));
    }

}
