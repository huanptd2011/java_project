package models;

public class PaymentMethod {
    private int paymentMethodID;
    private String paymentMethodName;

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Override
    public String toString() {
        return "PaymentMethod [paymentMethodID=" + paymentMethodID + ", paymentMethodName=" + paymentMethodName+ "]";
    }

}
