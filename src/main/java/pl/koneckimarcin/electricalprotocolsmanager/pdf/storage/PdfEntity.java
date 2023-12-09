package pl.koneckimarcin.electricalprotocolsmanager.pdf.storage;

import jakarta.persistence.*;

@Entity
public class PdfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Lob
    private byte[] data;

    public PdfEntity() {
    }

    public PdfEntity(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
