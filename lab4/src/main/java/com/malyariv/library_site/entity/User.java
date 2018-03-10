package com.malyariv.library_site.entity;


import com.malyariv.library_site.controller.forms.Human;
import com.malyariv.library_site.controller.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    private String role=Roles.ROLE_USER.name();
    @Column(name = "enabled")
    private boolean enabled=true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client clientData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employeeData;


    public User() {
    }

    public User(RegistrationForm registrationForm, Human human, boolean client) {
        username= registrationForm.getUsername();
        password= registrationForm.getCryptedPassword();
        human.setFirstname(registrationForm.getFirstname());
        human.setLastname(registrationForm.getLastname());
        human.setEmail(registrationForm.getEmail());
        if (client) {
            clientData=(Client) human;
        } else {
            employeeData=(Employee) human;
            role=Roles.ROLE_STAFF.name();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Client getClientData() {
        return clientData;
    }

    public void setClientData(Client clientData) {
        this.clientData = clientData;
    }

    public Employee getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(Employee employeeData) {
        this.employeeData = employeeData;
    }

    public boolean update(RegistrationForm registrationForm) {
        boolean flag=false;
        if (registrationForm.getUsername()!="") {
            username=registrationForm.getUsername();
            flag=true;
        }
        if (registrationForm.getPassword()!="") {
            password=registrationForm.getCryptedPassword();
            flag=true;
        }
        if (registrationForm.getFirstname()!="") {
            clientData.setFirstname(registrationForm.getFirstname());
            flag=true;
        }
        if (registrationForm.getLastname()!="") {
            clientData.setLastname(registrationForm.getLastname());
            flag=true;
        }
        if (registrationForm.getEmail()!="") {
            clientData.setEmail(registrationForm.getEmail());
            flag=true;
        }
        return flag;
    }
}
