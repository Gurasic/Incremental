package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private String name;
    private List<Stage> stages;
    private List<ArmorStand> armorStands ;
    private Location location;
    private boolean finished;

    public Plant(String name, List<Stage> stages) {
        this.name = name;
        this.stages = stages;
        this.armorStands = new ArrayList<>();
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
    public List<ArmorStand> getArmorStands() {
        return this.armorStands;
    }
    public ArmorStand getArmorStand(int i) {
        return this.armorStands.get(i);
    }
    public List<Stage> getStages() {
        return this.stages;
    }
    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public boolean getFinished() {
        return this.finished;
    }

    public Stage getStageForTime(int timeLeft) {
        for (Stage stage : stages) {
            if (timeLeft >= stage.getStartSeconds() && timeLeft <= stage.getEndSeconds()) {
                return stage;
            }
        }
        return null;
    }
}