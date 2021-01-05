package com.springsecurity.security5_practice2.context.bcm.service;

import com.springsecurity.security5_practice2.context.auth.service.AuthorityService;
import com.springsecurity.security5_practice2.context.bcm.domain.SubMenu;
import com.springsecurity.security5_practice2.context.bcm.domain.TopMenu;
import com.springsecurity.security5_practice2.context.bcm.repository.SubMenuRepository;
import com.springsecurity.security5_practice2.context.bcm.repository.TopMenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FunctionsServiceImpl implements FunctionsService {

    @Autowired
    SubMenuRepository subMenuRepository;

    @Autowired
    TopMenuRepository topMenuRepository;

    @Autowired
    AuthorityService authorityService;

    @Override
    public List<SubMenu> getSubMenuList() {
        return subMenuRepository.findAll();
    }

    @Override
    public List<TopMenu> getTopMenuList() {
        return topMenuRepository.findAll();
    }

    @Override
    public void createSubMenu(SubMenu submenu) {
        subMenuRepository.save(submenu);
        authorityService.createPermission(submenu.getId(), submenu.getName());
    }

    @Override
    public void editSubMenu(Long id, SubMenu submenu) {
        SubMenu persistSub = subMenuRepository.findById(id).get();
        BeanUtils.copyProperties(submenu, persistSub, "id");
        subMenuRepository.save(persistSub);
    }
    
    @Override
    public void deleteSubMenu(Long id) {
        subMenuRepository.deleteById(id);
        authorityService.deletePers(id);    // menu와 연결되어있는 권한 & 역할 내에 권한 삭제
    }

    @Override
    public void createTopMenu(TopMenu topmenu) {
        topMenuRepository.save(topmenu);
    }

    @Override
    public void editTopMenu(Long id, TopMenu topmenu) {
        TopMenu persistTop = topMenuRepository.findById(id).get();
        if(!topmenu.getTopMenuUsage()) {    // 상위 메뉴를 사용하지 않게 수정하면 SET TOPMENUID OF 하위메뉴 NULL
            setNullTopId(id);
        }
        BeanUtils.copyProperties(topmenu, persistTop, "id");
        topMenuRepository.save(persistTop);
    }

    @Override
    public void deleteTopMenu(Long id) {
        topMenuRepository.deleteById(id);
        setNullTopId(id);
    }

    @Override
    public List<TopMenu> getTopMenuBySub(List<Long> subMenus) {
        List<TopMenu> topmenus = new ArrayList<>();
        for(Long sub : subMenus) {
            TopMenu top = topMenuRepository.findById(subMenuRepository.findById(sub).get().getTopMenuId()).get();
            if(!topmenus.contains(top))
            {
                topmenus.add(top);
            }
        }

        return topmenus;
    }

    public void setNullTopId(Long id) {
        List<SubMenu> subList = subMenuRepository.findByTopMenuId(id);
        for(SubMenu sub : subList) {
            sub.setTopMenuId(null);
            subMenuRepository.save(sub);
        }
    }
}
