package com.viloveul.module.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${viloveul.controller.setting:/setting}")
public class SettingController {
}
