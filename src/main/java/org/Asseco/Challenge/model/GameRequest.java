package org.Asseco.Challenge.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class GameRequest {
    @NotNull(message = "rightPerson actions cannot be null")
    private List<String> rightPerson;

    @NotNull(message = "leftPerson actions cannot be null")
    private List<String> leftPerson;

    // Getters and Setters
    public List<String> getRightPerson() {
        return rightPerson;
    }

    public void setRightPerson(List<String> rightPerson) {
        this.rightPerson = rightPerson;
    }

    public List<String> getLeftPerson() {
        return leftPerson;
    }

    public void setLeftPerson(List<String> leftPerson) {
        this.leftPerson = leftPerson;
    }
}