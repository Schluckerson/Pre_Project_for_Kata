package web.model;

public class Car {
    private String model;
    private String driveUnit;
    private int price;

    public Car() {};

    public Car (String model, String driveUnit, int price) {
        this.model = model;
        this.driveUnit = driveUnit;
        this.price = price;
    }

    public String getModel() {
        return model;
    }
    public int getPrice() {
        return price;
    }
    public String getDriveUnit() {
        return driveUnit;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
