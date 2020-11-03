package ui.console.commands;

import java.util.HashMap;
import java.util.Map;

public class ExecuteResult {
    private final Map<String, Object> returnMap;
    private int status;

    public ExecuteResult() {
        this(0);
    }

    public ExecuteResult(int status) {
        this.status = status;
        returnMap = new HashMap<>();
    }

    public static ExecuteResult emptySuccessResult() {
        return new ExecuteResult();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getReturnMap() {
        return returnMap;
    }

    public void addReturnParameter(String name, Object param) {
        returnMap.put(name, param);
    }
}
