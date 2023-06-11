public class Role {
    private String name;
    private String description;
    private Type type;
    private Team team;
    private String image;

    public Role(String name, String description, Type type, Team team, String imagePath) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.team = team;
        this.image = imagePath;
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

    public String getImage() {
        return image;
    }

    // test method
    public void print(){
        System.out.println(name + " est de type : " + type + " et fait partie de l'équipe : " + team );
    }
}