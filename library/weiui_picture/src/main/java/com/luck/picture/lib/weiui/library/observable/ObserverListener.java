package com.luck.picture.lib.weiui.library.observable;

import com.luck.picture.lib.weiui.library.entity.LocalMedia;
import com.luck.picture.lib.weiui.library.entity.LocalMediaFolder;

import java.util.List;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.lib.weiui.library.observable
 * email：893855882@qq.com
 * data：17/1/16
 */
public interface ObserverListener {
    void observerUpFoldersData(List<LocalMediaFolder> folders);

    void observerUpSelectsData(List<LocalMedia> selectMedias);
}
