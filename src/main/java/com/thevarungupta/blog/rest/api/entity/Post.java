package com.thevarungupta.blog.rest.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "title" }
                )
        }
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotEmpty(message = "title should not be null or empty")
    @Size(min = 3, max = 100, message = "title should be between 3 to 100 characters")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "description should not be null or empty")
    @Size(min = 10, message = "description should be min 3 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty(message = "content should not be null or empty")
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
