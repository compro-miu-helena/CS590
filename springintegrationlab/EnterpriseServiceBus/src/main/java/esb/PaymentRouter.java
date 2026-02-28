package esb;

public class PaymentRouter {
    public String route(Order order) {
        System.out.println("Payment router: "+order);
        switch(order.getPaymentType()){
            case VISA -> {return "visapaymentchannel";}
            case MASTERCARD -> {return "mastercardpaymentchannel";}
            case PAYPAL -> {return "paypalpaymentchannel";}
            case null, default -> {return "visapaymentchannel";}
        }
    }
}
