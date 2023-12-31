/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author mfgperez
 */
public class User implements Serializable {

    private String roleName = "";
    private String email;
    private String firstName;
    private String lastName;
    private  String password;
    private Role role;
    private int roleID = 0;

 

    public User(String email, String firstName, String lastName, String password, String roleName) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
        this.password = password;

    }

    public User(String email, String firstName, String lastName, String password, int role) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.roleName = roleName;
        this.password = password;
        roleID = role;

    }

    public User(String email, String firstName, String lastName, String password, Role role) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.roleName = roleName;
        this.password = password;
        this.role = role;
        this.roleName = role.getRoleName();

    }


    public String toString() {

        return "User email: " + this.email + " user first name: " + this.firstName + " user last name: " + this.lastName + " user role: " + this.role;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password; 
    }
    public String getLastName() {
        return lastName;
    }



    public String getRoleName() {
        return role.getRoleName();
    }

    public int getRoleID() {
        return role.getRoleID();
    }

}
