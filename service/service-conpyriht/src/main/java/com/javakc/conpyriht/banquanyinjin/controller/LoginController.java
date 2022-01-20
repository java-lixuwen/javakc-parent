package com.javakc.conpyriht.banquanyinjin.controller;

import com.javakc.oss.commonutils.api.APICODE;
import org.springframework.web.bind.annotation.*;

/**
 * @Progrom: javakc-parent
 * @ClassName: LoginController
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/15 17:18
 */

    @RestController
    @RequestMapping("/conpyriht/user")
    @CrossOrigin
    public class LoginController {

        @PostMapping("login")
        public APICODE login() {
            return APICODE.OK().data("token", "admin");
        }

        @GetMapping("info")
        public APICODE info() {
            return APICODE.OK().data("roles", "[admin]").data("name", "admin").data("avatar", "http://img0.imgtn.bdimg.com/it/u=1782959667,617309577&fm=26&gp=0.jpg");
        }
    }


