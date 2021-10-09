package code.fortomorrow.foodie.Model;

public class FoodListModel {
    private int ImageView;
    private String name;
    private String price;

    public FoodListModel(int imageView, String name, String price) {
        ImageView = imageView;
        this.name = name;
        this.price = price;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
