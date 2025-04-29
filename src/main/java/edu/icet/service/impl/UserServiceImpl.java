package edu.icet.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository repository;
    final ModelMapper mapper;
    final BCryptPasswordEncoder encoder;
    final EmailCheck emailCheck;

    @Override
    public Boolean saveUser(icet.edu.com.dto.UserDto userDto) {
        if (userDto == null || !emailCheck.isValid(userDto.getEmail()) || repository.findByEmail(userDto.getEmail()) != null) {
            return false;
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return repository.save(mapper.map(userDto, UserEntity.class)) != null;
    }

    @Override
    public icet.edu.com.dto.UserDto getUser(Long id) {
        return mapper.map(repository.findById(id), icet.edu.com.dto.UserDto.class);
    }

    @Override
    public Boolean deleteUser(Long id) {
        repository.deleteById(id);
        return repository.findById(id).isEmpty();
    }

    @Override
    public Boolean authenticateUser(LogInRequest request) {
        UserEntity byEmail = repository.findByEmail(request.getEmail());
        return byEmail !=null && encoder.matches(request.getPassword(),byEmail.getPassword());
    }

}
