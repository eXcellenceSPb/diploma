package com.jwt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medical")
public class Medical {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time")
    private String wound_time; //Переделать дату ранения

    @Column(name = "antib")
    private String antibiotic;

    @Column(name = "ser")
    private String serum; //сыворотка

    @Column(name = "anatox")
    private String anatoxin;

    @Column(name = "antid")
    private String antidot;

    @Column(name = "anaest")
    private String anaesthetic;

    @Column(name = "comm")
    private String commit; //что произведено (переливание и т.д.)

    @Column(name = "wound")
    private String wound;

    @Column(name = "loc")
    private String location;

    @Column(name = "diag")
    private String diagnosis;

    @Column(name = "evac")
    private String evacuation;

    @Column(name = "place")
    private String place; //куда эвакуировали

    @Column(name = "transp")
    private String transport;

    @Column(name = "queue")
    private String queue; //очередь эвакуации

    @Column(name = "info")
    private String info; //поле доп информации

    public Medical() {
    }

    public Medical(String wound_time, String wound, String antibiotic) {
        this.wound_time = wound_time;
        this.wound = wound;
        this. antibiotic = antibiotic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWound_time() {
        return wound_time;
    }

    public void setWound_time(String wound_time) {
        this.wound_time = wound_time;
    }

    public String getAntibiotic() {
        return antibiotic;
    }

    public void setAntibiotic(String antibiotic) {
        this.antibiotic = antibiotic;
    }

    public String getSerum() {
        return serum;
    }

    public void setSerum(String serum) {
        this.serum = serum;
    }

    public String getAnatoxin() {
        return anatoxin;
    }

    public void setAnatoxin(String anatoxin) {
        this.anatoxin = anatoxin;
    }

    public String getAntidot() {
        return antidot;
    }

    public void setAntidot(String antidot) {
        this.antidot = antidot;
    }

    public String getAnaesthetic() {
        return anaesthetic;
    }

    public void setAnaesthetic(String anaesthetic) {
        this.anaesthetic = anaesthetic;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getWound() {
        return wound;
    }

    public void setWound(String wound) {
        this.wound = wound;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getEvacuation() {
        return evacuation;
    }

    public void setEvacuation(String evacuation) {
        this.evacuation = evacuation;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
