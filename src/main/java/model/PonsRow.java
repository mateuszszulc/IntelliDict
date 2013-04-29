package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 15.04.12
 * Time: 16:08
 */
@Entity
public class PonsRow {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    String original;

    @Column
    String translation;

    public PonsRow(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return new String(original + "=" + translation);
    }

    public static PonsRow createInstanceFromRawText(String rawText) {
        String[] splittedLine = rawText.split("=");
        return splittedLine.length == 2 ? new PonsRow(splittedLine[0], splittedLine[1]) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PonsRow)) return false;

        PonsRow ponsRow = (PonsRow) o;

        if (id != null ? !id.equals(ponsRow.id) : ponsRow.id != null) return false;
        if (!original.equals(ponsRow.original)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + original.hashCode();
        return result;
    }
}
