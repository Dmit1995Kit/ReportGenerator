package com.example.ReportGenerator.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

public enum Role implements GrantedAuthority{

    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
