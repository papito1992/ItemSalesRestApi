package com.SalesAPI.SalesAPI;

class SalesItemNotFoundException extends RuntimeException {
    SalesItemNotFoundException(Long id) {
        super("Could not find Item " + id);
    }
}
