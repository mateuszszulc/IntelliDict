package model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.*;
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
    List<PonsSubEntry> ponsSubEntryList = new ArrayList<PonsSubEntry>();

    @Column
    Spring baseWord;


}
