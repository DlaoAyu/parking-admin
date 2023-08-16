package com.laoayu.parking.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/oauth")
public class RestAuthController {

//    http://localhost:9999/oauth/render
    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest();

        return authRequest.login(callback);
//        AuthResponse login = authRequest.login(callback);
//        JSONObject remoteData = JSONObject.parseObject(JSON.toJSONString(login));
//        Object gitEEData = remoteData.get("data");
//        JSONObject user = JSONObject.parseObject(JSON.toJSONString(gitEEData));
//        Object nickname = user.get("nickname");
//        Object email = user.get("email");
//        Object avatar = user.get("avatar");
//        HashMap<String, Object> userMap = new HashMap<>();
//        userMap.put("nickname",nickname);
//        userMap.put("email",email);
//        userMap.put("avatar",avatar);
//
//        // TODO
//
//        return userMap;
    }

    private AuthRequest getAuthRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId("f668b0167d544a9a0e2b5a02a0fca98a8f9a6d13de5fb04c945dc86f4760618f")
                .clientSecret("07b226edbf73678eae1a6bf17149a7a1cde135a2568c164c762099711871f49c")
                .redirectUri("http://localhost:9999/oauth/callback")
                .build());
    }
}
