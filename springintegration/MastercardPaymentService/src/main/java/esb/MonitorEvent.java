package esb;

public class MonitorEvent {
    private String source;
    private String orderNumber;
    private String step;

    public MonitorEvent() {
    }

    public MonitorEvent(String source, String orderNumber, String step) {
        this.source = source;
        this.orderNumber = orderNumber;
        this.step = step;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
