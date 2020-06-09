package by.softclub.stm_api.domain.enums;

public enum OrderType {
    BUY(1, "Ордер на покупку"),
    SELL(2, "Ордер на продажу");

    OrderType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
