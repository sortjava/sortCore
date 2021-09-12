package com.sort.sortcore.config;

import com.sort.sortcore.data.ERole;
import com.sort.sortcore.data.Role;
import com.sort.sortcore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class DemoCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role();
        roleUser.setId(1);
        roleUser.setName(ERole.ROLE_USER);
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setId(2);
        roleAdmin.setName(ERole.ROLE_ADMIN);
        roleRepository.save(roleAdmin);

        Role roleModerator = new Role();
        roleModerator.setId(3);
        roleModerator.setName(ERole.ROLE_MODERATOR);
        roleRepository.save(roleModerator);
    }
}