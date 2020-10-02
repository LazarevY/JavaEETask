package ui;

import logic.business.BusinessLogic;

public abstract class UI {
    protected BusinessLogic logic;

    public UI(BusinessLogic logic) {
        this.logic = logic;
    }
}
