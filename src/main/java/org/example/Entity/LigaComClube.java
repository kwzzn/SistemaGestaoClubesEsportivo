package org.example.Entity;

public class LigaComClube {

    private int id;
    private int clubeId;
    private int ligaId;

    public LigaComClube(int id, int clubeId, int ligaId) {
        this.id = id;
        this.clubeId = clubeId;
        this.ligaId = ligaId;
    }

    public LigaComClube(int clubeId, int ligaId) {
        this.clubeId = clubeId;
        this.ligaId = ligaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubeId() {
        return clubeId;
    }

    public void setClubeId(int clubeId) {
        this.clubeId = clubeId;
    }

    public int getLigaId() {
        return ligaId;
    }

    public void setLigaId(int ligaId) {
        this.ligaId = ligaId;
    }

    @Override
    public String toString() {
        return "LigaComClube{" +
                "id=" + id +
                ", clubeId=" + clubeId +
                ", ligaId=" + ligaId +
                '}';
    }
}
