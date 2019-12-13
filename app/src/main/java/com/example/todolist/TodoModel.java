package com.example.todolist;

public class TodoModel {
    int noteId;
    String noteTitle;
    String noteDesrp;
    String time;

    TodoModel( int noteId, String noteTitle, String noteDesrp, String time){
       this.noteId = noteId;
       this.noteTitle = noteTitle;
       this.noteDesrp = noteDesrp;
       this.time = time;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDesrp() {
        return noteDesrp;
    }

    public void setNoteDesrp(String noteDesrp) {
        this.noteDesrp = noteDesrp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
