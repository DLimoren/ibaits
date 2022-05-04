package art.caixi.pojo;

public class Orders {
    private Integer id;
    private String orderNumber;
    private Double orderPrice;

    public Orders() {
    }

    public Orders(Integer id, String orderNumber, Double orderPrice) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderPrice=" + orderPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
