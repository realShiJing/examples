package com.nchu.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/21 17:28
 */
public class User implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Artical artical = (Artical)arg;
        System.out.println("title"+artical.getTitle() + " countent" + artical.getCoutent());
    }
}
