package com.amrmustafa.recipes.model;

import com.amrmustafa.recipes.ui.MainActivity;

public class Recipe implements Comparable{
    private int difficulty;
    private String id;
    private String name;
    private String calories;
    private String carbos;
    private String description;
    private String fats;
    private String headline;
    private String image;
    private String proteins;
    private String thumb;
    private String time;

    public String getId() {
        return id;
    }

    public String getCalories() {
        return calories;
    }

    public String getCarbos() {
        return carbos;
    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getFats() {
        return fats;
    }

    public String getHeadline() {
        return headline;
    }

    public String getImage() {
        return image;
    }

    public String getProteins() {
        return proteins;
    }

    public String getthumb() {
        return thumb;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public void setCarbos(String carbos) {
        this.carbos = carbos;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public void setthumb(String thumb) {
        this.thumb = thumb;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe(String id, String name, String calories, String carbos, String description, int difficulty, String fats, String headline, String image, String proteins, String thumb, String time) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.carbos = carbos;
        this.description = description;
        this.difficulty = difficulty;
        this.fats = fats;
        this.headline = headline;
        this.image = image;
        this.proteins = proteins;
        this.thumb = thumb;
        this.time = time;
    }



    @Override
    public int compareTo(Object o) {
        String comparecal;
        if(MainActivity.SORT_BY==1)
             comparecal = ((Recipe) o).getFats();
        else
            comparecal  = ((Recipe) o).getCalories();

        return this.calories.compareTo(comparecal);

        }

}
