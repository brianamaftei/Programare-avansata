package Compulsory;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name LIKE :name")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

