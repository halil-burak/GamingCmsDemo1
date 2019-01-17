package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.turkcell.playcell.cms.cmsdemo1.entity.User;
import com.turkcell.playcell.cms.cmsdemo1.service.UserService;
import com.turkcell.playcell.cms.cmsdemo1.util.UserNotFoundException;
import org.identityconnectors.common.security.GuardedString;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // Init Request with only token
    @GetMapping(path="/init", produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> userInit(@RequestHeader HttpHeaders headers) throws Exception {

        List<String> token = headers.get("Authorization");

        if (token != null) {
            int idx = token.get(0).indexOf(" ");
            Long id = JWTDecode(token.get(0).substring(idx+1));

            if (id != null) {
                if (userService.existsById(id)) {
                    User user = userService.retrieveUser(id);

                    // Prepare Response message here
                    JSONObject entity = new JSONObject();

                    entity.put("role", user.getRole());
                    entity.put("username", user.getUsername());
                    entity.put("isactive", user.getIsactive());

                    return new ResponseEntity<Object>(entity.toMap(), HttpStatus.OK);
                } else {
                    new UserNotFoundException(id);
                }
            }

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "/login", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> userLogin(@RequestBody User user, @RequestHeader HttpHeaders headers) throws Exception {

        // Get username key of Header and parse username and password
        List<String> header_username = headers.get("username");
        int unameidx = header_username.get(0).indexOf(":");
        //Retrieve username from header
        String hdr_uname = header_username.get(0).substring(0, unameidx);
        //Retrieve password from header
        String hdr_pw = header_username.get(0).substring(unameidx + 1, header_username.get(0).length());

        if (isUsernameValid(hdr_uname, hdr_pw)) {

            List<User> entityList = userService.retrieveUsers();

            for (User n : entityList) {
                if (n.getUsername().equalsIgnoreCase(user.getUsername())) {
                    if (n.getPassword().equals(user.getPassword())) {
                        // Login successful
                        User cmsuser = userService.retrieveUser(n.getId());

                        // Prepare Response message here
                        JSONObject entity = new JSONObject();

                        entity.put("role", cmsuser.getRole());
                        entity.put("username", cmsuser.getUsername());
                        entity.put("isactive", cmsuser.getIsactive());

                        String token = new String(
                                Objects.requireNonNull(JWTEncode(cmsuser.getId()),
                                        "id is null. token could not be acquired."));
                        entity.put("token", token);

                        if (token == "") {
                            return new ResponseEntity<Object>(HttpStatus.UNPROCESSABLE_ENTITY);
                        }

                        return new ResponseEntity<Object>(entity.toMap(), HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<Object>("User not found", HttpStatus.BAD_REQUEST);
                }
            }
        }

        return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path= "/users", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getUserlist(@RequestHeader HttpHeaders headers) {

        List<String> token = headers.get("Authorization");
        int idx = token.get(0).indexOf(" ");

        Long id = JWTDecode(token.get(0).substring(idx+1));

        if (id != null) {
            if (userService.existsById(id)) {

                List<User> entityList = userService.retrieveUsers();

                List<JSONObject> entities = new ArrayList<JSONObject>();

                for (User n : entityList) {

                    JSONObject entity = new JSONObject();

                    entity.put("id", n.getId());
                    entity.put("username", n.getUsername());
                    entity.put("role", n.getRole());
                    entity.put("isactive", n.getIsactive());
                    entities.add(entity);

                }

                return new ResponseEntity<String>(entities.toString(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

    }

    @PostMapping(path= "/users", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> newUser(@RequestHeader HttpHeaders headers, @RequestBody User newUser) {

        List<String> token = headers.get("Authorization");
        int idx = token.get(0).indexOf(" ");

        Long id = JWTDecode(token.get(0).substring(idx+1));

        if (id != null) {
            if (userService.existsById(id)) {

                List<User> entityList = userService.retrieveUsers();

                for (User n : entityList) {
                    // if this is a update request
                    if (n.getUsername().equals(newUser.getUsername())) {
                        return new ResponseEntity<Object>("username already exists !",HttpStatus.BAD_REQUEST);
                    }
                }

                userService.saveUser(newUser);

                JSONObject entity = new JSONObject();

                entity.put("id", newUser.getId());
                entity.put("username", newUser.getUsername());
                entity.put("role", newUser.getRole());
                entity.put("isactive", newUser.getIsactive());

                return new ResponseEntity<Object>(entity.toMap(),HttpStatus.OK);
            }
        }

        return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(path= "/users/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> replaceUser(@RequestHeader HttpHeaders headers, @RequestBody User newUser,
                                      @PathVariable Long id) {

        List<String> token = headers.get("Authorization");
        int idx = token.get(0).indexOf(" ");

        Long ids = JWTDecode(token.get(0).substring(idx+1));

        if (ids != null) {
            if (userService.existsById(ids)) {

                User updatedUser = userService.retrieveUser(id);

                if (newUser.getPassword() != null) {
                    updatedUser.setPassword(newUser.getPassword());
                }

                if (newUser.getRole() != null) {
                    updatedUser.setRole(newUser.getRole());
                }

                if (newUser.getIsactive() != null) {
                    updatedUser.setIsactive(newUser.getIsactive());
                }

                userService.saveUser(updatedUser);

                return new ResponseEntity<Object>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping(path="/users/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteUser(@RequestHeader HttpHeaders headers, @PathVariable Long id) {

        List<String> token = headers.get("Authorization");
        int idx = token.get(0).indexOf(" ");

        Long ids = JWTDecode(token.get(0).substring(idx+1));

        if (ids != null) {
            if (userService.existsById(ids)) {
                userService.deleteUser(id);
                return new ResponseEntity<String>("User is deleted from database", HttpStatus.OK);
            }
        }

        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    private String JWTEncode(Long id) {
        try {

            if (userService.existsById(id)) {

                User user = userService.retrieveUser(id);
                // secret key db de tutulacak
                Algorithm algorithm = Algorithm.HMAC256("playcell");

                String token = new String();

                // userid key db de tutulacak
                Map<String, Object> headerClaims = new HashMap();
                headerClaims.put("username", user.getUsername());
                headerClaims.put("role", user.getRole());
                headerClaims.put("id", id);

                token =	JWT.create().withHeader(headerClaims).sign(algorithm);
                return token;
            }
        } catch(JWTCreationException exception) {
            return null;
        }
        return null;
    }

    private boolean isUsernameValid(String hdr_uname, String hdr_pw) {
        // DB check eklenecek ayrıca securestring olacak
        if (hdr_uname.equals("cmsadmusr") && hdr_pw.equals("cmsadmpw")) {
            return true;
        }
        return false;
    }

    private Long JWTDecode(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Claim uname = jwt.getHeaderClaim("username");
            //Claim role = jwt.getHeaderClaim("role");
            Claim id = jwt.getHeaderClaim("id");

            if (userService.existsById(id.asLong())) {
                User user = userService.retrieveUser(id.asLong());
                if (user.getUsername().equals(uname.asString())) {
                    return id.asLong();
                }
            }

        } catch (JWTVerificationException exception) {
            return null;
        }
        return null;
    }

    // Conversion from GuardedString to String
    public String convertToClearText(GuardedString pass){

        final StringBuilder sbPass = new StringBuilder();

        pass.access(chars -> sbPass.append(new String(chars)));
        return sbPass.toString();
    }

    // Conversion from String to GuardedString
    public GuardedString convertToGuardedString(String s) {

        if (!s.isEmpty()) {
            GuardedString gs = new GuardedString(s.toCharArray());
            return gs;
        }
        return null;
    }
}
