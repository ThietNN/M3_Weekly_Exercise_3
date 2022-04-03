package model;

public class PersonaUser {
    protected int id;
    protected String name;
    protected String gender;
    protected int personaID;

    public PersonaUser() {
    }

    public PersonaUser(int id, String name, String gender, int personaID) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.personaID = personaID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }
}
