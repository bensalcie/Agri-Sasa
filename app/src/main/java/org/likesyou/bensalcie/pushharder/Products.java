package org.likesyou.bensalcie.pushharder;

public class Products {
    String post_id;
    String product_name;
    String product_category;
    String product_quantity;
    String product_price;
    String post_time;
    String post_image;
    String product_poster;

    public String getProduct_poster() {
        return product_poster;
    }

    public void setProduct_poster(String product_poster) {
        this.product_poster = product_poster;
    }

    public Products(String product_poster) {
        this.product_poster = product_poster;
    }



    public Products(String post_id, String product_name, String product_category, String product_quantity, String product_price, String post_time, String post_image) {
        this.post_id = post_id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
        this.post_time = post_time;
        this.post_image = post_image;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public Products(){

    }
}
