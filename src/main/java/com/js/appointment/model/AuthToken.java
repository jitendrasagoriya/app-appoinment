package com.js.appointment.model;

import java.util.Objects;

public class AuthToken {

    private String token;
    private String drId;
    private String drName;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public AuthToken(String token, String drId, String drName) {
        this.token = token;
        this.drId = drId;
        this.drName = drName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(token, authToken.token) &&
                Objects.equals(drId, authToken.drId) &&
                Objects.equals(drName, authToken.drName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, drId, drName);
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "token='" + token + '\'' +
                ", drId='" + drId + '\'' +
                ", drName='" + drName + '\'' +
                '}';
    }
}
