package vip.kuaifan.weiui.extend.integration.xutils.config;

import vip.kuaifan.weiui.extend.integration.xutils.DbManager;
import vip.kuaifan.weiui.extend.integration.xutils.common.util.LogUtil;
import vip.kuaifan.weiui.extend.integration.xutils.ex.DbException;

/**
 * Created by wyouflf on 15/7/31.
 * 全局db配置
 */
public enum DbConfigs {
    HTTP(new DbManager.DaoConfig()
            .setDbName("xUtils_http_cache.db")
            .setDbVersion(1)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    try {
                        db.dropDb(); // 默认删除所有表
                    } catch (DbException ex) {
                        LogUtil.e(ex.getMessage(), ex);
                    }
                }
            })),

    COOKIE(new DbManager.DaoConfig()
            .setDbName("xUtils_http_cookie.db")
            .setDbVersion(1)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    try {
                        db.dropDb(); // 默认删除所有表
                    } catch (DbException ex) {
                        LogUtil.e(ex.getMessage(), ex);
                    }
                }
            }));

    private DbManager.DaoConfig config;

    DbConfigs(DbManager.DaoConfig config) {
        this.config = config;
    }

    public DbManager.DaoConfig getConfig() {
        return config;
    }
}
