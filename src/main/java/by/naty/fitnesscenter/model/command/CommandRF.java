package by.naty.fitnesscenter.model.command;

public class CommandRF {
    public enum DispatchType {
        REDIRECT,
        FORWARD
    }

    private DispatchType dispatchType;
    private String page;

    public CommandRF(DispatchType dispatchType, String page) {
        this.dispatchType = dispatchType;
        this.page = page;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(DispatchType dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
