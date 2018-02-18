package com.library.project.dao;


import com.library.project.model.Client;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class ClientDaoImpl implements ClientDao {
    private final static Logger logger= Logger.getLogger("ClientDaoImpl");
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addClient() {

    }

    @Override
    public void updateClient() {

    }

    @Override
    public void removeClientByLogin(String login) {

    }

    @Override
    public Client getClientByLogin(String login) {
        return null;
    }
}
