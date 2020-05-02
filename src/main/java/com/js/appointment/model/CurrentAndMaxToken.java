package com.js.appointment.model;

import java.util.Objects;

public class CurrentAndMaxToken {
    private Integer currentToken;
    private Integer maxToken;

    public CurrentAndMaxToken() {
    }

    public CurrentAndMaxToken(Integer currentToken, Integer maxToken) {
        this.currentToken = currentToken;
        this.maxToken = maxToken;
    }

    public Integer getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Integer currentToken) {
        this.currentToken = currentToken;
    }

    public Integer getMaxToken() {
        return maxToken;
    }

    public void setMaxToken(Integer maxToken) {
        this.maxToken = maxToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentAndMaxToken that = (CurrentAndMaxToken) o;
        return Objects.equals(currentToken, that.currentToken) &&
                Objects.equals(maxToken, that.maxToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentToken, maxToken);
    }

    @Override
    public String toString() {
        return "CurrentAndMaxToken{" +
                "currentToken=" + currentToken +
                ", maxToken=" + maxToken +
                '}';
    }

    public String toJson() {
        return "{" +
                "currentToken :" + currentToken +
                ", maxToken :" + maxToken +
                '}';
    }
}
