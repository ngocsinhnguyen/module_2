package mvc.entity;

public class Dish implements Comparable<Dish> {
    private int id;
    private String name;
    private int price;
    private String description;

    public Dish() {
    }

    public Dish(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Dish o) {
        if(this.id > o.id) {
            return 1;
        }  else if(this.id < o.id) {
            return -1;
        } else {
            return 0;
        }

    }
}
