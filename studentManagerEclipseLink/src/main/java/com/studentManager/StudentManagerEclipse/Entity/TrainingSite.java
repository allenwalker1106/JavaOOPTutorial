package com.studentManager.StudentManagerEclipse.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="training_sites")
@NamedQueries({
        @NamedQuery(name="TrainingSite.getById",query="SELECT t FROM TrainingSite t WHERE t.id=:id"),
        @NamedQuery(name="TrainingSite.findAll",query="SELECT t FROM TrainingSite t"),
        @NamedQuery(name="TrainingSite.findAllById",query="SELECT t FROM TrainingSite t WHERE t.id IN :ids")
})
public class TrainingSite {
    @Id
    @Column(name="training_site_id",unique=true,nullable=false,length=50)
    private String id;

    @Column(name="training_site_name",nullable=false)
    private String name;

    @OneToMany(mappedBy="trainingSite")
    private Set<ServiceStudent> serviceStudents;


    public TrainingSite() {
    }

    public TrainingSite(String id) {
        this.id = id;
    }

    public TrainingSite(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ServiceStudent> getServiceStudents() {
        return serviceStudents;
    }

    public void setServiceStudents(Set<ServiceStudent> serviceStudents) {
        this.serviceStudents = serviceStudents;
    }

    @Override
    public String toString() {
        return "TrainingSite{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceStudents=" + serviceStudents +
                '}';
    }
}
