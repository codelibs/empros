package org.codelibs.empros.db.allcommon;

import org.seasar.dbflute.Entity;

/**
 * The interface of entity defined common columns.
 * @author DBFlute(AutoGenerator)
 */
public interface EntityDefinedCommonColumn extends Entity {

    /**
     * Enable common column auto set up. <br />
     * It's only for after disable because the default is enabled.
     */
    void enableCommonColumnAutoSetup();

    /**
     * Disable common column auto set up. <br />
     * This is an old style. You can get the same process
     * by varyingInsert() and varyingUpdate() and so on.
     */
    void disableCommonColumnAutoSetup();

    /**
     * Can the entity set up common column by auto? (basically for Framework)
     * @return The determination, true or false.
     */
    boolean canCommonColumnAutoSetup();
}
