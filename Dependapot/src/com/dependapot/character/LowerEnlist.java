package com.dependapot.character;

public class LowerEnlist {
    private String rank;

    public LowerEnlist(String _rank) {
        this.rank = _rank;
    }

    public String getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return "LowerEnlist{" +
                "rank='" + rank +'\'' + '}';
    }
}
