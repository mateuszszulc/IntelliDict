package model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 15.04.12
 * Time: 16:08
 */

@Entity
public class PonsSubEntry {

    @Id
    @GeneratedValue
    private Integer id;

    //List<PonsRow> subEntryRows = new ArrayList<PonsRow>();

    @Column
    String subEntryHeader;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubEntryHeader() {
        return subEntryHeader;
    }

    public void setSubEntryHeader(String subEntryHeader) {
        this.subEntryHeader = subEntryHeader;
    }

}
