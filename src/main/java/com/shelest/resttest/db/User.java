package com.shelest.resttest.db;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "USER")
public class User {

    public User() {
    }

    public User(String name, Date birthday, Gender gender) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", columnDefinition="NVARCHAR(255)", nullable = false, unique = true)
    @NotNull(message = "error.cannot.be.null")
    @Size(message = "error.not.correct.size", min = 1, max = 255)
    private String name;

    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}