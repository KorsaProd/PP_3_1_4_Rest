package com.example.pp_3_1_4_rest.service;

import com.example.pp_3_1_4_rest.model.Role;
import com.example.pp_3_1_4_rest.model.User;
import com.example.pp_3_1_4_rest.repository.RoleRepository;
import com.example.pp_3_1_4_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

 private final RoleService roleService;
 private final UserRepository userRepository;
 private final RoleRepository roleRepository;

 private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

 @Autowired
 public UserServiceImpl(RoleService roleService, UserRepository userRepository, RoleRepository roleRepository) {
  this.roleService = roleService;
  this.userRepository = userRepository;
  this.roleRepository = roleRepository;
 }

 public List<User> getAllUsers() {
  return userRepository.findAll();
 }

 @Transactional
 public User saveUser(User user, String role) {
  User newUser = new User();
  newUser.setRoles(roleService.getRoleSetFromString(newUser, role));
  newUser.setName(user.getName());
  newUser.setLastName(user.getLastName());
  newUser.setAge(user.getAge());
  newUser.setPassword(user.getPassword());
  newUser.setId(user.getId());
  newUser.setPassword(passwordEncoder.encode(user.getPassword()));
  return userRepository.save(newUser);
 }

 @Transactional
 public void edit(User user, Integer id, String role) {
  User editUser = new User();
  editUser.setName(user.getName());
  editUser.setLastName(user.getLastName());
  editUser.setAge(user.getAge());
  editUser.setRoles(roleService.getRoleSetFromString(user, role));
  editUser.setId(id);
  editUser.setPassword(passwordEncoder.encode(user.getPassword()));
  userRepository.save(editUser);
 }

 public void removeUserById(Integer id) {
  userRepository.deleteById(id);
 }

 public User getUserById(Integer id) {
  return userRepository.getById(id);
 }

 public User findByUsername(String username) {
  return userRepository.findByUsername(username);
 }

 public List<Role> listRoles() {
  return roleRepository.findAll();
 }

 @Override
 @Transactional
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user = findByUsername(username);
  if (user == null) {
   throw new UsernameNotFoundException(String.format("User '%s' not found", username));
  }
  return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
          mapRolesToAuthorities(user.getRoles()));
 }

 private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
  return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
 }

 //Добавление пользователя для теста функционала
 @PostConstruct
 public User createTestAdmin() {
  if (userRepository.findByUsername("admin") == null) {
   User user = new User("admin", "test", 30);
   user.setId(1);
   user.setPassword(passwordEncoder.encode("admin"));
   user.addRole(new Role(user.getId(), "ROLE_ADMIN"));
   user.addRole(new Role(user.getId(), "ROLE_USER"));
   return userRepository.save(user);
  }
  return null;
 }
}
