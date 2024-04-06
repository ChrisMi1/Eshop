package com.eshop.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "web_order")
public class WebOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne //many orders to the same address.
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "webOrder",cascade = CascadeType.ALL)
    private List<WebOrderQuantities> quantities;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public WebOrder() {
    }

    public WebOrder(User user, Address address, Payment payment) {
        this.user = user;
        this.address = address;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<WebOrderQuantities> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<WebOrderQuantities> quantities) {
        this.quantities = quantities;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addQuantities(List<WebOrderQuantities> webOrderQuantities){
        if(quantities==null){
            quantities=new ArrayList<>();
        }
        quantities.addAll(webOrderQuantities);
    }

    @Override
    public String toString() {
        return "WebOrder{" +
                "id=" + id +
                ", user=" + user +
                ", address=" + address +
                ", quantities=" + quantities +
                ", payment=" + payment +
                '}';
    }
}
