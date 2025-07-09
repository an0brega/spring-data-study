package br.com.backsolutions.springdata.study.orm;

import jakarta.persistence.*;

@Entity
@Table(name = "WORK_UNITS")
public class WorkUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String unitDescription;
    private String unitAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    @Override
    public String toString() {
        return "WorkUnit{" +
                "id=" + id +
                ", unitAddress='" + unitAddress + '\'' +
                ", unitDescription='" + unitDescription + '\'' +
                '}';
    }
}
