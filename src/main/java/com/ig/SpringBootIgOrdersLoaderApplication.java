package com.ig;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ig.service.StorageService;

@SpringBootApplication
public class SpringBootIgOrdersLoaderApplication implements CommandLineRunner {

	@Resource
	StorageService storageService;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootIgOrdersLoaderApplication.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}

}