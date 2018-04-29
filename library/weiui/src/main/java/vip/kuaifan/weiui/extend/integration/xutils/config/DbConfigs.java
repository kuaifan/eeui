package vip.kuaifan.weiui.extend.integration.xutils.config;

import vip.kuaifan.weiui.extend.integration.xutils.DbManager;
import vip.kuaifan.weiui.extend.integration.xutils.common.util.LogUtil;
import vip.kuaifan.weiui.extend.integration.xutils.ex.DbException;

/**
 * Created by wyouflf on 15/7/31.
 * 全局db配置
 */
public enum DbConfigs {

    HTTP(getCustom("cache")),

    COOKIE(getCustom("cookie"));

    private DbManager.DaoConfig config;

    DbConfigs(DbManager.DaoConfig config) {
        this.config = config;
    }

    public DbManager.DaoConfig getConfig() {
        return config;
    }

    public static DbManager.DaoConfig getCustom(String dirName) {
        dirName = dirName == null ? "cache" : dirName.replaceAll("/", "_");
        return new DbManager.DaoConfig()
                .setDbName("xUtils_http_" + dirName + ".db")
                .setDbVersion(1)
                .setDbOpenListener(db -> db.getDatabase().enableWriteAheadLogging())
                .setDbUpgradeListener((db, oldVersion, newVersion) -> {
                    try {
                        db.dropDb(); // 默认删除所有表
                    } catch (DbException ex) {
                        LogUtil.e(ex.getMessage(), ex);
                    }
                });
    }
}
