package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import org.bukkit.Material;

public class Stage {
    private String name;
    private int startSeconds;
    private int endSeconds;
    private Material material;

    public Stage(String name, int startSeconds, int endSeconds, Material material) {
        this.name = name;
        this.startSeconds = startSeconds;
        this.endSeconds = endSeconds;
        this.material = material;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartSeconds() {
        return this.startSeconds;
    }

    public void setStartSeconds(int startSeconds) {
        this.startSeconds = startSeconds;
    }

    public int getEndSeconds() {
        return this.endSeconds;
    }

    public void setEndSeconds(int endSeconds) {
        this.endSeconds = endSeconds;
    }
    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}