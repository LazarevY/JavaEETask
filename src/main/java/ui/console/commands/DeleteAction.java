package ui.console.commands;

import console.io.InputManager;
import data.AttributeFilterType;
import data.Filter;
import logic.business.BusinessLogic;
import logic.expressions.comparators.OperatorType;

import java.util.Collections;
import java.util.Map;

public class DeleteAction implements Command {

    private final BusinessLogic logic;

    public static final String CHOOSE_MSG = "Input event id";

    public DeleteAction(BusinessLogic logic) {
        this.logic = logic;
    }

    @Override
    public ExecuteResult execute(Map<String, Object> args) {
        Integer id = InputManager.getInstance().getIntFromStandardInput(CHOOSE_MSG);

        logic.removeEvents(
                Collections.singletonList(new Filter<>("id",OperatorType.Equal, id, Integer.class, AttributeFilterType.And))
        );

        return ExecuteResult.emptySuccessResult();

    }
}
