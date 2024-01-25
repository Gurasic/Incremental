package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plant {
    private String name;
    private List<Stage> stages;
    private String HexColor;
    private Map<String, ArmorStand> armorStands;
    private Location location;
    private boolean finished;
    private boolean finishedBuff;
    private int speedLevel;
    private int BuffDuration;

    public Plant(String name, String hexcolor, int BuffDuration, List<Stage> stages, int speedLevel) {
        this.name = name;
        this.stages = stages;
        this.armorStands  = new HashMap<>();
        this.HexColor = hexcolor;
        this.speedLevel = speedLevel;
        this.BuffDuration = BuffDuration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Location GetLocation() {
        return this.location;
    }
    public Map<String, ArmorStand> getArmorStands() {
        return this.armorStands;
    }
    public String getHexColor() {
        return this.HexColor;
    }
    public void addArmorStand(String key, ArmorStand armorStand) {
        this.armorStands.put(key, armorStand);
    }
    public int GetBuffDuration() {
        return BuffDuration;
    }
    public void SetBuffDuration(int BuffDuration) {
        this.BuffDuration = BuffDuration;
    }

    public ArmorStand getArmorStand(String key) {
        return this.armorStands.get(key);
    }
    public List<Stage> getStages() {
        return this.stages;
    }
    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
    public void setFinished(boolean finishedBuff) {
        this.finishedBuff = finishedBuff;
    }
    public boolean getFinishedBuff() {
        return this.finishedBuff;
    }
    public void setFinishedBuff(boolean finished) {
        this.finished = finished;
    }
    public boolean getFinished() {
        return this.finished;
    }

    public Stage getStageForTime(int timeLeft) {
        int adjustedTimeLeft = timeLeft + (int)(timeLeft * (speedLevel * 0.01));
        for (Stage stage : stages) {
            if (adjustedTimeLeft >= stage.getStartSeconds() && adjustedTimeLeft <= stage.getEndSeconds()) {
                return stage;
            }
        }
        return null;
    }
}