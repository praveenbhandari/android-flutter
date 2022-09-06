package com.example.dusra;

import com.google.firebase.firestore.Exclude;

public class Note {
    private String documentId;
    private String name;
    private String pass;

    public Note() {
        //public no-arg constructor needed
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Note(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return pass;
    }
}
