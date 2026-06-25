package UML.DiagramatEAktivitetit;

public class OrderProcess {

    public static void main(String[] args) {
        OrderProcess process = new OrderProcess();
        process.startWorkflow();
    }

    public void startWorkflow() {
        Order myOrder = receiveOrderRequest("ORD-123", 150.50);

        double costValue = transformToCost(myOrder);

        boolean isApproved = approvePayment(costValue);

        if (isApproved) {
            submitOrder();
        }

        System.out.println("Procesi perfundoi");
    }

    private Order receiveOrderRequest(String id, double cost) {
        System.out.println("Duke marrë kërkesen për porosine");
        return new Order(id, cost);
    }

    private double transformToCost(Order order) {
        System.out.println("Transformimi");
        return order.cost;
    }

    // ACTION: Approve Payment
    private boolean approvePayment(double cost) {
        System.out.println("Duke aprovuar " + cost);
        return true;
    }

    private void submitOrder() {
        System.out.println("Porosia u drg");
    }
}

