package com.library.project.dao;


import com.library.project.model.Client;

public interface ClientDao {
    void addClient();
    void updateClient();
    void removeClientByLogin(String login);
    Client getClientByLogin(String login);
}
