/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.ESSLDisplay;

import java.awt.image.BufferedImage;

/**
 *
 * @author Ratan
 */
public class Member {

    private int seq;
    private String name;
    private BufferedImage image;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member(String name, BufferedImage image, int seq) {
        this.name = name;
        this.image = image;
        this.seq = seq;
    }
}
