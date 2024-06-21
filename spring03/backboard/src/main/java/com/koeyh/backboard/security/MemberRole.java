package com.koeyh.backboard.security;


import lombok.Getter;

@Getter
public enum MemberRole {

    // ADMIN("관리자", "ROLE_ADMIN"), USER("사용자", "ROLE_USER");
    // ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    // MemberRole(String value) {
    //     this.key = key;
    //     this.value = value;
    // }

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    MemberRole(String value) {
        this.value = value;
    }
    
    private String value;
}
