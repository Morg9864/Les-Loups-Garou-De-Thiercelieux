package source.model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Role {
    private String name;
    private String description;
    private Type type;
    private Team team;
    private Image image;
    private String imagePath;

    public Role(String name, String description, Type type, Team team, String imagePath) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.team = team;
        this.imagePath = imagePath;
        try {
            this.image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            this.image = null;
            System.out.println(imagePath + " not found");
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    public Image getImage() {
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }

    // test method
    public void print(){
        System.out.println(name + " est de type : " + type + " et fait partie de l'équipe : " + team );
    }
}