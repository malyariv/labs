package com.malyariv.library_site;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bC=new BCryptPasswordEncoder();

        System.out.println("user="+bC.encode("user"));
    }
}
