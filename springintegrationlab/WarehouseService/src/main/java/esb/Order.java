package esb;

public class Order {

	private String orderNumber;
	private double amount;
	private boolean international;

	private PaymentType paymentType;

	public Order() {
	}

	public Order(String orderNumber, double amount, boolean international, PaymentType paymentType) {
		this.orderNumber = orderNumber;
		this.amount = amount;
		this.international = international;
		this.paymentType = paymentType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isInternational() {
		return international;
	}

	public void setInternational(boolean international) {
		this.international = international;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderNumber='" + orderNumber + '\'' +
				", amount=" + amount +
				", international=" + international +
				", paymentType=" + paymentType +
				'}';
	}
}
