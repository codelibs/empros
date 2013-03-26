package org.codelibs.empros.db.allcommon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.dbflute.Entity;
import org.seasar.dbflute.bhv.core.CommonColumnAutoSetupper;

/**
 * The basic implementation of the auto set-upper of common column.
 * @author DBFlute(AutoGenerator)
 */
public class ImplementedCommonColumnAutoSetupper implements
        CommonColumnAutoSetupper {

    // =====================================================================================
    //                                                                            Definition
    //                                                                            ==========
    /** Log instance. */
    private static final Log _log = LogFactory
            .getLog(ImplementedCommonColumnAutoSetupper.class);

    // =====================================================================================
    //                                                                                Set up
    //                                                                                ======
    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCommonColumnOfInsertIfNeeds(final Entity targetEntity) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCommonColumnOfUpdateIfNeeds(final Entity targetEntity) {
    }

    // =====================================================================================
    //                                                                               Logging
    //                                                                               =======
    protected boolean isInternalDebugEnabled() {
        return DBFluteConfig.getInstance().isInternalDebug()
                && _log.isDebugEnabled();
    }

    protected void logSettingUp(final EntityDefinedCommonColumn entity,
            final String keyword) {
        _log.debug("...Setting up column columns of " + entity.getTableDbName()
                + " before " + keyword);
    }
}
