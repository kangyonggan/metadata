package com.kangyonggan.metadata.controller.dashboard;

import com.kangyonggan.metadata.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 1/19/18
 */
@Controller
@RequestMapping("dashboard/metadata/manager")
public class DashboardMetadataManagerController extends BaseController {

    /**
     * 元数据维护
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("METADATA_MANAGER")
    public String list() {
        return getPathList();
    }

}
