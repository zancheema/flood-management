package com.shaikha.floodmanagement.auth;

import com.shaikha.floodmanagement.auth.dto.ForgotPasswordPayload;
import com.shaikha.floodmanagement.auth.dto.SignUpPayload;
import com.shaikha.floodmanagement.authority.AuthorityManager;
import com.shaikha.floodmanagement.authority.AuthorityManagerRepository;
import com.shaikha.floodmanagement.rescue.RescueTeam;
import com.shaikha.floodmanagement.rescue.RescueTeamRepository;
import com.shaikha.floodmanagement.user.User;
import com.shaikha.floodmanagement.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RescueTeamRepository rescueTeamRepository;
    private final AuthorityManagerRepository authorityManagerRepository;

    private final TokenService tokenService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RescueTeamRepository rescueTeamRepository, AuthorityManagerRepository authorityManagerRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rescueTeamRepository = rescueTeamRepository;
        this.authorityManagerRepository = authorityManagerRepository;
        this.tokenService = tokenService;
    }

    @Override
    public boolean signUp(SignUpPayload payload) {
        boolean alreadyExists = userRepository.existsByUsername(payload.getUsername()) ||
                userRepository.existsByEmail(payload.getEmail());
        if (alreadyExists) return false;

        User user = new User();
        user.setUsername(payload.getUsername());
        user.setEmail(payload.getEmail());
        String encPassword = passwordEncoder.encode(payload.getPassword());
        user.setPassword(encPassword);

        List<String> roles = payload.getRoles();
        if (roles.isEmpty()) return false;
        if (roles.contains("authority")) {
            AuthorityManager authority = authorityManagerRepository.save(new AuthorityManager());
            user.setAuthorityManager(authority);
        }
        if (roles.contains("rescue")) {
            RescueTeam rescue = rescueTeamRepository.save(new RescueTeam());
            user.setRescueTeam(rescue);
        }

        userRepository.save(user);

        return true;
    }

    @Override
    public Optional<AuthToken> login(Authentication authentication, String role, boolean remember) {
        User user = userRepository.findByUsername(authentication.getName()).get();

        if (role.equals("authority") && user.getAuthorityManager() == null) {
            return Optional.empty();
        }
        if (role.equals("rescue") && user.getRescueTeam() == null) {
            return Optional.empty();
        }

        return Optional.of(tokenService.generateToken(authentication, role, remember));
    }

    @Override
    public boolean forgotPassword(ForgotPasswordPayload payload) {
        Optional<User> optionalUser = userRepository.findByUsernameAndEmail(payload.getUsername(), payload.getEmail());
        if (optionalUser.isEmpty()) return false;
        User user = optionalUser.get();
        String encPassword = passwordEncoder.encode(payload.getPassword());
        user.setPassword(encPassword);
        userRepository.save(user);
        return true;
    }
}
