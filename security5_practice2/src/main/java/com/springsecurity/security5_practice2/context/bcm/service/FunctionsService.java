package com.springsecurity.security5_practice2.context.bcm.service;

import com.springsecurity.security5_practice2.context.bcm.domain.SubMenu;
import com.springsecurity.security5_practice2.context.bcm.domain.TopMenu;

import java.util.List;

public interface FunctionsService {
    List<SubMenu> getSubMenuList();
    List<TopMenu> getTopMenuList();
    void createSubMenu(SubMenu submenu);
    void editSubMenu(Long id, SubMenu submenu);
    void deleteSubMenu(Long id);

    void createTopMenu(TopMenu topmenu);
    void editTopMenu(Long id, TopMenu topmenu);
    void deleteTopMenu(Long id);

    List<TopMenu> getTopMenuBySub(List<Long> subMenus);
}
