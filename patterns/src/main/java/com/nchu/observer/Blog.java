package com.nchu.observer;

import java.util.Observable;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/21 17:25
 */
public class Blog extends Observable {

    public void publishArtical(){
        Artical artical = new Artical();
        artical.setTitle("title");
        artical.setCoutent("countent");
        this.setChanged();
        this.notifyObservers(artical);
    }
}
