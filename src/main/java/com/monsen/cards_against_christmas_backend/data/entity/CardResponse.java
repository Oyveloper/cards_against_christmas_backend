package com.monsen.cards_against_christmas_backend.data.entity;

public class CardResponse {
    private String _createdAt;
    private String _id;
    private String _rev;
    private String _type;
    private String _updatedAt;
    private String content;
    private int number_of_blank;

    public String get_createdAt() {
        return _createdAt;
    }

    public void set_createdAt(String _createdAt) {
        this._createdAt = _createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_updatedAt() {
        return _updatedAt;
    }

    public void set_updatedAt(String _updatedAt) {
        this._updatedAt = _updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber_of_blank() {
        return number_of_blank;
    }

    public void setNumber_of_blank(int number_of_blank) {
        this.number_of_blank = number_of_blank;
    }
}
