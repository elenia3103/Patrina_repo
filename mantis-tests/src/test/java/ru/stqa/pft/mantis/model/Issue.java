package ru.stqa.pft.mantis.model;

public class Issue {
    private int id;
    private String summary;
    private String description;
    private Project project;

    public String getCategory() {
        return category;
    }

    public Issue withCategory(String category) {
        this.category = category;
        return this;
    }

    private String category;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
