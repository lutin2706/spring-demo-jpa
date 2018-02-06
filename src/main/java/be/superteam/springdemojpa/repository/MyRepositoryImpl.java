package be.superteam.springdemojpa.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepositoryImpl {

    public String test() {
        throw new IllegalArgumentException("Invalid input");
    }
}
