package com.library.project.services;

public interface ClientService {
    void registrate();
    void modify();
    void removeProfile(String login);
    boolean authorize(String login, String password);

}
