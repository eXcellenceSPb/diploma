package com.jwt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "organisation")
    private String organisation; //мед часть

    @Column(name = "date")
    private String date; //Переделать дату выдачи карточки

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "crank")
    private String rank; //звание

    @Column(name = "cunit")
    private String unit; //вч

    @Column(name = "num")
    private String number; // номер жетона

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "med_card",
            joinColumns = {@JoinColumn(name = "card_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "med_id", referencedColumnName = "id")}
    )
    private List<Medical> medical = new ArrayList<>();

    public Card() {
    }

    public Card(String organisation, String date, String first_name, String last_name, String rank, String unit, String number) {
        this.organisation = organisation;
        this.date = date;
        this.firstname = first_name;
        this.lastname = last_name;
        this.rank = rank;
        this.unit = unit;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String org) {
        this.organisation = org;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Medical> getMedical() {
        return medical;
    }

    public void setMedical(List<Medical> medical) {
        this.medical = medical;
    }

}
