package com.luck.picture.lib.weiui.ui.module;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.luck.picture.lib.weiui.library.PictureSelectionModel;
import com.luck.picture.lib.weiui.library.PictureSelector;
import com.luck.picture.lib.weiui.library.compress.Luban;
import com.luck.picture.lib.weiui.library.compress.OnCompressListener;
import com.luck.picture.lib.weiui.library.config.PictureConfig;
import com.luck.picture.lib.weiui.library.config.PictureMimeType;
import com.luck.picture.lib.weiui.library.entity.EventEntity;
import com.luck.picture.lib.weiui.library.entity.LocalMedia;
import com.luck.picture.lib.weiui.library.rxbus2.RxBus;
import com.luck.picture.lib.weiui.library.tools.PictureFileUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.kuaifan.weiui.activity.PageActivity;
import vip.kuaifan.weiui.extend.bean.PageBean;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiMap;
import vip.kuaifan.weiui.extend.module.weiuiPage;
import vip.kuaifan.weiui.extend.module.weiuiParse;

import static android.app.Activity.RESULT_OK;


@WeexModule(name = "weiui_picture")
public class weiuiPictureModule extends WXModule {

    private static final String TAG = "weiuiPictureModule";

    private Handler mHandler = new Handler();

    private List<LocalMedia> toLocalMedia(JSONArray selectedList) {
        List<LocalMedia> selected = new ArrayList<>();
        if (selectedList != null) {
            for (int i = 0; i <  selectedList.size(); i++) {
                JSONObject tempJson = weiuiJson.parseObject(selectedList.get(i));
                LocalMedia tempMedia = new LocalMedia();
                tempMedia.setDuration(weiuiJson.getInt(tempJson, "duration"));
                tempMedia.setPath(weiuiJson.getString(tempJson, "path"));
                tempMedia.setCut(weiuiJson.getBoolean(tempJson, "cut"));
                tempMedia.setNum(weiuiJson.getInt(tempJson, "num"));
                tempMedia.setWidth(weiuiJson.getInt(tempJson, "width"));
                tempMedia.setHeight(weiuiJson.getInt(tempJson, "height"));
                tempMedia.setChecked(weiuiJson.getBoolean(tempJson, "checked"));
                tempMedia.setMimeType(weiuiJson.getInt(tempJson, "mimeType"));
                tempMedia.setPosition(weiuiJson.getInt(tempJson, "position"));
                tempMedia.setCompressed(weiuiJson.getBoolean(tempJson, "compressed"));
                tempMedia.setPictureType(weiuiJson.getString(tempJson, "pictureType"));
                selected.add(tempMedia);
            }
        }
        return selected;
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 打开相册
     * @param object
     * @param callback
     */
    @JSMethod
    public void create(String object, final JSCallback callback) {
        final JSONObject json = weiuiJson.parseObject(object);
        //
        PageActivity.startTransparentPage(mWXSDKInstance.getContext(), new JSCallback() {

            @Override
            public void invoke(Object data) {
                if (callback != null) {
                    callback.invoke(data);
                }
            }

            @Override
            public void invokeAndKeepAlive(Object data) {
                Map<String, Object> retData = weiuiMap.objectToMap(data);
                String pageName = weiuiParse.parseStr(retData.get("pageName"));
                String status = weiuiParse.parseStr(retData.get("status"));
                PageBean mBean = weiuiPage.getPageBean(pageName);
                if (mBean == null) {
                    return;
                }
                switch (status) {
                    case "create":
                        List<LocalMedia> selected = toLocalMedia(weiuiJson.parseArray(json.getString("selected")));
                        PictureSelectionModel model;
                        if (weiuiJson.getString(json, "type", "gallery").equals("camera")) {
                            model = PictureSelector.create(mBean.getActivity())
                                    .openCamera(weiuiJson.getInt(json, "gallery", PictureMimeType.ofAll())); // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        }else{
                            model = PictureSelector.create(mBean.getActivity())
                                    .openGallery(weiuiJson.getInt(json, "gallery", PictureMimeType.ofAll())); // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        }
                        model.maxSelectNum(weiuiJson.getInt(json, "maxNum", 9))                         // 最大选择数量 int
                                .minSelectNum(weiuiJson.getInt(json, "minNum", 0))                      // 最小选择数量 int
                                .imageSpanCount(weiuiJson.getInt(json, "spanCount", 4))                 // 每行显示个数 int
                                .selectionMode(weiuiJson.getInt(json, "mode", PictureConfig.MULTIPLE))  // 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                .previewImage(weiuiJson.getBoolean(json, "previewImage", true))         // 是否可预览图片 true or false
                                .previewVideo(weiuiJson.getBoolean(json, "previewVideo", true))         // 是否可预览视频 true or false
                                .enablePreviewAudio(weiuiJson.getBoolean(json, "previewAudio", true))   // 是否可播放音频 true or false
                                .isCamera(weiuiJson.getBoolean(json, "camera", true))                   // 是否显示拍照按钮 true or false
                                .imageFormat(weiuiJson.getString(json, "format", PictureMimeType.JPEG)) // 拍照保存图片格式后缀,默认jpeg
                                .isZoomAnim(weiuiJson.getBoolean(json, "zoomAnim", true))               // 图片列表点击 缩放效果 默认true
                                .sizeMultiplier(weiuiJson.getFloat(json, "multiplier", 0.5f))           // glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                .enableCrop(weiuiJson.getBoolean(json, "crop", false))                  // 是否裁剪 true or false
                                .compress(weiuiJson.getBoolean(json, "compress", false))                // 是否压缩 true or false
                                .glideOverride(weiuiJson.getInt(json, "overrideWidth", 100), weiuiJson.getInt(json, "overrideHeight", 100))     // int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                                .withAspectRatio(weiuiJson.getInt(json, "ratioX", 1), weiuiJson.getInt(json, "ratioY", 1))                      // int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                .hideBottomControls(weiuiJson.getBoolean(json, "cropControls", false))  // 是否显示uCrop工具栏，默认不显示 true or false
                                .isGif(weiuiJson.getBoolean(json, "gif", false))                        // 是否显示gif图片 true or false
                                .freeStyleCropEnabled(weiuiJson.getBoolean(json, "freeCrop", false))    // 裁剪框是否可拖拽 true or false
                                .circleDimmedLayer(weiuiJson.getBoolean(json, "circle", false))         // 是否圆形裁剪 true or false
                                .showCropFrame(weiuiJson.getBoolean(json, "cropFrame", true))           // 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                                .showCropGrid(weiuiJson.getBoolean(json, "cropGrid", true))             // 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                .openClickSound(weiuiJson.getBoolean(json, "clickSound", false))        // 是否开启点击声音 true or false
                                .selectionMedia(selected)                                               // 是否传入已选图片 List<LocalMedia> list
                                .previewEggs(weiuiJson.getBoolean(json, "eggs", false))                 // 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                                .cropCompressQuality(weiuiJson.getInt(json, "quality", 90))             // 裁剪压缩质量 默认90 int
                                .minimumCompressSize(weiuiJson.getInt(json, "compressSize", 100))       // 小于100kb的图片不压缩
                                .synOrAsy(weiuiJson.getBoolean(json, "sync", true))                     // 同步true或异步false 压缩 默认同步
                                .cropWH(weiuiJson.getInt(json, "cropWidth", 0), weiuiJson.getInt(json, "cropHeight", 0))                        // 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                                .rotateEnabled(weiuiJson.getBoolean(json, "rotate", true))              // 裁剪是否可旋转图片 true or false
                                .scaleEnabled(weiuiJson.getBoolean(json, "scale", true))                // 裁剪是否可放大缩小图片 true or false
                                .videoQuality(weiuiJson.getInt(json, "videoQuality", 0))                // 视频录制质量 0 or 1 int
                                .videoMaxSecond(weiuiJson.getInt(json, "videoMaxSecond", 15))           // 显示多少秒以内的视频or音频也可适用 int
                                .videoMinSecond(weiuiJson.getInt(json, "videoMinSecond", 10))           // 显示多少秒以内的视频or音频也可适用 int
                                .recordVideoSecond(weiuiJson.getInt(json, "recordVideoSecond", 60))     // 视频秒数录制 默认60s int
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;

                    case "activityResult":
                        int requestCode = weiuiParse.parseInt(retData.get("requestCode"));
                        int resultCode = weiuiParse.parseInt(retData.get("resultCode"));
                        if (resultCode == RESULT_OK) {
                            switch (requestCode) {
                                case PictureConfig.CHOOSE_REQUEST:
                                    if (callback != null) {
                                        Map<String, Object> callData = new HashMap<>();
                                        callData.put("status", "success");
                                        callData.put("lists", PictureSelector.obtainMultipleResult((Intent) retData.get("resultData")));
                                        callback.invokeAndKeepAlive(callData);
                                    }
                                    break;
                            }
                        }
                        mBean.getActivity().finish();
                        break;
                }
                if (callback != null) {
                    callback.invokeAndKeepAlive(data);
                }
            }
        });
    }

    /**
     * 压缩图片
     * @param object
     * @param callback
     */
    @JSMethod
    public void compressImage(String object, final JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        final List<LocalMedia> selected = toLocalMedia(weiuiJson.parseArray(json.getString("lists")));
        Luban.with(mWXSDKInstance.getContext())
                .loadLocalMedia(selected)
                .ignoreBy(weiuiJson.getInt(json, "compressSize", 100))
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(List<LocalMedia> list) {
                        RxBus.getDefault().post(new EventEntity(PictureConfig.CLOSE_PREVIEW_FLAG));
                        if (callback != null) {
                            Map<String, Object> callData = new HashMap<>();
                            callData.put("status", "success");
                            callData.put("lists", list);
                            callback.invokeAndKeepAlive(callData);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        RxBus.getDefault().post(new EventEntity(PictureConfig.CLOSE_PREVIEW_FLAG));
                        if (callback != null) {
                            Map<String, Object> callData = new HashMap<>();
                            callData.put("status", "error");
                            callData.put("lists", selected);
                            callback.invokeAndKeepAlive(callData);
                        }
                    }
                }).launch();
    }

    /**
     * 预览图片
     * @param position
     * @param array
     */
    @JSMethod
    public void picturePreview(int position, String array, JSCallback callback) {
        JSONArray lists = weiuiJson.parseArray(array);
        if (lists.size() == 0) {
            JSONObject tempJson = new JSONObject();
            tempJson.put("path", array);
            lists.add(tempJson);
        }
        //
        List<LocalMedia> mediaLists = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            LocalMedia tempMedia = new LocalMedia();
            if (lists.get(i) instanceof String) {
                tempMedia.setPath((String) lists.get(i));
            }else{
                JSONObject tempJson = weiuiJson.parseObject(lists.get(i));
                tempMedia.setPath(tempJson.getString("path"));
            }
            mediaLists.add(tempMedia);
        }
        if (mediaLists.size() == 0) {
            return;
        }
        PictureSelector.create((Activity) mWXSDKInstance.getContext()).externalPicturePreview(position, mediaLists, callback);
    }

    /**
     * 预览视频
     * @param path
     */
    @JSMethod
    public void videoPreview(String path) {
        PictureSelector.create((Activity) mWXSDKInstance.getContext()).externalPictureVideo(path);
    }

    /**
     * 缓存清除，包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
     */
    @JSMethod
    public void deleteCache() {
        PictureFileUtils.deleteCacheDirFile(mWXSDKInstance.getContext());
    }
}
