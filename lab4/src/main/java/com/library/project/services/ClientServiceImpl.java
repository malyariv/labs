package com.library.project.services;

import com.library.project.dao.ClientDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientServiceImpl implements ClientService{

    private ClientDao clientDao;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public void registrate() {

    }

    @Override
    @Transactional
    public void modify() {

    }

    @Override
    @Transactional
    public void removeProfile(String login) {

    }

    @Override
    @Transactional
    public boolean authorize(String login, String password) {
        return false;
    }
}
