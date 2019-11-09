package com.dzz.medical.supervise.controller;

import com.dzz.medical.common.response.ResponseDzz;
import com.dzz.medical.supervise.domain.dto.SupervisePostParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监督提交
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年10月14 13:55
 */
@RestController
@RequestMapping("/api/supervise")
@Slf4j
public class SuperviseController {


    /**
     * 测试一下
     * @return 结果
     */
    @GetMapping("/testSupervise")
    @PreAuthorize("hasRole('m_user')")
    public ResponseDzz testSupervise() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{}", authentication);
        return ResponseDzz.ok(String.valueOf(System.currentTimeMillis()));
    }


    /**
     * 提交诚信自律信息
     * @param param 信息
     * @return 结果
     */
    @PostMapping("/postSupervise")
//    @PreAuthorize("hasRole('m_user')")
    public ResponseDzz postSupervise(@RequestBody List<SupervisePostParam> param){

        log.info("接收到的数据为:{}", param.toString());
        return ResponseDzz.ok();
    }

}
