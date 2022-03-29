package com.example.telenor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private ReadFile readFile;

    @Override
    public void run(String...args) throws Exception {
        readFile.readFile();

    }
}
