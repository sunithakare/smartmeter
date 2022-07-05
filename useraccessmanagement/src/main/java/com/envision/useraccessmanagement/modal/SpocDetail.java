package com.envision.useraccessmanagement.modal;

import lombok.Data;

@Data
public class SpocDetail {
    private String username;
    private String appUrls;
    private String ouName;
    private String securityGroups;
    private String systemDestinationIpSubnets;
    private String serviceSshHttps;
    private String customPorts;
}