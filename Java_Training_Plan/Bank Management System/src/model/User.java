package model;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String name;
    protected String password;
    protected boolean blocked;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.blocked = false;
    }

    public String getName() {
        return name;
    }
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    public boolean isBlocked(){
        return blocked;
    }

    public void blocked(){
        blocked = true;
    }

    public abstract String getRole();

}
