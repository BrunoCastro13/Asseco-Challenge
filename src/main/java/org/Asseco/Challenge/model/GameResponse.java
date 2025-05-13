package org.Asseco.Challenge.model;

public class GameResponse {
    private int rightPerson;
    private int leftPerson;

    public GameResponse() {
    }

    public GameResponse(int rightPerson, int leftPerson) {
        this.rightPerson = rightPerson;
        this.leftPerson = leftPerson;
    }

    // Getters e Setters
    public int getRightPerson() {
        return rightPerson;
    }

    public void setRightPerson(int rightPerson) {
        this.rightPerson = rightPerson;
    }

    public int getLeftPerson() {
        return leftPerson;
    }

    public void setLeftPerson(int leftPerson) {
        this.leftPerson = leftPerson;
    }
}