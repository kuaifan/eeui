package com.luck.picture.lib.weiui.library.observable;


/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.lib.weiui.library.observable
 * email：893855882@qq.com
 * data：17/1/16
 */
public interface SubjectListener {
    void add(ObserverListener observerListener);

    void remove(ObserverListener observerListener);
}
