package ca.polymtl.micrometafy.playlist;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PlaylistElement {

    @Id
    @GeneratedValue
    protected int id;

    public int getId() { return id; }
    public void setId(final int id) { this.id = id; }
}
