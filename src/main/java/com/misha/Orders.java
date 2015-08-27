/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

import java.util.List;

/**
 * Represents one order
 * (plural in the name - to correspond to the input JSON)
 */
public class Orders {
    private int Header;
    private List<com.misha.Lines> Lines;

    public Orders() {
    }

    public int getHeader() {
        return Header;
    }

    public void setHeader(int header) {
        Header = header;
    }

    public List<com.misha.Lines> getLines() {
        return Lines;
    }

    public void setLines(List<com.misha.Lines> lines) {
        this.Lines = lines;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Orders{");
        sb.append("Header=").append(Header);
        sb.append(", Lines=").append(Lines);
        sb.append('}');
        return sb.toString();
    }
}
