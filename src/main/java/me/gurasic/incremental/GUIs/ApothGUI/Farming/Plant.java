package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import java.util.List;

public class Plant {
    private String name;
    private List<Stage> stages;

    public Plant(String name, List<Stage> stages) {
        this.name = name;
        this.stages = stages;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stage> getStages() {
        return this.stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
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