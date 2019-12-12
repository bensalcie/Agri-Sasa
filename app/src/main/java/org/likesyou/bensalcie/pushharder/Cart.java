package org.likesyou.bensalcie.pushharder;

public class Cart {

    String name,qty,price,poster_id,post_id,time,post_image;

    public Cart(String name, String qty, String price, String poster_id, String post_id, String time, String post_image) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.poster_id = poster_id;
        this.post_id = post_id;
        this.time = time;
        this.post_image = post_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(String poster_id) {
        this.poster_id = poster_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public Cart()
    {

    }
}
