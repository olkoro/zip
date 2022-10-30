package com.example.demo.message;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "message", schema = "demo")
public class Message {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "value")
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
