package esb;

public class InternationalShippingRouter {
    public String route(Order order) {
        if (order.isInternational()) {
            return "internationalshippingchannel";
        } else {
            return "domesticshippingchannel";
        }
    }
}
