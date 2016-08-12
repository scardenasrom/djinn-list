package com.example.scardenas.djinnlist.data;

import java.io.Serializable;

public class Djinni implements Comparable<Djinni>, Serializable {

    private int order;
    private String name;
    private String element;
    private String flavor;
    private String location;
    private String catchingDescription;
    private String battleEffect;
    private boolean caught;
    private boolean fights;
    private String setBonus;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCatchingDescription() {
        return catchingDescription;
    }

    public void setCatchingDescription(String catchingDescription) {
        this.catchingDescription = catchingDescription;
    }

    public String getBattleEffect() {
        return battleEffect;
    }

    public void setBattleEffect(String battleEffect) {
        this.battleEffect = battleEffect;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    public boolean doesItFight() {
        return fights;
    }

    public void setFights(boolean fights) {
        this.fights = fights;
    }

    public String getSetBonus() {
        return setBonus;
    }

    public void setSetBonus(String setBonus) {
        this.setBonus = setBonus;
    }

    @Override
    public int compareTo(Djinni djinni) {
        return this.getOrder() - djinni.getOrder();
    }

}
