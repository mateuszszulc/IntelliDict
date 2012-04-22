package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 15.04.12
 * Time: 16:07
 */

@Entity
public class PonsEntry {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    String baseWord;

    @OneToMany
    List<PonsSubEntry> ponsSubEntryList = new ArrayList<PonsSubEntry>();

    public List<PonsSubEntry> getPonsSubEntryList() {
        return ponsSubEntryList;
    }

    public void setPonsSubEntryList(List<PonsSubEntry> ponsSubEntryList) {
        this.ponsSubEntryList = ponsSubEntryList;
    }

    public String getBaseWord() {
        return baseWord;
    }

    public void setBaseWord(String baseWord) {
        this.baseWord = baseWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
