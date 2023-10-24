package tn.esprit.springjasser.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springjasser.entities.ERole;
import tn.esprit.springjasser.entities.Role;
import tn.esprit.springjasser.entities.User;
import tn.esprit.springjasser.payload.request.LoginRequest;
import tn.esprit.springjasser.payload.request.SignupRequest;
import tn.esprit.springjasser.payload.response.MessageResponse;
import tn.esprit.springjasser.payload.response.UserInfoResponse;
import tn.esprit.springjasser.repository.RoleRepository;
import tn.esprit.springjasser.repository.UserRepository;
import tn.esprit.springjasser.security.jwt.JwtUtils;
import tn.esprit.springjasser.security.services.UserDetailsImpl;
import tn.esprit.springjasser.security.services.UserService;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserService userService;


    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

       // ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //log.info("logged in");

        return ResponseEntity.ok(new UserInfoResponse(jwt,

                        userDetails.getId(),
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getUsername(),
                        userDetails.getDateOfBirth(),
                        userDetails.getEmail(),
                userDetails.getContactNumber(),
                        roles));
    }
    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getEmail(),
                signUpRequest.getContactNumber(),
                signUpRequest.getCreatedAt(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> roleList = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (roleList == null || roleList.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);

            roles.add(userRole);
        } else {
            for (String roleName : roleList) {
                ERole roleEnum = ERole.valueOf(roleName);
                Role userRole = roleRepository.findByName(roleEnum)
                        ;
                roles.add(userRole);
            }
        }

        user.setRole(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
       // log.info("logged out");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @PostMapping("/forgotpassword")
    public String processForgotPassword(@RequestParam String email) {
        String token = RandomString.make(45);
        try {
            userService.updateResetPasswordToken(token,email);

            String resetPasswordlink="http://localhost:8082/api/auth/resetPassword?token="+token;
            sendEmail(email, resetPasswordlink);
            return "Email sent";
        } catch (UsernameNotFoundException | MessagingException | UnsupportedEncodingException |
                 javax.mail.MessagingException ex){

        }
        return "dfghj";

    }
    private void sendEmail(String email, String resetPasswordlink) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
       /* MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("mariem.benkhlifa@esprit.tn","amal151617000");
        helper.setTo(email);
        String subject="Heres the link to reset your password";
        String content="<p>Hello,</p>" +
                "<p> You have requested to reset your password.</p>" +
                "<p> Click the link below to change the password:</p>"  +
                "<p><b><a href=\""+resetPasswordlink +"\">Change my password</a><b></p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
*/
    }

    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@Param(value="token") String token, Model model) {

        User user = userService.getByToken(token);
        if (user == null){
            model.addAttribute("title","Reset password");
            model.addAttribute("message","Invalid token");
            return "reset_password_form";
        }
        model.addAttribute("token",token);
        model.addAttribute("pageTitle","Reset your password");

        return "reset_password_form";
    }
    @GetMapping("/getUserByToken")
    public User get(@RequestParam String token){
        return userService.getByToken(token);
    }

    @PostMapping("/resetPassword")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset_password_formm";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
            return "hh";
        }


      //  return "reset_password_form";

    }
    @GetMapping("/users")
    public List<User> getusers(){

            return  userRepository.findAll();



    }
    @DeleteMapping("/deleteuser/{id}")
    public void deleteuser(@PathVariable("id") Long id){
      User userr = userRepository.findById(id).get();
      if(userr != null){
          userRepository.delete(userr);
      }




    }
}
