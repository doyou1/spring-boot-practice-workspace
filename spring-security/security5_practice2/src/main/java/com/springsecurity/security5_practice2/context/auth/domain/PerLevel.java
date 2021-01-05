package com.springsecurity.security5_practice2.context.auth.domain;

public enum PerLevel {
    VIEW, // read
    EDIT, // create, update, delete
    ADMIN // create, read, update, delete
}
