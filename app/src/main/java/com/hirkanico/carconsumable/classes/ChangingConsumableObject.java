package com.hirkanico.carconsumable.classes;

public class ChangingConsumableObject{

    public String id;
    public String consumableId;
    public String consumableName;
    public String changeDate;
    public String previousChangeKilometer;
    public String kilometerToChange;
    public String changePrice;
    public String description;

    public ChangingConsumableObject(String id,  String consumableId, String changeDate, String previousChangeKilometer, String kilometerToChange, String changePrice, String description)
    {
        this.id = id;
        this.consumableId = consumableId;
        this.changeDate = changeDate;
        this.previousChangeKilometer = previousChangeKilometer;
        this.kilometerToChange = kilometerToChange;
        this.changePrice = changePrice;
        this.description = description;
    }

    public ChangingConsumableObject(String id, String consumableId, String consumableName, String changeDate, String previousChangeKilometer, String kilometerToChange, String changePrice, String description)
    {
        this.id = id;
        this.consumableId = consumableId;
        this.consumableName = consumableName;
        this.changeDate = changeDate;
        this.previousChangeKilometer = previousChangeKilometer;
        this.kilometerToChange = kilometerToChange;
        this.changePrice = changePrice;
        this.description = description;
    }
}
