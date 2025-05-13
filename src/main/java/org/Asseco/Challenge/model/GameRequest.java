package org.Asseco.Challenge.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class GameRequest {
    @NotNull(message = "As ações da \"rightPerson\" não podem ser nulas.")
    private List<String> rightPerson;

    @NotNull(message = "As ações da \"leftPerson\" não podem ser nulas.")
    private List<String> leftPerson;

    // Getters e Setters
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